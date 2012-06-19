package de.agilecoders.wicket.examples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.HeadingRow;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.Row;
import de.agilecoders.wicket.examples.ui.demo.heading.CodeSpan;
import de.agilecoders.wicket.examples.ui.demo.heading.HeadingSpan;
import de.agilecoders.wicket.examples.ui.demo.heading.ListRow;
import de.agilecoders.wicket.examples.ui.demo.heading.QuoteRightSpan;
import de.agilecoders.wicket.examples.ui.demo.heading.QuoteSpan;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class HeadingPage extends BootstrapBasePage {
    public HeadingPage(PageParameters parameters) {
        super(parameters);

        Span element = new HeadingSpan().width(8);
        Row row = new Row();
        row.addSpan(element);
        
        Span quoteSpan = new QuoteSpan().width(6);
        Span quoteRightSpan = new QuoteRightSpan().width(6);
        Row quoteRow = new Row();
        quoteRow.addSpan(quoteSpan, quoteRightSpan);
        
        Row listRow = new ListRow();

        getLayout().addRow(row, new HeadingRow(Model.of("Quote")), quoteRow, new HeadingRow(Model.of("Lists")), listRow,
                           new HeadingRow(Model.of("Code")), new CodeSpan());
    }
}
