package de.agilecoders.wicket.samples.panels;

import java.time.LocalDateTime;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.AbstractDateTimePickerWithIcon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.LocalDateTimePicker;

/**
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class LocalDateTimePickerPanel extends AbstractDatetimePickerPanel<LocalDateTime> {
    private static final long serialVersionUID = 1L;

    /**
     * @param id     wicket id
     * @param config config
     */
    public LocalDateTimePickerPanel(String id, DatetimePickerConfig config) {
        super(id, config);
    }

    @Override
    protected Component newInput(String id, IModel<LocalDateTime> model, DatetimePickerConfig config) {
        return new LocalDateTimePicker(id, model, config);
    }

    @Override
    protected Component newInputWithIcon(String id, IModel<LocalDateTime> model, DatetimePickerConfig config) {
        return new AbstractDateTimePickerWithIcon<>(id, model, config) {
            private static final long serialVersionUID = 1L;

            @Override
            protected FormComponent<LocalDateTime> newInput(String wicketId, String dateFormat) {
                return new LocalDateTimeTextField(wicketId, getModel(), dateFormat);
            }
        };
    }
}
