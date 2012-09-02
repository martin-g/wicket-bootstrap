package de.agilecoders.wicket.util;

import com.google.common.base.Predicate;

import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public final class Iterables {

    public static <T> List<T> transform(final T[] elements) {
        return newArrayList(elements);
    }

    public static <T> List<T> forEach(final T[] elements, final Predicate<T> predicate) {
        return newArrayList(filter(transform(elements), predicate));
    }

    public static <T> List<T> forEach(final List<T> elements, final Predicate<T> predicate) {
        return newArrayList(filter(elements, predicate));
    }

}
