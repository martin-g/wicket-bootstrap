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
 * Validation tooltip page
 *
 * @author Alexey Volkov
 * @since 08.11.14
 */
public class TooltipValidationPage extends BaseValidationPage {

    private static final long serialVersionUID = 1L;

    private final String javaCode =
        "    public MyWebPage(PageParameters parameters) {\n"
            + "        super(parameters);\n"
            + "        TooltipValidation validation = new TooltipValidation();\n"
            + "        add(validation);\n"
            + "    }\n";

    /**
     * @param parameters current page parameters
     */
    public TooltipValidationPage(PageParameters parameters) {
        super(parameters);
        TooltipValidation validation = new TooltipValidation();
        add(validation);
        add(new Label("behavior", Model.of(TooltipValidation.class.getSimpleName())));
        add(new Label("behavior-other", Model.of(SimpleMessageValidation.class.getSimpleName())));
        add(new Code("config", Model.of(javaCode)));

    }

    @Override
    protected SimpleFormPanel newSimpleFormPanel(String wicketId) {
        return super.newSimpleFormPanel(wicketId);
    }


    @Override
    protected WebMarkupContainer newHeader(String wicketId) {
        WebMarkupContainer container = super.newHeader(wicketId);
        container.add(new Label("type", Model.of("tooltips")));
        BookmarkablePageLink link = new BookmarkablePageLink("link", SimpleValidationPage.class);
        link.add(new Label("other", "simple validation"));
        container.add(link);
        return container;
    }

}
