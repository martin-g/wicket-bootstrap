package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.util.lang.Args;

import java.util.List;

/**
 * Helper class to handle {@link org.apache.wicket.request.resource.ResourceReference} dependencies.
 *
 * @author miha
 */
public final class Dependencies {

    /**
     * creates a list of {@link HeaderItem}
     *
     * @param headerItems the base header item list
     * @param additional all additional {@link HeaderItem}
     * @return combined list of {@link HeaderItem}
     */
    public static List<HeaderItem> combine(final Iterable<? extends HeaderItem> headerItems, final HeaderItem... additional) {
        Args.notNull(headerItems, "headerItems");
        Args.notNull(additional, "additional");

        final List<HeaderItem> elements = Generics2.newArrayList(headerItems);
        elements.addAll(Generics2.newArrayList(additional));

        return elements;
    }

    /**
     * Private constructor to prevent instantiation
     */
    private Dependencies() {
        throw new UnsupportedOperationException();
    }
}
