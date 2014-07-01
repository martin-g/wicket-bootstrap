package de.agilecoders.wicket.core.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.jquery.util.Generics2;
import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * #### Description
 *
 * helper class for css class names
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class CssClassNames {
    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();
    private static final Joiner JOINER = Joiner.on(' ').skipNulls();

    /**
     * creates a new {@link ICssClassNameProvider} from a given model.
     *
     * @param model the model to use for class name provider
     * @return new {@link ICssClassNameProvider} implementation
     */
    public static ICssClassNameProvider newProvider(final IModel<String> model) {
        Args.notNull(model, "model");

        return new ICssClassNameProvider() {
            @Override
            public String cssClassName() {
                return model.getObject();
            }
        };
    }

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
        return Generics2.newLinkedHashSet(SPLITTER.split(classValue));
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
            classValues = Generics2.newLinkedHashSet(Collections.<String>emptySet());

            addRaw(classValue);
        }

        /**
         * adds an unparsed css class name string.
         *
         * @param rawCssString the raw css classes
         * @return this instance for chaining
         */
        public Builder addRaw(final String rawCssString) {
            add(split(Strings2.nullToEmpty(rawCssString)));
            return this;
        }

        /**
         * removes all class names that are set on given builder
         *
         * @param builder The {@link Builder}
         * @return this builder instance for chaining
         */
        public Builder remove(final Builder builder) {
            Args.notNull(builder, "builder");

            return remove(builder.asSet());
        }

        /**
         * removes all class names that are given
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final String... classNames) {
            Args.notNull(classNames, "classNames");

            return remove(Generics2.newHashSet(classNames));
        }

        /**
         * removes all given class names
         *
         * @param classNames The css class names to remove
         * @return this builder instance for chaining
         */
        public Builder remove(final Set<String> classNames) {
            Args.notNull(classNames, "classNames");

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
            Args.notNull(builder, "builder");

            return add(builder.asSet());
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final String... classNames) {
            Args.notNull(classNames, "classNames");

            return add(Generics2.newLinkedHashSet(Arrays.asList(classNames)));
        }

        /**
         * adds all given class names
         *
         * @param classNames The css class names to add
         * @return this builder instance for chaining
         */
        public Builder add(final Set<String> classNames) {
            Args.notNull(classNames, "classNames");

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
            return Generics2.newArrayList(classValues);
        }

        /**
         * @return all css class names as set of strings
         */
        public Set<String> asSet() {
            return Generics2.newLinkedHashSet(classValues);
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
