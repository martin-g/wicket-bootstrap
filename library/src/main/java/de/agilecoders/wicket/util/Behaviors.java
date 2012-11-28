package de.agilecoders.wicket.util;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * Helper class for {@link Behavior}s
 *
 * @author miha
 * @version 1.0
 */
public final class Behaviors {

    /**
     * Construct.
     */
    private Behaviors() {
        throw new UnsupportedOperationException();
    }

    /**
     * removes given behavior from given component if it was added before
     *
     * @param component The {@link Component} to remove behavior from
     * @param checkBehavior The {@link Behavior} to remove
     */
    public static boolean remove(final Component component, final Behavior checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                component.remove(behavior);
                return true;
            }
        }
        return false;
    }

    /**
     * checks whether given behavior was added to given component before
     *
     * @param component The {@link Component} to check if behavior was set
     * @param checkBehavior The {@link Behavior} to check
     */
    public static boolean contains(final Component component, final Behavior checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks whether given behavior class was added to given component before
     *
     * @param component The {@link Component} to check if behavior was set
     * @param checkBehavior The {@link Behavior} class to check
     */
    public static boolean contains(Component component, Class<? extends Behavior> checkBehavior) {
        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.getClass().equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }
}
