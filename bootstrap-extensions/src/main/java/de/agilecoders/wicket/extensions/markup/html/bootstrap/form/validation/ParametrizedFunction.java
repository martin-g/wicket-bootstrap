package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.Config;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * <h1>JS function with parameters {@link de.agilecoders.wicket.jquery.JQuery.AbstractFunction}.</h1>
 * <p>Create JS function with string or {@link de.agilecoders.wicket.jquery.Config} parameters <br>
 *
 * TODO Replace with Function once wicket-jquery-selectors:0.1.5 is released
 *
 * @author Anton Osipov
 * @author Alexey Volkov
 * @since 30.09.2014
 */
class ParametrizedFunction extends JQuery.AbstractFunction {

    private static final long serialVersionUID = 1L;

    /**
     * @param functionName function name
     * @param parameters   parameters
     */
    ParametrizedFunction(String functionName, Object... parameters) {
        super(functionName);
        for (Object parameter : parameters) {
            if (parameter instanceof Config) {
                addParameter(((Config) parameter).toJsonString());
            } else {
                addParameter(toParameterValue(parameter));
            }
        }
    }

    /**
     * creates function instance
     *
     * @param name  name
     * @param value value
     * @return inline function
     */
    static ParametrizedFunction func(String name, Object... value) {
        return new ParametrizedFunction(name, value);
    }
}

