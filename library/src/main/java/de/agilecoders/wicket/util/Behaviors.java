package de.agilecoders.wicket.util;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public final class Behaviors {
    private Behaviors() {
        throw new RuntimeException("static class");
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

    public static boolean contains(Component component, Class<? extends Behavior> checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.getClass().equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }
}
