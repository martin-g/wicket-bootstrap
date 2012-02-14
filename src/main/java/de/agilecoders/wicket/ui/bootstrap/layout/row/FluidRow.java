package de.agilecoders.wicket.ui.bootstrap.layout.row;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FluidRow extends AbstractRow {

    public FluidRow() {
        super();
    }

    public FluidRow(IModel<?> model) {
        super(model);
    }

    @Override
    protected String newCssClassName() {
        return "row-fluid";
    }

}
