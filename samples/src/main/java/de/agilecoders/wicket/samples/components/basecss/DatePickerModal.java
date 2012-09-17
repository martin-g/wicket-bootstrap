package de.agilecoders.wicket.samples.components.basecss;

import de.agilecoders.wicket.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextField;
import org.apache.wicket.model.Model;

/**
 * DatePicker in modal dialog demo
 *
 * @author miha
 * @version 1.0
 */
public class DatePickerModal extends Modal {
    public DatePickerModal(final String markupId) {
        super(markupId);

        add(new DateTextField("date"));

        show(true);
        setFooterVisible(true);
        addCloseButton();
        header(Model.of("DatePicker"));
    }
}
