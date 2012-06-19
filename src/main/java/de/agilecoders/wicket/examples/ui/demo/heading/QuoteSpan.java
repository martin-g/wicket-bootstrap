package de.agilecoders.wicket.examples.ui.demo.heading;

import de.agilecoders.wicket.markup.html.bootstrap.block.Cite;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class QuoteSpan extends Span {
    public QuoteSpan() {
        add(new Cite("cite"));
    }
}
