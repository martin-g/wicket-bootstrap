package de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Objects;

/**
 * {@link de.agilecoders.wicket.markup.html.bootstrap.components.PopoverBehavior} configuration
 *
 * @author miha
 */
public class DraggableConfig extends AbstractConfig {

    private enum Key implements IKey {

        /**
         * The CSS cursor during the drag operation.
         */
        Cursor("cursor", String.class, ""),

        /**
         * If specified, restricts dragging from starting unless the mousedown occurs on
         * the specified element(s).
         */
        Handle("handle", String.class, ""),

        /**
         * If set to false, will prevent the ui-draggable class from being added.
         * This may be desired as a performance optimization when calling .draggable()
         * on hundreds of elements.
         */
        AddClasses("addClasses", Boolean.class, false),

        /**
         * Constrains dragging to either the horizontal (x) or vertical (y) axis.
         * Possible values: "x", "y".
         */
        Axis("axis", String.class, "");

        private final String key;
        private final Class type;
        private final Object defaultValue;

        /**
         * Construct.
         *
         * @param key          string representation of this key
         * @param type         The object type
         * @param defaultValue The default value
         */
        private Key(final String key, final Class type, final Object defaultValue) {
            this.key = key;
            this.type = type;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void assertCorrectType(final Object value) {
            Preconditions.checkArgument(type.isInstance(value));
        }

        @Override
        public boolean isDefaultValue(final Object value) {
            return Objects.equal(value, defaultValue);
        }

        @Override
        public Object getDefaultValue() {
            return defaultValue;
        }
    }

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
        put(Key.Axis, value.value());
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
        put(Key.AddClasses, value);
        return this;
    }

    /**
     * The CSS cursor during the drag operation.
     *
     * @param value mandatory parameter
     * @return this instance for chaining
     */
    public DraggableConfig withCursor(final String value) {
        put(Key.Cursor, value);
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
        put(Key.Handle, value);
        return this;
    }

}
