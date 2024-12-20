package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * #### Description
 *
 * Block-level element for quoting content from another source.
 *
 * #### Usage
 *
 * ```java
 * Quote quote = new Quote("componentId")
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
public class Quote extends WebMarkupContainer {

    private final QuoteBehavior quoteBehavior;

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     */
    public Quote(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     * @param model       the component's model
     */
    public Quote(final String markupId, final IModel<?> model) {
        super(markupId, model);

        quoteBehavior = new QuoteBehavior();
        add(quoteBehavior);
    }

    /**
     * floats the quote to the right.
     *
     * @return the component's current instance
     */
    public final Quote pullRight() {
        quoteBehavior.pullRight();

        return this;
    }

    /**
     * sets the floating of the quote to "left".
     *
     * @return this component's instance
     */
    public final Quote pullLeft() {
        quoteBehavior.pullLeft();

        return this;
    }

}



