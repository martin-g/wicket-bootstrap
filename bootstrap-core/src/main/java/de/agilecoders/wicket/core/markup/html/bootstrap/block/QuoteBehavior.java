package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * #### Description
 *
 * Block-level element for quoting content from another source.
 *
 * #### Usage
 *
 * ```java
 * component.add(new QuoteBehavior()
 *                  .pullRight()); // set content right aligned (optional, default: left)
 * ```
 *
 * ```html
 * <pre>{@code
 *     <blockquote wicket:id="componentId">
 *          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 *          <small>Someone famous</small>
 *     </blockquote>
 * }</pre>
 * ```
 */
public class QuoteBehavior extends Behavior {

    private final IModel<String> pullRight;

    /**
     * Construct.
     */
    public QuoteBehavior() {
        super();

        this.pullRight = Model.of("");
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "blockquote");
        Attributes.addClass(tag, "blockquote", pullRight.getObject());
    }

    /**
     * sets the floating of the quote to "right".
     *
     * @return this component's instance
     */
    public final QuoteBehavior pullRight() {
        pullRight.setObject("float-end");

        return this;
    }

    /**
     * sets the floating of the quote to "left".
     *
     * @return this component's instance
     */
    public final QuoteBehavior pullLeft() {
        pullRight.setObject("");

        return this;
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
