package de.agilecoders.wicket.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;

import java.util.Set;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public final class Components {

    public static void assertTag(final Component component, final ComponentTag tag, final String... tagNames) {
        assertTag(component, tag, Sets.newHashSet(tagNames));
    }

    public static void assertTag(Component component, ComponentTag tag, Set<String> tagNames) {
        boolean found = false;
        for (String tagName : tagNames) {
            if (tag.getName().equalsIgnoreCase(tagName)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw createMarkupException(component, tag, tagNames);
        }
    }

    private static MarkupException createMarkupException(Component component, ComponentTag tag, Set<String> tagNames) {
        String msg = String.format("Component [%s] (path = [%s]) must be applied to a tag of type [%s], not: %s",
                                   component.getId(), component.getPath(), Joiner.on(',').join(tagNames), tag.toUserDebugString());

        throw new MarkupException(component.getMarkup().getMarkupResourceStream(), msg);
    }
}
