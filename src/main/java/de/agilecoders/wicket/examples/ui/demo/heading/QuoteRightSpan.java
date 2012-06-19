package de.agilecoders.wicket.examples.ui.demo.heading;

import de.agilecoders.wicket.markup.html.bootstrap.block.Cite;
import de.agilecoders.wicket.markup.html.bootstrap.block.Quote;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class QuoteRightSpan extends Span {
    public QuoteRightSpan() {
        Quote quote = new Quote("quote").pullRight();
        quote.add(new Cite("cite"));

        add(quote);
    }
}
