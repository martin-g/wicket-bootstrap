package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.jquery.JQuery;

/**
 * A jquery function abstraction for buttons.
 *
 * @author miha
 */
public class ButtonJqueryFunction extends JQuery.AbstractFunction {
    private static final CharSequence functionName = "button";

    /**
     * shortcut for {@link ButtonJqueryFunction}.
     *
     * @param action the button action to execute.
     */
    public static ButtonJqueryFunction button(final CharSequence action) {
        return new ButtonJqueryFunction(action);
    }

    /**
     * Construct.
     *
     * @param action the button action to execute.
     */
    public ButtonJqueryFunction(final CharSequence action) {
        super(functionName);

        addParameter(toParameterValue(action));
    }
}
