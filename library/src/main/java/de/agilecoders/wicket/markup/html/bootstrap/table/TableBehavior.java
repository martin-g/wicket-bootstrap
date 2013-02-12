package de.agilecoders.wicket.markup.html.bootstrap.table;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.util.Components;
import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class TableBehavior extends BootstrapBaseBehavior {

    private enum Type implements ICssClassNameProvider {
        Basic, Bordered, Zebra, Condensed;

        @Override
        public String cssClassName() {
            return equals(Basic) ? "table" : "table-" + name().toLowerCase();
        }

    }

    private List<Type> types = Generics2.newArrayList(Type.Basic);

    public TableBehavior striped() {
        return addType(Type.Zebra);
    }

    public TableBehavior condensed() {
        return addType(Type.Condensed);
    }

    public TableBehavior bordered() {
        return addType(Type.Bordered);
    }

    private TableBehavior addType(Type type) {
        if (!types.contains(type)) {
            types.add(type);
        }

        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new CssClassNameAppender(createCssClassNames()));
    }

    private List<String> createCssClassNames() {
        return Generics2.transform(types, new Generics2.Function<Type, String>() {
            @Override
            public String apply(Type input) {
                return input != null ? input.cssClassName() : "";
            }
        });
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "table");
    }
}
