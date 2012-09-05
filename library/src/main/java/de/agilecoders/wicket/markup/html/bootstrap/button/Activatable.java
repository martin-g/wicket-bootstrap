package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.Component;

/**
 * An interface that allows the implementations to decide whether they are the active item
 * in a collection of items (like buttons, links, pills, ...)
 */
public interface Activatable {

    /**
     * Decides whether the current instance ({@code this}) is the active item.
     *
     * @param item the current instance as a Component
     * @return {@code true} if the item is the current active one
     */
    boolean isActive(Component item);
}
