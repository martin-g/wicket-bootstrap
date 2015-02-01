package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.model.IModel;

import java.util.Date;

/**
 * DateTime input field with a Eonasdan datetimepicker plugin.
 *
 * @author Alexey Volkov
 * @since 01.02.2015
 */
public class DatetimePicker extends DateTextField {

    private static final long serialVersionUID = -9043590922065213472L;
    private DatetimePickerConfig config = newDefaultConfig();

    /**
     * Construct.
     *
     * @param id wicket id
     */
    public DatetimePicker(String id) {
        super(id);
    }

    /**
     * Construct.
     *
     * @param id    wicket id
     * @param model model
     */
    public DatetimePicker(String id, IModel<Date> model) {
        super(id, model);
    }

    /**
     * Construct.
     *
     * @param id          wicket id
     * @param datePattern datetime pattern
     */
    public DatetimePicker(String id, String datePattern) {
        this(id, null, datePattern);
    }

    /**
     * Construct.
     *
     * @param id          wicket id
     * @param model       model
     * @param datePattern datetime pattern
     */
    public DatetimePicker(String id, IModel<Date> model, String datePattern) {
        this(id, model);
        config.withFormat(datePattern);
    }

    /**
     * Construct.
     *
     * @param id     wicket id
     * @param config config
     */
    public DatetimePicker(String id, DatetimePickerConfig config) {
        this(id);
        this.config = config;
    }

    /**
     * Construct.
     *
     * @param id     wicket id
     * @param model  model
     * @param config config
     */
    public DatetimePicker(String id, IModel<Date> model, DatetimePickerConfig config) {
        super(id, model, config.getFormat());
        this.config = config;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        //        BootstrapResourcesBehavior.addTo(this);
        add(new DatetimePickerBehavior(config));
        if (config.getMaskInput()) {
            add(config.newMaskBehavior());
        }
    }

    /**
     * @return new default config
     */
    protected DatetimePickerConfig newDefaultConfig() {
        return new DatetimePickerConfig();
    }

}
