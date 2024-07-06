package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * Use {@link TableContextBehavior} to color table rows or individual cells of a html table.
 *
 * documentation: http://getbootstrap.com/css/#tables-contextual-classes
 *
 * @author Eric Hamel eric.hamel@me.com
 *
 */
public class TableContextBehavior extends Behavior{

    private final IModel<TableContextType> type;

    /**
     * Construct.
     *
     * Default sets background as active.
     */
    public TableContextBehavior() {
        this(TableContextType.Active);
    }

    /**
     * Construct.
     *
     * @param type The type of the table context
     */
    public TableContextBehavior(final TableContextType type) {
        this(Model.of(type));
    }

    /**
     * Construct.
     *
     * @param type The type of the table context as model
     */
    public TableContextBehavior(final IModel<TableContextType> type) {
        super();

        this.type = type;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "tr", "th", "td");
        Attributes.addClass(tag, getType().cssClassName());
    }

    /**
     * @return current table context type
     */
    public final TableContextType getType() {
        return type.getObject();
    }

    /**
     * sets the table context type
     *
     * @param type the table context type
     * @return this instance
     */
    public final TableContextBehavior setType(final TableContextType type) {
        this.type.setObject(type);

        return this;
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }

}
