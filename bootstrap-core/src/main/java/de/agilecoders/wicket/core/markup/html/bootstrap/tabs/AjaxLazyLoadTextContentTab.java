package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * This {@link TextContentTab} implementation loads all text content after domready on
 * client side. This tab should be used with a {@link org.apache.wicket.model.LoadableDetachableModel} and
 * huge amount of text else you should prefer the {@link TextContentTab}.
 *
 * @author miha
 * @version 1.0
 */
public class AjaxLazyLoadTextContentTab extends TextContentTab {

    /**
     * Constructor
     *
     * @param title IModel used to represent the title of the tab. Must contain a string
     */
    public AjaxLazyLoadTextContentTab(IModel<String> title, IModel<String> text) {
        super(title, text);
    }

    @Override
    protected WebMarkupContainer newPanel(final String markupId, final IModel<String> text) {
        return new AjaxLazyLoadPanel(markupId) {
            @Override
            public Component getLazyLoadComponent(String markupId) {
                return new TextPanel(markupId, text);
            }
        };
    }
}
