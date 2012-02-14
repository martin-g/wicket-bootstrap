package de.agilecoders.wicket.ui.bootstrap.layout;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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
