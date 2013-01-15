package de.agilecoders.wicket.util;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.string.Strings;

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
        final String classValue = CssClassNames.parse(tag.getAttribute("class")).add(classNames).asString();

        if (!Strings.isEmpty(classValue)) {
            tag.put("class", classValue);
        }
    }

    /**
     * private constructor.
     */
    private Attributes() {
        throw new UnsupportedOperationException();
    }
}
