package de.agilecoders.wicket.examples.ui.demo.heading;

import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.Row;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CodeSpan extends Row {
    public CodeSpan() {
        add(new Code("code1").addLineNumbers().language(Code.Language.JAVA));
        add(new Code("code2"));
        add(new Code("code3").addLineNumbers().language(Code.Language.JAVA).from(2));
    }
}
