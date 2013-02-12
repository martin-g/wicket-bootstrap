package de.agilecoders.wicket.util;

import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * helper class to handle creation of collections
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
        Args.notNull(elements, "elements");

        final ArrayList<T> list = new ArrayList<T>(elements.length);
        Collections.addAll(list, elements);

        return list;
    }

    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements.
     *
     * @param elements the elements that the list should contain, in order
     * @return a new {@code ArrayList} containing those elements
     */
    public static <T> ArrayList<T> newArrayList(Iterator<? extends T> elements) {
        Args.notNull(elements, "elements");

        ArrayList<T> list = new ArrayList<T>();
        while (elements.hasNext()) {
            list.add(elements.next());
        }

        return list;
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
        Args.notNull(elements, "elements");

        return (elements instanceof Collection)
               ? new ArrayList<T>(cast(elements))
               : newArrayList(elements.iterator());
    }

    /**
     * creates a new {@link LinkedHashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link LinkedHashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link LinkedHashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newLinkedHashSet(T... elements) {
        if (elements != null) {
            final Set<T> set = new LinkedHashSet<T>();
            Collections.addAll(set, elements);

            return set;
        }

        throw new IllegalArgumentException("'elements' is not allowed to be null.");
    }

    /**
     * creates a new {@link LinkedHashSet} from given array of elements.
     *
     * @param elements the elements to add to new {@link LinkedHashSet}
     * @param <T>      the type of all elements inside the set
     * @return new {@link LinkedHashSet} that contains all given elements
     * @throws IllegalArgumentException if given array of elements is null
     */
    public static <T> Set<T> newLinkedHashSet(Collection<? extends T> elements) {
        if (elements != null) {
            final Set<T> set = new LinkedHashSet<T>();
            set.addAll(elements);

            return set;
        }

        throw new IllegalArgumentException("'elements' is not allowed to be null.");
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
        if (elements != null) {
            final Set<T> set = new HashSet<T>();
            Collections.addAll(set, elements);

            return set;
        }

        throw new IllegalArgumentException("'elements' is not allowed to be null.");
    }

    public static String join(final Iterable<?> elements, final char separator) {
        Args.notNull(elements, "elements");

        final Iterator<?> iterator = elements.iterator();
        if (!iterator.hasNext()) {
            return "";
        } else {
            final StringBuilder joinedElements = new StringBuilder();

            while (iterator.hasNext()) {
                final CharSequence v = toString(iterator.next());

                if (v != null) {
                    if (joinedElements.length() > 0) {
                        joinedElements.append(separator);
                    }

                    joinedElements.append(v);
                }
            }

            return joinedElements.toString();
        }
    }

    private static CharSequence toString(Object part) {
        return part != null ? (part instanceof CharSequence) ? (CharSequence) part : part.toString() : null;
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

    public static <P, R> List<R> transform(List<P> elements, Function<P, R> transformer) {
        Args.notNull(elements, "elements");
        Args.notNull(transformer, "transformer");

        List<R> transformed = newArrayList();

        for (P elem : elements) {
            transformed.add(transformer.apply(elem));
        }
        return transformed;
    }

    public static <T> List<T> filter(final Iterable<T> elements, final Predicate<T> filter) {
        Args.notNull(elements, "elements");
        Args.notNull(filter, "filter");

        final List<T> filteredElements = new ArrayList<T>();
        for(T elem:elements) {
            if (filter.apply(elem)) {
                filteredElements.add(elem);
            }
        }
        return filteredElements;
    }

    /**
     * splits a {@link CharSequence} by given separator.
     *
     * @param value the value to split
     * @param separator the separator to use to split value
     * @return list of values
     */
    public static List<String> split(CharSequence value, String separator) {
        return filter(transform(String.valueOf(value).split(separator)), new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return !Strings.isEmpty(input);
            }
        });
    }

    /**
     * simple function interface.
     *
     * @param <P> the input parameter type
     * @param <R> the return value type
     */
    public static interface Function<P, R> {

        /**
         * Returns the result of applying this function to {@code input}
         *
         * @param param the input parameter with type P
         * @return value with type R
         */
        R apply(P param);

    }

    /**
     * Determines a true or false value for a given input.
     *
     * @param <T> the input type
     */
    public interface Predicate<T> {
        /**
         * Returns the result of applying this predicate to {@code input}.
         */
        boolean apply(T input);
    }

    /**
     * Used to avoid http://bugs.sun.com/view_bug.do?bug_id=6558557
     */
    private static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection<T>) iterable;
    }

    /**
     * private constructor to prevent instantiation of util class.
     */
    private Generics2() {
        throw new UnsupportedOperationException();
    }
}
