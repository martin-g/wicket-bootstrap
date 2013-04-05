package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.Set;

/**
 * Helper class.
 *
 * @author miha
 */
public final class Attributes {

    /**
     * adds a css class name to given tag
     *
     * @param tag        The tag
     * @param classNames The class names to add
     */
    public static void addClass(final ComponentTag tag, final String... classNames) {
        Args.notNull(classNames, "classNames");

        addClass(tag, Generics2.newHashSet(classNames));
    }

    /**
     * adds a css class name to given tag
     *
     * @param tag        The tag
     * @param classNames The class names to add
     */
    public static void addClass(final ComponentTag tag, final Set<String> classNames) {
        Args.notNull(classNames, "classNames");

        final String classValue = CssClassNames.parse(tag.getAttribute("class")).add(classNames).asString();

        set(tag, "class", classValue);
    }

    /**
     * adds a css class name to given tag
     *
     * @param tag      The tag
     * @param provider Provider that provides a class name to add
     */
    public static void addClass(final ComponentTag tag, final ICssClassNameProvider provider) {
        if (provider != null) {
            addClass(tag, provider.cssClassName());
        }
    }

    /**
     * private constructor.
     */
    private Attributes() {
        throw new UnsupportedOperationException();
    }

    /**
     * sets a new attribute to given tag
     *
     * @param tag   The tag
     * @param key   attribute name
     * @param value attribute value
     */
    public static void set(final ComponentTag tag, final String key, final String value) {
        Args.notNull(key, "key");

        if (!Strings.isEmpty(value)) {
            tag.put(key, value);
        } else {
            tag.remove(key);
        }
    }
}
