package de.agilecoders.wicket.examples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.FluidRow;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.HeadingRow;
import de.agilecoders.wicket.examples.ui.demo.buttons.ButtonsSpan;
import de.agilecoders.wicket.examples.ui.demo.buttons.DropDownButtonRow;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ButtonsPage extends BootstrapBasePage {
    private static final Logger LOG = LoggerFactory.getLogger(ButtonsPage.class);

    public ButtonsPage(PageParameters parameters) {
        super(parameters);

        FluidRow row = new FluidRow();

        for (ButtonType type : ButtonType.values()) {
            row.addSpan(new ButtonsSpan(Model.of(type)).width(2));
        }

        getLayout().addRow(row, new HeadingRow(Model.of("Dropdown")), new DropDownButtonRow());
    }
}
