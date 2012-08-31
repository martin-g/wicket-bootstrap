package de.agilecoders.wicket.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.model.IModel;

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

    public static void hideIfModelIsEmpty(Component component) {
        if (component != null && (component.getDefaultModel() == null || component.getDefaultModelObject() == null ||
                                  Strings.isNullOrEmpty(component.getDefaultModelObjectAsString()))) {
            component.setVisible(false);
        }
    }

    public static boolean isModelEmpty(IModel<String> model) {
        return model == null || Strings.isNullOrEmpty(model.getObject());
    }

    public static void show(Component... components) {
        if (components != null) {
            for (Component component : components) {
                component.setVisible(true);
            }
        }
    }

    public static void remove(Component component, Behavior checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                component.remove(behavior);
            }
        }
    }

    public static boolean contains(Component component, Behavior checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }
}
