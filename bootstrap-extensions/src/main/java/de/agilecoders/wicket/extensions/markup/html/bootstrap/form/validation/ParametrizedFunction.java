package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * <h1>JS function with parameters {@link de.agilecoders.wicket.jquery.JQuery.AbstractFunction}.</h1>
 * <p>Create JS function with string or {@link de.agilecoders.wicket.jquery.AbstractConfig} parameters <br>
 *
 * @TODO move to wicket-jquery-selectors
 *
 * @author Anton Osipov
 * @author Alexey Volkov
 * @since 30.09.2014
 */
public class ParametrizedFunction extends JQuery.AbstractFunction {

    private static final long serialVersionUID = 1L;

    /**
     * @param functionName function name
     * @param parameters   parameters
     */
    public ParametrizedFunction(String functionName, Object... parameters) {
        super(functionName);
        for (Object parameter : parameters) {
            if (parameter instanceof AbstractConfig) {
                addParameter(((AbstractConfig) parameter).toJsonString());
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
    public static ParametrizedFunction of(String name, String... value) {
        return new ParametrizedFunction(name, value);
    }

}

