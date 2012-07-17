package de.agilecoders.wicket.markup.html.bootstrap.list;

import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class BootstrapListView<T> extends org.apache.wicket.markup.html.list.ListView<T> {

    private ListBehavior listBehavior;

    public BootstrapListView(String id) {
        super(id);

        commonInit();
    }

    public BootstrapListView(String id, IModel<? extends List<? extends T>> iModel) {
        super(id, iModel);

        commonInit();
    }

    public BootstrapListView(String id, List<? extends T> ts) {
        super(id, ts);

        commonInit();
    }

    private void commonInit() {
        listBehavior = new ListBehavior();
        add(listBehavior);
    }

    public BootstrapListView<T> unstyled() {
        listBehavior.unstyled();
        return this;
    }
}
