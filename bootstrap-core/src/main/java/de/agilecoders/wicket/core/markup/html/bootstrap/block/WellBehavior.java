package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

/**
 * #### Description
 *
 * Use the well as a simple effect on an element to give it an inset effect.
 * Control padding and rounded corners with two optional modifier classes ({@link Size}).
 *
 * documentation: http://getbootstrap.com/components/#wells
 *
 * #### Usage
 *
 * make a component looks like a well with tighter margin and padding:
 *
 * ```java
 * component.add(new WellBehavior(Size.Small));
 * ```
 *
 * ```html
 * <div wicket:id="id">content</div>
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class WellBehavior extends Behavior {

    /**
     * defines the size of a well
     */
    public enum Size implements ICssClassNameProvider {
        // TODO miha: add documentation
        Default(""), Small("-sm"), Large("-lg");

        private final String cssName;

        private Size(String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "well" + cssName;
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
        this(Model.of(Args.notNull(size, "size")));
    }

    /**
     * Construct.
     *
     * @param sizeModel The size of the well
     */
    public WellBehavior(IModel<Size> sizeModel) {
        super();

        this.size = Args.notNull(sizeModel, "size");
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
