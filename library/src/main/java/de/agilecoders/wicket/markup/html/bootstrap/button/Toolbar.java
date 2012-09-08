package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Toolbar extends WebMarkupContainer {

    public Toolbar(String id) {
        this(id, null);
    }

    public Toolbar(String id, IModel<?> model) {
        super(id, model);

        add(new ToolbarBehavior());
    }
}
