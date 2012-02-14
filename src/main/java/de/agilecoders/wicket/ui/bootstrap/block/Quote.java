package de.agilecoders.wicket.ui.bootstrap.block;

import de.agilecoders.wicket.ui.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.ComponentTag;
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

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Quote(final String componentId) {
        super(componentId);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Quote(final String componentId, final IModel<?> model) {
        super(componentId, model);
    }

    /**
     * floats the quote to the right.
     *
     * @return the component's current instance
     */
    public Quote pullRight() {
        add(new CssClassNameAppender("pull-right"));

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "blockquote");
    }
}



