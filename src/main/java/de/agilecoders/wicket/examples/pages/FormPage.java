package de.agilecoders.wicket.examples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.layout.row.HeadingRow;
import de.agilecoders.wicket.examples.ui.demo.form.HorizontalFormRow;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FormPage extends BootstrapBasePage {
    public FormPage(PageParameters parameters) {
        super(parameters);

        addRow(new HeadingRow(Model.of("Horizontal Form")), new HorizontalFormRow());
    }
}
