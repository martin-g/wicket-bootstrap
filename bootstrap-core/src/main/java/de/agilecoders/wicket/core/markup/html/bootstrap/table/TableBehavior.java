package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import com.google.common.base.Function;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.Generics2;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import java.util.Set;

/**
 * A {@link TableBehavior} that is able to style a html table.
 *
 * @author miha
 */
public class TableBehavior extends BootstrapBaseBehavior {

    /**
     * all possible table styles.
     */
    private static enum Type implements ICssClassNameProvider {
        Basic, Bordered, Striped, Condensed, Hover;

        @Override
        public String cssClassName() {
            return equals(Basic) ? "table" : "table-" + name().toLowerCase();
        }

    }

    private Set<Type> types = Generics2.newHashSet(Type.Basic);

    /**
     * adds the "striped" style to table
     *
     * @return this instance for chaining
     */
    public TableBehavior striped() {
        return addType(Type.Striped);
    }

    /**
     * adds the "condensed" style to table
     *
     * @return this instance for chaining
     */
    public TableBehavior condensed() {
        return addType(Type.Condensed);
    }

    /**
     * adds the "bordered" style to table
     *
     * @return this instance for chaining
     */
    public TableBehavior bordered() {
        return addType(Type.Bordered);
    }

    /**
     * adds the "hover" flag to table
     *
     * @return this instance for chaining
     */
    public TableBehavior hover() {
        return addType(Type.Hover);
    }
    
    private TableBehavior addType(Type type) {
        if (!types.contains(type)) {
            types.add(type);
        }

        return this;
    }

    /**
     * @return all needed css class names that were assigned to the type list
     */
    private Set<String> createCssClassNames() {
        return Generics2.transform(types, new Function<Type, String>() {
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

        Attributes.addClass(tag, createCssClassNames());
    }
}
