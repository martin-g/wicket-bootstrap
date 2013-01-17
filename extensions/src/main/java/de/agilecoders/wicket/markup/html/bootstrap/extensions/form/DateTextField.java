package de.agilecoders.wicket.markup.html.bootstrap.extensions.form;

import de.agilecoders.wicket.markup.html.bootstrap.extensions.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.references.BootstrapDatepickerLangJsReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.references.BootstrapDatepickerReference;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;

import java.util.Date;

import static de.agilecoders.wicket.util.JQuery.$;

/**
 * A TextField that is mapped to a <code>java.util.Date</code> object.
 * <p/>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 */
public class DateTextField extends org.apache.wicket.extensions.markup.html.form.DateTextField {
    private static final long serialVersionUID = 3499287675713818823L;

    private final DateTextFieldConfig config;

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     */
    public DateTextField(final String markupId) {
        this(markupId, new DateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId    The id of the text field
     * @param datePattern The format of the date
     */
    public DateTextField(final String markupId, final String datePattern) {
        this(markupId, new DateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     */
    public DateTextField(final String markupId, final IModel<Date> model) {
        this(markupId, model, new DateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId   The id of the text field
     * @param model      The date model
     * @param dateFormat The format of the date
     */
    public DateTextField(final String markupId, final IModel<Date> model, final String dateFormat) {
        this(markupId, model, new DateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     * @param config   The configuration of this field
     */
    public DateTextField(final String markupId, final IModel<Date> model, final DateTextFieldConfig config) {
        super(markupId, model, config.getFormat());

        this.config = config;
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param config   The configuration of this field
     */
    public DateTextField(final String markupId, final DateTextFieldConfig config) {
        super(markupId, config.getFormat());

        this.config = config;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "input");
        Attributes.set(tag, "type", "text");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(BootstrapDatepickerReference.INSTANCE));

        if (!config.isDefaultLanguageSet()) {
            response.render(JavaScriptHeaderItem.forReference(new BootstrapDatepickerLangJsReference(config.getLanguage())));
        } else {
            response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        }

        response.render(OnDomReadyHeaderItem.forScript(createScript(config)));
    }

    /**
     * creates the initializer script.
     *
     * @return initializer script
     */
    protected CharSequence createScript(final DateTextFieldConfig config) {
        return $(this).chain("datepicker", config).get();
    }

}
