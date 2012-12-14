package de.agilecoders.wicket.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
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
    private static final Joiner SELECTOR_JOINER = Joiner.on(' ').skipNulls();

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

    /**
     * helper method to allow a jquery like code style
     *
     * @param component          The markup id of given component is used as jquery selector
     * @param additionalSelector an additional initial selector
     * @return new Jquery instance
     */
    public static JQuery $(final Component component, final String... additionalSelector) {
        final List<String> selector = Lists.newArrayList("#" + component.getMarkupId(true));

        if (additionalSelector != null) {
            selector.addAll(Lists.newArrayList(additionalSelector));
        }

        return $(SELECTOR_JOINER.join(selector));
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
     * adds a chained function to this jquery instance
     *
     * @param functionName the function to add
     * @return this instance for chaining
     */
    public JQuery chain(final String functionName) {
        functions.add(new SimpleFunction(functionName));
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
     * adds a chained function to this jquery instance
     *
     * @param functionName the function to add
     * @param config       the function configuration
     * @return this instance for chaining
     */
    public JQuery chain(final String functionName, final AbstractConfig config) {
        functions.add(new ConfigurableFunction(functionName, config));
        return this;
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
     * simple class to represent a javascript function.
     */
    public static class JavaScriptInlineFunction extends AbstractFunction {
        private final String functionBody;

        /**
         * Construct.
         *
         * @param functionBody the function body as string
         */
        public JavaScriptInlineFunction(final String functionBody) {
            super("function");

            this.functionBody = nullToEmpty(functionBody);
        }

        @Override
        public String build() {
            return super.build() + "{" + nullToEmpty(functionBody) + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof JavaScriptInlineFunction) {
                return functionBody.equals(((JavaScriptInlineFunction) o).functionBody);
            } else if (o instanceof String) {
                return functionBody.equals(o);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return functionBody.hashCode();
        }

        @Override
        public String toString() {
            return functionBody;
        }

        /**
         * @param value The value to stringify
         * @return string representation of given value
         */
        public static String toString(final JavaScriptInlineFunction value) {
            return value != null ? value.toString() : "null";
        }
    }

    /**
     * a simple function without params and body
     */
    public static final class SimpleFunction extends AbstractFunction {
        /**
         * Construct.
         *
         * @param functionName The function name of this {@link de.agilecoders.wicket.util.JQuery.IFunction} implementation
         */
        protected SimpleFunction(final String functionName) {
            super(functionName);
        }
    }

    /**
     * a configurable function with one parameter (an {@link AbstractConfig}) and without body
     */
    public static final class ConfigurableFunction extends AbstractFunction {

        /**
         * Construct.
         *
         * @param functionName The function name of this {@link de.agilecoders.wicket.util.JQuery.IFunction} implementation
         * @param config       the function configuration
         */
        protected ConfigurableFunction(final String functionName, final AbstractConfig config) {
            super(functionName);

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }

    /**
     * java abstraction of jquery each function
     */
    public static final class EachJqueryFunction extends AbstractFunction {

        /**
         * creates a new {@link EachJqueryFunction} instance that holds a given inline function.
         *
         * @param function The inline function to execute for each element
         * @return new {@link EachJqueryFunction} instance
         */
        public static EachJqueryFunction each(final JavaScriptInlineFunction function) {
            return new EachJqueryFunction(function);
        }

        /**
         * Construct.
         */
        protected EachJqueryFunction(final JavaScriptInlineFunction function) {
            super("each");

            addParameter(toParameterValue(function));
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
            } else if (value instanceof JavaScriptInlineFunction) {
                return toParameterValue((JavaScriptInlineFunction) value);
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
        protected final String toParameterValue(final JavaScriptInlineFunction value) {
            return value != null ? value.build() : "null";
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
