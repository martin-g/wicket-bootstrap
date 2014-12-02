package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.samples.panels.validation.SimpleFormModal;
import de.agilecoders.wicket.samples.panels.validation.SimpleFormPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Alexey Volkov
 * @since 08.11.14
 */
public abstract class BaseValidationPage extends BasePage {

    private static final long serialVersionUID = 8101772374853082900L;

    /**
     * @param parameters current page parameters
     */
    public BaseValidationPage(PageParameters parameters) {
        super(parameters);
        add(newHeader("header"));
        add(new SimpleFormModal("modal", newSimpleFormPanel("content")));
        add(newSimpleFormPanel("form"));
        add(newSimpleFormPanel("form-ajax").withAjax());
    }

    protected SimpleFormPanel newSimpleFormPanel(String wicketId) {
        return new SimpleFormPanel(wicketId);
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }

    protected WebMarkupContainer newHeader(String wicketId){
        return new WebMarkupContainer(wicketId);
    }
}

