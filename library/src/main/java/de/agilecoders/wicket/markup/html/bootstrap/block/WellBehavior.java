package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Use the well as a simple effect on an element to give it an inset effect.
 * Control padding and rounded corners with two optional modifier classes ({@link Size}).
 *
 * @author miha
 * @version 1.0
 */
public class WellBehavior extends AssertTagNameBehavior {

    /**
     * defines the size of a well
     */
    public enum Size implements ICssClassNameProvider {
        Default, Small, Large;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "well-" + name().toLowerCase();
        }

        @Override
        public AttributeModifier newCssClassNameModifier() {
            return new CssClassNameAppender(this);
        }
    }

    private final IModel<Size> size;
    private final CssClassNameAppender cssClassNameAppender;

    /**
     * Construct.
     * <p/>
     * This constructor uses the default well size.
     */
    public WellBehavior() {
        this(Size.Default);
    }

    /**
     * Construct.
     *
     * @param size The size of the well
     */
    public WellBehavior(Size size) {
        this(Model.of(size));
    }

    /**
     * Construct.
     *
     * @param sizeModel The size of the well
     */
    public WellBehavior(IModel<Size> sizeModel) {
        super("div", "span");

        this.size = sizeModel;
        this.cssClassNameAppender = new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return CssClassNames.parse("well").add(size.getObject().cssClassName()).asString();
            }
        });
    }

    /**
     * sets the well size
     *
     * @param size The size of the well element
     * @return this instance
     */
    public final WellBehavior setSize(Size size) {
        this.size.setObject(size);

        return this;
    }

    /**
     * @return the current size of the well element
     */
    public final Size getSize() {
        return size.getObject();
    }

    @Override
    public void detach(final Component component) {
        super.detach(component);

        size.detach();
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
        component.add(cssClassNameAppender);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
        component.remove(cssClassNameAppender);
    }
}
