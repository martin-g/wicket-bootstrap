package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * {@link de.agilecoders.wicket.core.markup.html.bootstrap.components.PopoverBehavior} configuration
 *
 * @author miha
 */
public class DraggableConfig extends AbstractConfig {

    /**
     * The CSS cursor during the drag operation.
     */
    private static final IKey<String> Cursor = newKey("cursor", "");

    /**
     * If specified, restricts dragging from starting unless the mousedown occurs on
     * the specified element(s).
     */
    private static final IKey<String> Handle = newKey("handle", "");

    /**
     * If set to false, will prevent the ui-draggable class from being added.
     * This may be desired as a performance optimization when calling .draggable()
     * on hundreds of elements.
     */
    private static final IKey<Boolean> AddClasses = newKey("addClasses", false);

    /**
     * Constrains dragging to either the horizontal (x) or vertical (y) axis.
     * Possible values: "x", "y".
     */
    private static final IKey<String> Axis = newKey("axis", "");


    /**
     * all possible axis
     */
    public enum Axis {
        X, Y, All;

        /**
         * @return the string value of this enum
         */
        public String value() {
            return equals(All) ? "" : name().toLowerCase();
        }
    }

    /**
     * Constrains dragging to either the horizontal (x) or vertical (y) axis. Possible values: "x", "y".
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public DraggableConfig Axis(final Axis value) {
        put(Axis, value.value());
        return this;
    }

    /**
     * If set to false, will prevent the ui-draggable class from being added.
     * This may be desired as a performance optimization when calling .draggable() on hundreds of elements.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public DraggableConfig addClasses(final boolean value) {
        put(AddClasses, value);
        return this;
    }

    /**
     * The CSS cursor during the drag operation.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public DraggableConfig withCursor(final String value) {
        put(Cursor, value);
        return this;
    }

    /**
     * If specified, restricts dragging from starting unless the mousedown occurs on
     * the specified element(s).
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public DraggableConfig withHandle(final String value) {
        put(Handle, value);
        return this;
    }

}
