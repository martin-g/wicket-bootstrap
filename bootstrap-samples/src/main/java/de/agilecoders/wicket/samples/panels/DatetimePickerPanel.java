package de.agilecoders.wicket.samples.panels;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerWithIcon;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.Date;

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
