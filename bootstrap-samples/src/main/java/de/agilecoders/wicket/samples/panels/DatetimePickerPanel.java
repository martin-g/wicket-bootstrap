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
        Form<Object> form = new Form<Object>("form");
        add(
            form.add(
                new DatetimePicker("input", PropertyModel.<Date>of(this, "date"), config),
                new DatetimePickerWithIcon("with-icon", PropertyModel.<Date>of(this, "date"), config),
                new BootstrapAjaxButton("submit", Model.of("Submit"), form, Buttons.Type.Default) {

                    private static final long serialVersionUID = 3921151963195781052L;

                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        super.onSubmit(target, form);
                        target.add(form);
                    }

                    @Override
                    protected void onError(AjaxRequestTarget target, Form<?> form) {
                        super.onError(target, form);
                        target.add(form);
                    }
                }
            )
        );
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
