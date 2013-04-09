package de.agilecoders.wicket.core.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * helper class to handle creation/transformation/filtering of collections.
 *
 * This class is used to wrap guava. Future releases of bootstrap won't depend
 * on guava anymore.
 *
 * @author miha
 */
public final class Generics2 {

    /**
     * creates a new {@link ArrayList} from given array of elements.
     *
     * @param elements the elements to add to new {@link ArrayList}
     * @param <T>      the type of all elements inside the list
     * @return new {@link ArrayList} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> ArrayList<T> newArrayList(T... elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements.
     *
     * @param elements the elements that the list should contain, in order
     * @return a new {@code ArrayList} containing those elements
     */
    public static <T> ArrayList<T> newArrayList(Iterator<? extends T> elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * creates a new {@link ArrayList} from given array of elements.
     *
     * @param elements the elements to add to new {@link ArrayList}
     * @param <T>      the type of all elements inside the list
     * @return new {@link ArrayList} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> ArrayList<T> newArrayList(Iterable<? extends T> elements) {
        return Lists.newArrayList(elements);
    }

    /**
     * creates a new {@link LinkedHashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link LinkedHashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link LinkedHashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newLinkedHashSet(Iterable<? extends T> elements) {
        return Sets.newLinkedHashSet(elements);
    }

    /**
     * creates a new {@link HashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link HashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link HashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newHashSet(T... elements) {
        return Sets.newHashSet(elements);
    }

    /**
     * joins all given elements with a special separator
     *
     * @param elements elements to join
     * @param separator separator to use
     * @return elements as string
     */
    public static String join(final Iterable<?> elements, final char separator) {
        return Joiner.on(separator).skipNulls().join(elements);
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
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> List<R> transform(List<P> elements, Function<P, R> transformer) {
        return Lists.transform(elements, transformer);
    }

    /**
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> Set<R> transform(Set<P> elements, Function<P, R> transformer) {
        return Sets.newHashSet(transform(newArrayList(elements), transformer));
    }

    /**
     * Returns a list that applies {@code transformer} to each element of {@code
     * elements}
     */
    public static <P, R> List<R> transform(P[] elements, Function<P, R> transformer) {
        return Lists.transform(newArrayList(elements), transformer);
    }

    /**
     * Returns the elements of {@code unfiltered} that satisfy given {@code filter}. The
     * resulting iterable's iterator does not support {@code remove()}.
     */
    public static <T> List<T> filter(final Iterable<T> unfiltered, final Predicate<T> filter) {
        return newArrayList(Iterables.filter(unfiltered, filter));
    }

    /**
     * splits a {@link CharSequence} by given separator.
     *
     * @param value the value to split
     * @param separator the separator to use to split value
     * @return list of values
     */
    public static List<String> split(final CharSequence value, final String separator) {
        return newArrayList(Splitter.on(separator).omitEmptyStrings().trimResults().split(value));
    }

    /**
     * private constructor to prevent instantiation of util class.
     */
    private Generics2() {
        throw new UnsupportedOperationException();
    }
}
