package de.agilecoders.wicket.extensions.markup.html.bootstrap.table;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

class DemoProvider extends SortableDataProvider<DemoType, String> {

    private static final long serialVersionUID = 1L;

    final List<DemoType> list = Arrays.asList(new DemoType(1, "foo"), new DemoType(2, "bar"), new DemoType(3, "baz"));

    // ignoring first/count
    @Override
    public Iterator<? extends DemoType> iterator(long first, long count) {
        return list.iterator();
    }

    @Override
    public long size() {
        return list.size();
    }

    @Override
    public IModel<DemoType> model(DemoType o) {
        return Model.of(o);
    }

}
