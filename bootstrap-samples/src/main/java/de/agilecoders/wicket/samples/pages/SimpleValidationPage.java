package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation.SimpleMessageValidation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation.TooltipValidation;
import de.agilecoders.wicket.samples.panels.validation.SimpleFormPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Alexey Volkov
 * @since 08.11.14
 */
public class SimpleValidationPage extends BaseValidationPage {

    private static final long serialVersionUID = -5120266468076044285L;
    private final String javaCode =
        "    public MyWebPage(PageParameters parameters) {\n"
        + "        super(parameters);\n"
        + "        SimpleMessageValidation validation = new SimpleMessageValidation();\n"
        + "        add(validation);\n"
        + "    }\n";

    /**
     * @param parameters current page parameters
     */
    public SimpleValidationPage(PageParameters parameters) {
        super(parameters);
        SimpleMessageValidation validation = new SimpleMessageValidation();
        add(validation);
        add(new Label("behavior", Model.of(SimpleMessageValidation.class.getSimpleName())));
        add(new Label("behavior-other", Model.of(TooltipValidation.class.getSimpleName())));
        add(new Code("config", Model.of(javaCode)));
    }

    @Override
    protected SimpleFormPanel newSimpleFormPanel(String wicketId) {
        return super.newSimpleFormPanel(wicketId);
    }

    @Override
    protected WebMarkupContainer newHeader(String wicketId) {
        WebMarkupContainer container = super.newHeader(wicketId);
        container.add(new Label("type", Model.of("p-tag next to input fields")));
        BookmarkablePageLink link = new BookmarkablePageLink("link", TooltipValidationPage.class);
        link.add(new Label("other", "tooltip validation"));
        container.add(link);
        return container;
    }
}
