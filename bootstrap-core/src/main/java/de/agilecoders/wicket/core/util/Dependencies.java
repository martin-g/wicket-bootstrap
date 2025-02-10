package de.agilecoders.wicket.core.util;

import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.jquery.util.Generics2;

/**
 * #### Description
 *
 * Helper class to handle {@link org.apache.wicket.request.resource.ResourceReference} dependencies.
 */
public final class Dependencies {

    /**
     * #### Description
     *
     * combines two lists of {@link HeaderItem}.
     *
     * #### Usage
     *
     * when using in `getDependencies()`:
     *
     * ```java
     * <pre>{@code
     * public Iterable<? extends HeaderItem> getDependencies() {
     *     return Dependencies.combine(super.getDependencies(), myCustomCssHeaderItem, myCustomJsHeaderItem);
     * }
     * }</pre>
     * ```
     *
     * @param headerItems the base header item list
     * @param additional all additional {@link HeaderItem}
     * @return combined list of {@link HeaderItem}
     */
    public static List<HeaderItem> combine(final Iterable<? extends HeaderItem> headerItems, final HeaderItem... additional) {
        Args.notNull(headerItems, "headerItems");
        Args.notNull(additional, "additional");

        final List<HeaderItem> elements = Generics2.newArrayList(headerItems);
        elements.addAll(List.of(additional));

        return elements;
    }

    /**
     * Private constructor to prevent instantiation
     */
    private Dependencies() {
        throw new UnsupportedOperationException();
    }
}
