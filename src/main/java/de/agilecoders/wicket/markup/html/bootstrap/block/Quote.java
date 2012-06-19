package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Block-level element for quoting content from another source.
 * <p/>
 * <pre>
 *     <blockquote wicket:id="componentId">
 *          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 *          <small>Someone famous</small>
 *     </blockquote>
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class Quote extends WebMarkupContainer {

    private final QuoteBehavior quoteBehavior;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Quote(final String componentId) {
        this(componentId, null);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Quote(final String componentId, final IModel<?> model) {
        super(componentId, model);

        quoteBehavior = new QuoteBehavior();
        add(quoteBehavior);
    }

    /**
     * floats the quote to the right.
     *
     * @return the component's current instance
     */
    public Quote pullRight() {
        quoteBehavior.pullRight();

        return this;
    }

}



