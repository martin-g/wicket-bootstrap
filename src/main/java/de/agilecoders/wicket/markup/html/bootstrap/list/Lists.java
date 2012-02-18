package de.agilecoders.wicket.markup.html.bootstrap.list;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class Lists<T> extends org.apache.wicket.markup.html.list.ListView<T> {

    private boolean unstyled = false;

    public Lists(String componentId) {
        super(componentId);
    }

    public Lists(String componentId, IModel<? extends List<? extends T>> model) {
        super(componentId, model);
    }

    public Lists(String componentId, List<T> list) {
        super(componentId, list);
    }

    public Lists<T> unstyled() {
        unstyled = true;

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (unstyled) {
            add(new CssClassNameAppender("unstyled"));
        }
    }


}
