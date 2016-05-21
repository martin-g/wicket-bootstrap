package de.agilecoders.wicket.core.markup.html.bootstrap.list;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * A special list view that is styled by bootstrap.
 *
 * @author miha
 */
public abstract class BootstrapListView<T> extends ListView<T> {

    private ListBehavior listBehavior;

    /**
     * Construct.
     *
     * @param id the markup id
     */
    public BootstrapListView(final String id) {
        this(id, (List<T>) null);
    }

    /**
     * Construct.
     *
     * @param id    the markup id
     * @param model the list model
     */
    public BootstrapListView(String id, IModel<? extends List<T>> model) {
        super(id, model);

        add(listBehavior = new ListBehavior());
    }

    /**
     * Construct.
     *
     * @param id       the markup id
     * @param listData the list data
     */
    public BootstrapListView(String id, List<T> listData) {
        this(id, Model.ofList(listData));
    }

    /**
     * whether to us styles or not for ul tags.
     *
     * @return this instance for chaining
     */
    public BootstrapListView<T> unstyled() {
        listBehavior.unstyled();
        return this;
    }

    /**
     * sets a special css class so that this list will be rendered horizontally.
     *
     * @return this instance for chaining.
     */
    public BootstrapListView<T> horizontal() {
        listBehavior.horizontal();
        return this;
    }
}
