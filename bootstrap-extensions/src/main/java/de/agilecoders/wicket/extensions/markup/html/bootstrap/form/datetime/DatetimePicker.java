package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;

import java.util.Date;

/**
 * DateTime input field with a Eonasdan datetimepicker plugin.
 *
 * @author Alexey Volkov
 * @since 01.02.2015
 */
public class DatetimePicker extends DateTextField {

    private static final long serialVersionUID = 1L;
    private DatetimePickerConfig config;

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
        super(id, model, datePattern);
        config = new DatetimePickerConfig().withFormat(datePattern);
    }

    /**
     * Construct.
     *
     * @param id     wicket id
     * @param config config
     */
    public DatetimePicker(String id, DatetimePickerConfig config) {
        this(id, null, config.getFormat());
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
        this(id, model, config.getFormat());
        this.config = config;
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);
    	String scriptTemplate = "$('#%1$s').on('dp.change', function(){ $('#%1$s').trigger('change'); })";
    	response.render(OnDomReadyHeaderItem.forScript(String.format(scriptTemplate, getMarkupId())));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new DatetimePickerBehavior(config));
        if (config.getMaskInput()) {
            add(config.newMaskBehavior());
        }
    }

}
