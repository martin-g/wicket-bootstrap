package de.agilecoders.wicket.markup.html.bootstrap.tabs;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Simple {@link AbstractTab} that contains only a {@link TextPanel}.
 *
 * @author miha
 * @version 1.0
 */
public class TextContentTab extends AbstractTab {
    private final IModel<String> text;

    /**
     * Constructor
     *
     * @param title IModel used to represent the title of the tab. Must contain a string
     */
    public TextContentTab(IModel<String> title, IModel<String> text) {
        super(title);

        this.text = text;
    }

    @Override
    public final WebMarkupContainer getPanel(String markupId) {
        return newPanel(markupId, text);
    }

    /**
     * creates a new tab panel.
     *
     * @param markupId The markup id of new tab panel
     * @param text     The text to render
     * @return new tab panel
     */
    protected WebMarkupContainer newPanel(String markupId, IModel<String> text) {
        return new TextPanel(markupId, text);
    }
}
