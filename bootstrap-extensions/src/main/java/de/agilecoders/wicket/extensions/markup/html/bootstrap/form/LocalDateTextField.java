package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * A TextField that is mapped to a {@link java.time.LocalDate} object.
 * <p>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 * @author Urs Joss
 */
public class LocalDateTextField extends
    AbstractDateTextField<LocalDate, org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField, LocalDateTime, LocalDateTextFieldConfig, LocalDateTextField> {

    private static final long serialVersionUID = 3499287675713818823L;

    /**
     * @param markupId
     *     The id of the text field
     */
    public LocalDateTextField(final String markupId) {
        this(markupId, new LocalDateTextFieldConfig());
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param datePattern
     *     The format of the date
     */
    public LocalDateTextField(final String markupId, final String datePattern) {
        this(markupId, new LocalDateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model) {
        this(markupId, model, new LocalDateTextFieldConfig());
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     * @param dateFormat
     *     The format of the date
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model, final String dateFormat) {
        this(markupId, model, new LocalDateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     * @param config
     *     The configuration of this field
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model,
        final LocalDateTextFieldConfig config) {
        super(new org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField(markupId, model,
            config.getFormat()), LocalDate.class, config);
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param config
     *     The configuration of this field
     */
    public LocalDateTextField(final String markupId, final LocalDateTextFieldConfig config) {
        super(
            new org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField(markupId, config.getFormat()),
            LocalDate.class, config);
    }

    /**
     * Ajax event handler
     */
    public interface IAjaxEventHandler extends IParentAjaxEventHandler<LocalDate> {
        void onAjaxEvent(AjaxRequestTarget target, LocalDate date, Event event);
    }
}
