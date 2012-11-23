package de.agilecoders.wicket.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.wicket.Component;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.time.Duration;

import java.util.List;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * The Jquery class helps to keep all javascript jquery scripts type safe.
 *
 * @author miha
 */
public final class JQuery implements IClusterable {
    private static final Joiner FUNCTION_JOINER = Joiner.on('.').skipNulls();

    /**
     * Function that maps an {@link IFunction} to its string representation.
     */
    private static final Function<IFunction, String> FUNCTION_TRANSFORMER = new Function<IFunction, String>() {
        @Override
        public String apply(final IFunction function) {
            return function != null ? function.build() : null;
        }
    };

    /**
     * helper method to allow a jquery like code style
     *
     * @param selector The jquery selector
     * @return new Jquery instance
     */
    public static JQuery $(final String selector) {
        return new JQuery(selector);
    }

    /**
     * helper method to allow a jquery like code style
     *
     * @param component The markup id of given component is used as jquery selector
     * @return new Jquery instance
     */
    public static JQuery $(final Component component) {
        return $("#" + component.getMarkupId(true));
    }

    private final String selector;
    private final List<IFunction> functions;

    /**
     * Construct.
     *
     * @param selector the selector to use.
     */
    private JQuery(final String selector) {
        this.selector = selector;
        this.functions = Lists.newArrayList();
    }

    /**
     * adds a chained function to this jquery instance
     *
     * @param function the function to add
     * @return this instance for chaining
     */
    public JQuery chain(final IFunction function) {
        functions.add(function);
        return this;
    }

    /**
     * @return this jquery chain as string.
     */
    public String get() {
        return "$('" + selector + "')" + createFunctionString() + ";";
    }

    /**
     * @return all functions as joined (by {@link #FUNCTION_JOINER}) string.
     */
    private String createFunctionString() {
        return functions.isEmpty() ? "" : "." + FUNCTION_JOINER.join(Lists.transform(functions, FUNCTION_TRANSFORMER));
    }

    /**
     * simple interface to represent a jquery function.
     */
    public static interface IFunction extends IClusterable {

        /**
         * @return the function as javascript string.
         */
        String build();
    }

    /**
     * simple class to represent a jquery function.
     */
    public static final class JavaScriptFunction implements IClusterable {
        private final String function;

        /**
         * Construct.
         *
         * @param function     The function as string.
         * @param functionBody the function body as string
         */
        public JavaScriptFunction(final JavaScriptInlineFunction function, final String functionBody) {
            Preconditions.checkNotNull(function);

            this.function = function.build() + "{" + nullToEmpty(functionBody) + "}";
        }

        /**
         * Construct.
         *
         * @param function The function as string.
         */
        public JavaScriptFunction(final String function) {
            this.function = "function(){" + nullToEmpty(function) + "}";
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof JavaScriptFunction || o instanceof String) && function.equals(o);
        }

        @Override
        public int hashCode() {
            return function.hashCode();
        }

        @Override
        public String toString() {
            return function;
        }

        /**
         * @param value The value to stringify
         * @return string representation of given value
         */
        public static String toString(JavaScriptFunction value) {
            return value != null ? value.toString() : null;
        }
    }

    /**
     * A javascript inline function.
     */
    public static class JavaScriptInlineFunction extends AbstractFunction {

        /**
         * Construct.
         */
        protected JavaScriptInlineFunction() {
            super("function");
        }
    }

    /**
     * A simple implementation of {@link IFunction} that allows you to chain
     * function parameters in a javascript safe way.
     */
    public static abstract class AbstractFunction implements IFunction {
        private static final Joiner PARAMETER_JOINER = Joiner.on(',').skipNulls();

        private final String functionName;
        private final List<String> parameters;

        /**
         * Construct.
         *
         * @param functionName The function name of this {@link IFunction} implementation
         */
        protected AbstractFunction(final String functionName) {
            this.functionName = functionName;
            this.parameters = Lists.newArrayList();
        }

        @Override
        public String build() {
            return functionName + "(" + buildParameters() + ")";
        }

        /**
         * @return the parameter joiner instance
         */
        protected final Joiner getJoiner() {
            return PARAMETER_JOINER;
        }

        /**
         * @return a joined list of parameters as string
         */
        protected String buildParameters() {
            return getJoiner().join(parameters);
        }

        /**
         * adds a new parameter to parameter list
         *
         * @param parameter The parameter to add
         */
        protected final void addParameter(final String parameter) {
            parameters.add(parameter);
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final Object value) {
            if (value instanceof Long) {
                return toParameterValue((Long) value);
            } else if (value instanceof Integer) {
                return toParameterValue((Integer) value);
            } else if (value instanceof Boolean) {
                return toParameterValue((Boolean) value);
            } else if (value instanceof Float) {
                return toParameterValue((Float) value);
            } else if (value instanceof JavaScriptFunction) {
                return toParameterValue((JavaScriptFunction) value);
            } else if (value instanceof Duration) {
                return String.valueOf(((Duration) value).getMilliseconds());
            }

            return value != null ? "'" + String.valueOf(value) + "'" : "null";
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final JavaScriptFunction value) {
            return value != null ? JavaScriptFunction.toString(value) : "null";
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final Long value) {
            return Long.toString(value);
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final Integer value) {
            return Integer.toString(value);
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final Float value) {
            return Float.toString(value);
        }

        /**
         * transform given value to a javascript parameter value
         *
         * @param value The value to transform
         * @return value as string
         */
        protected final String toParameterValue(final Boolean value) {
            return value != null ? Boolean.toString(value) : "null";
        }
    }
}
