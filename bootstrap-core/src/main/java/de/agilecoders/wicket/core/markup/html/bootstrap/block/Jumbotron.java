package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

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
public class Jumbotron extends WebMarkupContainer {

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Jumbotron(final String componentId) {
        this(componentId, null);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Jumbotron(final String componentId, final IModel<?> model) {
        super(componentId, model);

        add(new JumbotronBehavior());
    }

}



