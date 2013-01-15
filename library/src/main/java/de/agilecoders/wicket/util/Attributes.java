package de.agilecoders.wicket.util;

import com.google.common.collect.Sets;
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

        addClass(tag, Sets.newHashSet(classNames));
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
