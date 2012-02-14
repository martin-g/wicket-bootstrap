package de.agilecoders.wicket.markup.html.bootstrap.layout.row;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Row extends AbstractRow {

    public Row() {
        super();
    }

    public Row(IModel<?> model) {
        super(model);
    }

    @Override
    protected String newCssClassName() {
        return "row";
    }
}
