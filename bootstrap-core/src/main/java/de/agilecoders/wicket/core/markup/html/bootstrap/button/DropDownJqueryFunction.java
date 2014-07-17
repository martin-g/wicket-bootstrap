package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.jquery.JQuery;

import org.apache.wicket.util.string.Strings;

/**
 * A jquery function abstraction for dropdowns.
 *
 * @author miha
 */
public class DropDownJqueryFunction extends JQuery.AbstractFunction {
    private static final CharSequence functionName = "dropdown";

    /**
     * shortcut for {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.DropDownJqueryFunction}.
     *
     * @param action the dropdown action to execute.
     */
    public static DropDownJqueryFunction dropdown(final CharSequence action) {
        return new DropDownJqueryFunction(action);
    }

    /**
     * shortcut for {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.DropDownJqueryFunction}.
     */
    public static DropDownJqueryFunction dropdown() {
        return new DropDownJqueryFunction(null);
    }

    /**
     * Construct.
     *
     * @param action the button action to execute.
     */
    public DropDownJqueryFunction(final CharSequence action) {
        super(functionName);

        if (!Strings.isEmpty(action)) {
            addParameter(toParameterValue(action));
        }
    }
}
