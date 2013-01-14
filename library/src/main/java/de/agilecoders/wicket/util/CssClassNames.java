package de.agilecoders.wicket.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

import static com.google.common.base.Strings.nullToEmpty;

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
     * returns a {@link Builder} to add/remove new values.
     *
     * @return new Builder.
     */
    public static Builder newBuilder() {
        return new Builder("");
    }

    /**
     * parses the given value and returns a {@link Builder} to add/remove
     * new values.
     *
     * @param classValue The class attribute value
     * @return new Builder.
     */
    public static Builder parse(final String classValue) {
        return new Builder(classValue);
    }

    /**
     * Builder for css class names. All css class names will be hold in a
     * {@link Set} of strings.
     */
    public static final class Builder {
        private final Set<String> classValues;

        /**
         * Construct.
         *
         * @param classValue the initial class name string
         */
        private Builder(final String classValue) {
            classValues = Sets.newHashSet();

            addRaw(classValue);
        }

        /**
         * adds an unparsed css class name string.
         *
         * @param rawCssString the raw css classes
         * @return this instance for chaining
         */
        public Builder addRaw(final String rawCssString) {
            add(split(nullToEmpty(rawCssString)));
            return this;
        }

        /**
         * removes all class names that are set on given builder
         *
         * @param builder The {@link Builder}
         * @return this builder instance for chaining
         */
        public Builder remove(final Builder builder) {
            return remove(builder.asSet());
        }

        /**
         * removes all class names that are given
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final String... classNames) {
            return remove(Sets.newHashSet(classNames));
        }

        /**
         * removes all given class names
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final Set<String> classNames) {
            classValues.removeAll(classNames);
            return this;
        }

        /**
         * adds all class names that are set on given builder
         *
         * @param builder The {@link Builder}
         * @return this builder instance for chaining
         */
        public Builder add(final Builder builder) {
            return add(builder.asSet());
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final String... classNames) {
            return add(Sets.newHashSet(classNames));
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final Set<String> classNames) {
            classValues.addAll(classNames);
            return this;
        }

        /**
         * @return all css class names as valid html attribute value
         */
        public String asString() {
            return join(classValues);
        }

        /**
         * @return all css class names as list of strings
         */
        public List<String> asList() {
            return Lists.newArrayList(classValues);
        }

        /**
         * @return all css class names as set of strings
         */
        public Set<String> asSet() {
            return Sets.newLinkedHashSet(classValues);
        }

        /**
         * checks if given class name was added before
         *
         * @param className The class name to check
         * @return true, if class name was set
         */
        public boolean contains(final String className) {
            return classValues.contains(className);
        }
    }
}
