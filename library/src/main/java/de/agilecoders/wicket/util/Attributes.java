package de.agilecoders.wicket.util;

import org.apache.wicket.markup.ComponentTag;

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
        tag.put("class", CssClassNames.parse(tag.getAttribute("class")).add(classNames).asString());
    }

    /**
     * private constructor.
     */
    private Attributes() {
        throw new UnsupportedOperationException();
    }
}
