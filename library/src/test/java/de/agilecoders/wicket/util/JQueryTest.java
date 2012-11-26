package de.agilecoders.wicket.util;

import org.apache.wicket.util.time.Duration;
import org.junit.Test;

import static de.agilecoders.wicket.util.JQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link JQuery} class
 *
 * @author miha
 * @version 1.0
 */
public class JQueryTest {

    @Test
    public void selectorIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").get(),
                   is(equalTo("$('.selector ul li.classname');")));
    }

    @Test
    public void jsFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new HelperFunction("setTimeout").addParam(new JQuery.JavaScriptInlineFunction("alert('Hello');")).addParam(Duration.seconds(1))).get(),
                   is(equalTo("$('.selector ul li.classname').setTimeout(function(){alert('Hello');},1000);")));
    }

    @Test
    public void chainedFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new JQuery.IFunction() {
            @Override
            public String build() {
                return "function()";
            }
        }).get(),
                   is(equalTo("$('.selector ul li.classname').function();")));
    }

    @Test
    public void chainedAbstractFunctionIsAddedToJqueryCall() {
        assertThat($(".selector ul li.classname").chain(new HelperFunction("function").addParam("String").addParam(true).addParam(null).addParam(1.567f).addParam(1).addParam(Long.MAX_VALUE)).get(),
                   is(equalTo("$('.selector ul li.classname').function('String',true,null,1.567,1,9223372036854775807);")));
    }

    /**
     * helper to build an {@link de.agilecoders.wicket.util.JQuery.IFunction}
     */
    private static final class HelperFunction extends JQuery.AbstractFunction {

        /**
         * Construct.
         *
         * @param functionName The function name of this {@link de.agilecoders.wicket.util.JQuery.IFunction} implementation
         */
        protected HelperFunction(final String functionName) {
            super(functionName);
        }

        /**
         * adds a parameter
         *
         * @param value the value to add
         * @return this instance for chaining
         */
        public HelperFunction addParam(final Object value) {
            addParameter(toParameterValue(value));
            return this;
        }
    }

}
