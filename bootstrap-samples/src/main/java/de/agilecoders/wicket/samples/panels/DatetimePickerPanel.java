package de.agilecoders.wicket.samples.panels;

import java.util.Date;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerWithIcon;

/**
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class DatetimePickerPanel extends Panel {

    private static final long serialVersionUID = -3234694041469981384L;

    private Date date;

    /**
     * @param id     wicket id
     * @param config config
     */
    public DatetimePickerPanel(String id, DatetimePickerConfig config) {
        super(id);

        add(
            new DatetimePicker("input", PropertyModel.<Date>of(this, "date"), config),
            new DatetimePickerWithIcon("with-icon", PropertyModel.<Date>of(this, "date"), config)
        );
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
