package de.agilecoders.wicket.markup.html.bootstrap.layout;

import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FixedLayout extends AbstractLayout {

    public FixedLayout(String id) {
        super(id);
    }

    public FixedLayout(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected Layout newLayout() {
        return Layout.Fixed;
    }

}
