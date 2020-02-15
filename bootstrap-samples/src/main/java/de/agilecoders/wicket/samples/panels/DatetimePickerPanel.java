package de.agilecoders.wicket.samples.panels;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.AbstractDateTimePickerWithIcon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

/**
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class DatetimePickerPanel extends AbstractDatetimePickerPanel<Date> {
    private static final long serialVersionUID = 1L;

    /**
     * @param id     wicket id
     * @param config config
     */
    public DatetimePickerPanel(String id, DatetimePickerConfig config) {
        super(id, config);
    }

    @Override
    protected Component newInput(String id, IModel<Date> model, DatetimePickerConfig config) {
        return new DatetimePicker(id, model, config);
    }

    @Override
    protected Component newInputWithIcon(String id, IModel<Date> model, DatetimePickerConfig config) {
        return new AbstractDateTimePickerWithIcon<>(id, model, config) {
            private static final long serialVersionUID = 1L;

            @Override
            protected FormComponent<Date> newInput(String wicketId, String dateFormat) {
                return new DateTextField(wicketId, getModel(), dateFormat);
            }
        };
    }
}
