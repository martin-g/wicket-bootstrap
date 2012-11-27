package de.agilecoders.wicket.util;

import com.google.common.base.Predicate;

import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Helper class for all kinds of {@link Iterable}
 *
 * @author miha
 */
public final class Iterables {

    /**
     * Construct.
     */
    private Iterables() {
        throw new UnsupportedOperationException();
    }

    /**
     * transform an array of elements into a list of elements
     *
     * @param elements the elements to add to list
     * @param <T>      the type of elements
     * @return new list of elements
     */
    public static <T> List<T> transform(final T[] elements) {
        return newArrayList(elements);
    }

    /**
     * filters and transform given array of elements by given predicate and returns
     * new list of filtered elements.
     *
     * @param elements  The elements to filter and transform
     * @param predicate the filter
     * @param <T>       the type of elements
     * @return new filtered list of elements
     */
    public static <T> List<T> forEach(final T[] elements, final Predicate<T> predicate) {
        return newArrayList(filter(transform(elements), predicate));
    }

    /**
     * filters and transform given list of elements by given predicate and returns
     * new list of filtered elements.
     *
     * @param elements  The elements to filter and transform
     * @param predicate the filter
     * @param <T>       the type of elements
     * @return new filtered list of elements
     */
    public static <T> List<T> forEach(final List<T> elements, final Predicate<T> predicate) {
        return newArrayList(filter(elements, predicate));
    }

}
