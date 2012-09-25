package de.agilecoders.wicket.samples.components.basecss;

import de.agilecoders.wicket.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextField;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;

import java.util.Date;

/**
 * DatePicker in modal dialog demo
 *
 * @author miha
 * @version 1.0
 */
public class DatePickerModal extends Modal {
    private final DateTextField dateTextField;

    public DatePickerModal(final String markupId) {
        super(markupId);

        add(dateTextField = new DateTextField("date", Model.of(new Date(System.currentTimeMillis())), "MM/dd/yyyy"));

        show(true);
        setFooterVisible(true);
        addCloseButton();
        header(Model.of("DatePicker"));
        setUseCloseHandler(true);
    }

    @Override
    protected void onClose(AjaxRequestTarget target) {
        super.onClose(target);

        target.appendJavaScript("$('#" + dateTextField.getMarkupId() + "').hide();");
    }
}
