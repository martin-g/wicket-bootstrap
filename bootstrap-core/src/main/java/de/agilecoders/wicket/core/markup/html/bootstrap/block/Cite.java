package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * #### Description
 *
 * A Cite just asserts the correct tag name and appends the
 * {@link de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior}.
 *
 * #### Usage
 *
 * ```java
 * Cite cite = new Cite("id");
 * ```
 *
 * ```html
 * {@code <cite wicket:id="id">text</cite>}
 * ```
 */
public class Cite extends WebMarkupContainer {

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     */
    public Cite(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param markupId The non-null id of a new component
     * @param model       the component's model
     */
    public Cite(final String markupId, final IModel<?> model) {
        super(markupId, model);

        add(new CiteBehavior());
    }
}
