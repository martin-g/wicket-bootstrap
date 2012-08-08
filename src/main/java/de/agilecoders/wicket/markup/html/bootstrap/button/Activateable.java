package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.Component;

/**
 * An interface used by Navbar to decide whether a component is the currently active one
 */
public interface Activateable {

    /**
     * Decides whether the current instance ({@code this}) is the active button
     * in the Navbar.
     *
     * @param button the current instance as a Component
     * @return {@code true} if the button is the current active one
     */
    boolean isActive(Component button);
}
