package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.util.Attributes;
import de.agilecoders.wicket.util.Components;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Use the well as a simple effect on an element to give it an inset effect.
 * Control padding and rounded corners with two optional modifier classes ({@link Size}).
 *
 * @author miha
 */
public class WellBehavior extends Behavior {

    /**
     * defines the size of a well
     */
    public enum Size implements ICssClassNameProvider {
        Default, Small, Large;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "well-" + name().toLowerCase();
        }

    }

    private final IModel<Size> size;

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
        super();

        this.size = sizeModel;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, CssClassNames.parse("well").add(size.getObject().cssClassName()).asSet());
        Components.assertTag(component, tag, "div", "span");
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
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }
}
