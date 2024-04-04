package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.Set;

/**
 * Helper class for components.
 */
public final class Components {

    /**
     * Construct.
     */
    private Components() {
        throw new UnsupportedOperationException();
    }

    /**
     * checks if given tag has one of given tag names.
     *
     * @param tag      The component tag
     * @param tagNames the names that at least one must match
     * @return true if given tag has one of given tag names.
     */
    public static boolean hasTagName(final ComponentTag tag, String... tagNames) {
        return hasTagName(tag, Generics2.newHashSet(tagNames));
    }

    /**
     * checks if given tag has one of given tag names.
     *
     * @param tag      The component tag
     * @param tagNames the names that at least one must match
     * @return true if given tag has one of given tag names.
     */
    public static boolean hasTagName(final ComponentTag tag, Set<? extends String> tagNames) {
        Args.notNull(tag, "tag");

        if (tagNames != null) {
            for (String tagName : tagNames) {
                if (tag.getName().equalsIgnoreCase(tagName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if given tag has one of given tag names else a {@link MarkupException} will be thrown.
     *
     * @param component The component
     * @param tag       The component tag
     * @param tagNames  the names that at least one must match
     * @throws MarkupException if given tag has none of given tag names
     */
    public static void assertTag(final Component component, final ComponentTag tag, final String... tagNames) {
        assertTag(component, tag, Generics2.newHashSet(tagNames));
    }

    /**
     * checks if given tag has one of given tag names else a {@link MarkupException} will be thrown.
     *
     * @param component The component
     * @param tag       The component tag
     * @param tagNames  the names that at least one must match
     * @throws MarkupException if given tag has none of given tag names
     */
    public static void assertTag(Component component, ComponentTag tag, Set<? extends String> tagNames) {
        Args.notNull(component, "component");

        if (!hasTagName(tag, tagNames)) {
            throw createMarkupException(component, tag, tagNames);
        }
    }

    /**
     * Creates a new {@link MarkupException} instance with given values as message.
     *
     * @param component The component
     * @param tag       The component tag
     * @param tagNames  the names that at least one must match
     * @return new {@link MarkupException}
     */
    private static MarkupException createMarkupException(final Component component, final ComponentTag tag, final Set<? extends String> tagNames) {
        String msg = String.format("Component [%s] (path = [%s]) must be applied to a tag of type [%s], not: %s",
                                   component.getId(), component.getPath(), Generics2.join(tagNames, ','), tag.toUserDebugString());

        throw new MarkupException(component.getMarkup().getMarkupResourceStream(), msg);
    }

    /**
     * checks given component' default model, if it's empty or null the component will be hidden.
     *
     * @param component component to check
     */
    public static void hideIfModelIsEmpty(final Component component) {
        if (component != null && Strings.isEmpty(component.getDefaultModelObjectAsString())) {
            component.setVisible(false);
        }
    }

    /**
     * sets all given components visible
     *
     * @param components The components to show
     */
    public static void show(Component... components) {
        if (components != null) {
            for (Component component : components) {
                component.setVisible(true);
            }
        }
    }

}
