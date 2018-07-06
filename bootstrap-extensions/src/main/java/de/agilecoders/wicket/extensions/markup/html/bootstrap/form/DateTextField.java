package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;

/**
 * A TextField that is mapped to a <code>java.util.Date</code> object.
 * <p/>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 * @author Urs Joss
 */
public class DateTextField extends
    AbstractDateTextField<Date, org.apache.wicket.extensions.markup.html.form.DateTextField, DateTime, DateTextFieldConfig, DateTextField> {

    private static final long serialVersionUID = 3499287675713818823L;

    /**
     * @param markupId
     *     The id of the text field
     */
    public DateTextField(final String markupId) {
        this(markupId, new DateTextFieldConfig());
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param datePattern
     *     The format of the date
     */
    public DateTextField(final String markupId, final String datePattern) {
        this(markupId, new DateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     */
    public DateTextField(final String markupId, final IModel<Date> model) {
        this(markupId, model, new DateTextFieldConfig());
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     * @param dateFormat
     *     The format of the date
     */
    public DateTextField(final String markupId, final IModel<Date> model, final String dateFormat) {
        this(markupId, model, new DateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param model
     *     The date model
     * @param config
     *     The configuration of this field
     */
    public DateTextField(final String markupId, final IModel<Date> model, final DateTextFieldConfig config) {
        super(new org.apache.wicket.extensions.markup.html.form.DateTextField(markupId, model, config.getFormat()),
            Date.class, config);
    }

    /**
     * @param markupId
     *     The id of the text field
     * @param config
     *     The configuration of this field
     */
    public DateTextField(final String markupId, final DateTextFieldConfig config) {
        super(new org.apache.wicket.extensions.markup.html.form.DateTextField(markupId, config.getFormat()), Date.class,
            config);
    }

    /**
     * Ajax event handler
     */
    public interface IAjaxEventHandler extends IParentAjaxEventHandler<Date> {
        void onAjaxEvent(AjaxRequestTarget target, Date date, Event event);
    }
}
