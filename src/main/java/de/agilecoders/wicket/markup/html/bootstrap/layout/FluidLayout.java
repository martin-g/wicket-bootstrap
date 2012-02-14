package de.agilecoders.wicket.markup.html.bootstrap.layout;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FluidLayout extends AbstractLayout {

    public FluidLayout(String id) {
        super(id);
    }

    public FluidLayout(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected String newCssClassName() {
        return "container-fluid";
    }

}
