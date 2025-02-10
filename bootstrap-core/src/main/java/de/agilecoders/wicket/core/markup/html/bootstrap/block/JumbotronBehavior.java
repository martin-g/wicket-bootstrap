package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;

/**
 * #### Description
 *
 * A lightweight, flexible component that can optionally extend the entire viewport to showcase key content on your site.
 *
 * documentation: http://getbootstrap.com/components/#jumbotron
 *
 * #### Usage
 *
 * ```java
 * component.add(new HeroBehavior());
 * ```
 *
 * ```html
 * <pre>{@code
 *     <div wicket:id="componentId">
 *          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 *          <small>Someone famous</small>
 *     </div>
 * }</pre>
 * ```
 *
 * To make the jumbotron full width, and without rounded corners, place it outside all `.containers` and instead add a
 * `.container` within.
 *
 * ```html
 * <pre>{@code
 *     <div wicket:id="componentId">
 *         <div class="container">
 *             content
 *         </div>
 *     </div>
 * }</pre>
 * ```
 */
public class JumbotronBehavior extends Behavior {

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.addClass(tag, "jumbotron");
        Components.assertTag(component, tag, "div");
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
