package de.agilecoders.wicket.samples.components.basecss;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;

import org.apache.wicket.model.Model;

import java.util.Date;

/**
 * DatePicker in modal dialog demo
 *
 * @author miha
 * @version 1.0
 */
public class DatePickerModal extends Modal<Void> {

    public DatePickerModal(final String markupId) {
        super(markupId);

        add(new DateTextField("date", Model.of(new Date(System.currentTimeMillis())), "MM/dd/yyyy"));

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        show(true);
        setFooterVisible(true);
        addCloseButton();
        header(Model.of("DatePicker"));
        setUseCloseHandler(true);
    }
}
