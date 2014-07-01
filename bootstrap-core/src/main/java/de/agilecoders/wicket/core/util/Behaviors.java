package de.agilecoders.wicket.core.util;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.util.lang.Args;

/**
 * # Description
 * <p/>
 * Helper class for {@link Behavior}s
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class Behaviors {

    /**
     * Construct.
     */
    private Behaviors() {
        throw new UnsupportedOperationException();
    }

    /**
     * # Description
     * <p/>
     * removes given behavior from given component if it was added before
     * <p/>
     * # Usage
     * <p/>
     * if behavior was added to component:
     * ```java
     * myComponent.add(myBehavior);
     * // ...
     * Behaviors.remove(myComponent, myBehavior); // = true
     * ```
     * <p/>
     * if behavior wasn't added to component before:
     * ```java
     * Behaviors.remove(myComponent, myBehavior); // = false
     * ```
     *
     * @param component     The {@link Component} to remove behavior from
     * @param checkBehavior The {@link Behavior} to remove
     * @throws java.lang.IllegalArgumentException if component is null
     */
    public static boolean remove(final Component component, final Behavior checkBehavior) {
        Args.notNull(component, "component");

        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                component.remove(behavior);
                return true;
            }
        }
        return false;
    }

    /**
     * # Description
     * <p/>
     * checks whether given behavior was added to given component before
     * <p/>
     * # Usage
     * <p/>
     * if behavior was added to component:
     * ```java
     * myComponent.add(myBehavior);
     * // ...
     * Behaviors.contains(myComponent, myBehavior); // = true
     * ```
     * <p/>
     * if behavior wasn't added to component before:
     * ```java
     * Behaviors.contains(myComponent, myBehavior); // = false
     * ```
     *
     * @param component     The {@link Component} to check if behavior was set
     * @param checkBehavior The {@link Behavior} to check
     * @throws java.lang.IllegalArgumentException if component or behavior is null
     */
    public static boolean contains(final Component component, final Behavior checkBehavior) {
        Args.notNull(component, "component");
        Args.notNull(checkBehavior, "behavior");

        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks whether given behavior class was added to given component before
     * <p/>
     * # Usage
     * <p/>
     * if behavior was added to component:
     * ```java
     * myComponent.add(myBehavior);
     * // ...
     * Behaviors.contains(myComponent, MyBehavior.class); // = true
     * ```
     * <p/>
     * if behavior wasn't added to component before:
     * ```java
     * Behaviors.contains(myComponent, MyBehavior.class); // = false
     * ```
     *
     * @param component     The {@link Component} to check if behavior was set
     * @param checkBehavior The {@link Behavior} class to check
     * @throws java.lang.IllegalArgumentException if component or behavior is null
     */
    public static boolean contains(Component component, Class<? extends Behavior> checkBehavior) {
        Args.notNull(component, "component");
        Args.notNull(checkBehavior, "behavior");

        for (Behavior behavior : component.getBehaviors()) {
            if (behavior.getClass().equals(checkBehavior)) {
                return true;
            }
        }

        return false;
    }
}
