package de.agilecoders.wicket.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * helper class for css class names
 *
 * @author miha
 * @version 1.0
 */
public final class CssClassNames {

    private static final Splitter SPLITTER = Splitter.onPattern("\\s+").trimResults().omitEmptyStrings();
    private static final Joiner JOINER = Joiner.on(' ').skipNulls();

    /**
     * Constructor.
     */
    private CssClassNames() {
        throw new UnsupportedOperationException();
    }

    /**
     * joins a list of css class names.
     *
     * @param classNames The css class names to join
     * @return the joined class names.
     */
    public static String join(Iterable<String> classNames) {
        return JOINER.join(classNames);
    }

    /**
     * splits a given class attribute value.
     *
     * @param classValue The css class names as string
     * @return a new set of css class names.
     */
    public static Set<String> split(final CharSequence classValue) {
        return Sets.newLinkedHashSet(SPLITTER.split(classValue));
    }

    /**
     * parses the given value and returns a {@link Builder} to add/remove
     * new values.
     *
     * @param classValue The class attribute value
     * @return new Builder.
     */
    public static Builder parse(final CharSequence classValue) {
        return new Builder(classValue);
    }

    /**
     * Builder for css class names.
     */
    public static final class Builder {
        private final Set<String> classValues;

        private Builder(final CharSequence classValue) {
            classValues = split(classValue);
        }

        public Builder remove(final Builder builder) {
            return remove(builder.asSet());
        }

        public Builder remove(final String... classNames) {
            return remove(Sets.newHashSet(classNames));
        }

        public Builder remove(final Set<String> classNames) {
            classValues.removeAll(classNames);
            return this;
        }

        public Builder add(final Builder builder) {
            return add(builder.asSet());
        }

        public Builder add(final String... classNames) {
            return add(Sets.newHashSet(classNames));
        }

        public Builder add(final Set<String> classNames) {
            classValues.addAll(classNames);
            return this;
        }

        public String asString() {
            return join(classValues);
        }

        public Set<String> asSet() {
            return Sets.newLinkedHashSet(classValues);
        }
    }
}
