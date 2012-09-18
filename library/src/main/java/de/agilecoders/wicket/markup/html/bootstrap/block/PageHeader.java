package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * A simple shell for an h1 to appropriately space out and segment sections
 * of content on a page. It can utilize the h1's default small, element as
 * well most other components (with additional styles).
 *
 * @author miha
 * @version 1.0
 */
public class PageHeader extends Panel {

    private Label subtitle;

    /**
     * Construct.
     *
     * @param markupId The markup id
     */
    public PageHeader(final String markupId) {
        super(markupId);
    }

    /**
     * Construct.
     *
     * @param markupId The markup id
     * @param model    The label of the page header
     */
    public PageHeader(final String markupId, final IModel<String> model) {
        super(markupId, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CssClassNameAppender("page-header"));
        add(new AssertTagNameBehavior("div"));
        add(new Label("label", getDefaultModel()));
        add(subtitle = new Label("subtitle"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        Components.hideIfModelIsEmpty(subtitle);
    }

    /**
     * sets the subtitle model.
     *
     * @param subtitle The subtitle as model
     * @return this component's instance
     */
    public PageHeader setSubtitle(final IModel<String> subtitle) {
        this.subtitle.setDefaultModel(subtitle);
        return this;
    }
}
