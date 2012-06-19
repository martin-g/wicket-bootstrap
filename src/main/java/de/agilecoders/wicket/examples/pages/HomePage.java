package de.agilecoders.wicket.examples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.FluidRow;
import de.agilecoders.wicket.examples.ui.demo.home.NavigationSpan;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends BootstrapBasePage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        Span element = new NavigationSpan("span").width(8).offset(2);
        FluidRow row = new FluidRow();
        row.addSpan(element);
        getLayout().addRow(row);
    }
}
