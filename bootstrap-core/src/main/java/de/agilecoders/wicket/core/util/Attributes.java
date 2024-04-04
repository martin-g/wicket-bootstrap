package de.agilecoders.wicket.core.util;

import java.util.Set;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.jquery.util.Generics2;

/**
 * #### Description
 * <p/>
 * Helper class to interact with {@link org.apache.wicket.markup.ComponentTag} attributes.
 */
public final class Attributes {

    /**
     * #### Description
     * <p/>
     * adds a list of css class names to given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.addClass(tag, "my-class-a", "my-class-b"); // = <tag class="my-class-a my-class-b"></tag>
     * ```
     *
     * @param tag        The tag
     * @param classNames The class names to add
     * @throws java.lang.IllegalArgumentException if given component tag or class names are null
     */
    public static void addClass(final ComponentTag tag, final String... classNames) {
        Args.notNull(classNames, "classNames");
        Args.notNull(tag, "tag");

        addClass(tag, Generics2.newHashSet(classNames));
    }

    /**
     * #### Description
     * <p/>
     * adds a list of css class names to given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.addClass(tag, Set("my-class-a", "my-class-b")); // = <tag class="my-class-a my-class-b"></tag>
     * ```
     *
     * @param tag        The tag
     * @param classNames The class names to add
     * @throws java.lang.IllegalArgumentException if given component tag or class names are null
     */
    public static void addClass(final ComponentTag tag, final Set<String> classNames) {
        Args.notNull(classNames, "classNames");
        Args.notNull(tag, "tag");

        final String classValue = CssClassNames.parse(tag.getAttribute("class")).add(classNames).asString();

        set(tag, "class", classValue);
    }

    /**
     * #### Description
     * <p/>
     * adds a css class to given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.addClass(tag, new ICssClassNameProvider() {
     *     public String cssClassName() {
     *          return "my-class-a";
     *     }
     * }); // = <tag class="my-class-a"></tag>
     * ```
     *
     * @param tag      The tag
     * @param provider Provider that provides a class name to add
     * @throws java.lang.IllegalArgumentException if given component tag is null
     */
    public static void addClass(final ComponentTag tag, final ICssClassNameProvider provider) {
        if (provider != null) {
            addClass(tag, provider.cssClassName());
        }
    }

    /**
     * #### Description
     * <p/>
     * removes a css class name from a given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.removeClass(tag, Set("my-class-a", "my-class-b"));
     * ```
     *
     * @param tag        The tag
     * @param classNames The class names to remove
     * @throws java.lang.IllegalArgumentException if given component tag or class names are null
     */
    public static void removeClass(final ComponentTag tag, final Set<String> classNames) {
        Args.notNull(classNames, "classNames");
        Args.notNull(tag, "tag");

        final String classValue = CssClassNames.parse(tag.getAttribute("class")).remove(classNames).asString();

        set(tag, "class", classValue);
    }

    /**
     * #### Description
     * <p/>
     * removes a css class name from a given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.removeClass(tag, "my-class-a", "my-class-b");
     * ```
     *
     * @param tag        The tag
     * @param classNames The class names to remove
     * @throws java.lang.IllegalArgumentException if given component tag or class names are null
     */
    public static void removeClass(final ComponentTag tag, final String... classNames) {
        Args.notNull(classNames, "classNames");

        removeClass(tag, Set.of(classNames));
    }

    /**
     * #### Description
     * <p/>
     * removes a css class name from a given tag
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.removeClass(tag, new ICssClassNameProvider() {
     *     public String cssClassName() {
     *         return "my-class-a";
     *     }
     * });
     * ```
     *
     * @param tag      The tag
     * @param provider Provider that provides a class name to remove
     * @throws java.lang.IllegalArgumentException if given component tag is null
     */
    public static void removeClass(final ComponentTag tag, final ICssClassNameProvider provider) {
        if (provider != null) {
            removeClass(tag, provider.cssClassName());
        }
    }

    /**
     * #### Description
     * <p/>
     * sets a new attribute to given tag, if value is null, the attribute will be removed from given
     * tag.
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Attributes.set(tag, "data-custom", "value"); // = <tag data-custom="value"></tag>
     * Attributes.set(tag, "data-custom", null); // = <tag></tag>
     * ```
     *
     * @param tag           The tag
     * @param attributeName attribute name
     * @param value         attribute value
     * @throws java.lang.IllegalArgumentException if given component tag or attribute name is null
     */
    public static void set(final ComponentTag tag, final String attributeName, final String value) {
        Args.notNull(attributeName, "key");
        Args.notNull(tag, "tag");

        if (!Strings.isEmpty(value)) {
            tag.put(attributeName, value);
        } else {
            tag.remove(attributeName);
        }
    }

    /**
     * private constructor.
     */
    private Attributes() {
        throw new UnsupportedOperationException();
    }
}
