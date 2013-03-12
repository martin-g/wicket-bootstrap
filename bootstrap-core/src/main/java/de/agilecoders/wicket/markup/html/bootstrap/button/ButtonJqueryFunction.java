package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.util.JQuery;

/**
 * A jquery function abstraction for buttons.
 *
 * @author miha
 * @version 1.0
 */
public class ButtonJqueryFunction extends JQuery.AbstractFunction {

    /**
     * shortcut for {@link ButtonJqueryFunction}.
     *
     * @param action the button action to execute.
     */
    public static ButtonJqueryFunction button(final String action) {
        return new ButtonJqueryFunction(action);
    }

    /**
     * Construct.
     *
     * @param action the button action to execute.
     */
    public ButtonJqueryFunction(final String action) {
        super("button");

        addParameter(toParameterValue(action));
    }
}
