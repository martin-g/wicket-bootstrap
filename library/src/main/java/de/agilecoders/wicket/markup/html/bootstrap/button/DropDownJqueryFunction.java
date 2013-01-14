package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.base.Strings;
import de.agilecoders.wicket.util.JQuery;

/**
 * A jquery function abstraction for dropdowns.
 *
 * @author miha
 */
public class DropDownJqueryFunction extends JQuery.AbstractFunction {

    /**
     * shortcut for {@link de.agilecoders.wicket.markup.html.bootstrap.button.DropDownJqueryFunction}.
     *
     * @param action the dropdown action to execute.
     */
    public static DropDownJqueryFunction dropdown(final String action) {
        return new DropDownJqueryFunction(action);
    }

    /**
     * shortcut for {@link de.agilecoders.wicket.markup.html.bootstrap.button.DropDownJqueryFunction}.
     */
    public static DropDownJqueryFunction dropdown() {
        return new DropDownJqueryFunction(null);
    }

    /**
     * Construct.
     *
     * @param action the button action to execute.
     */
    public DropDownJqueryFunction(final String action) {
        super("dropdown");

        if (!Strings.isNullOrEmpty(action)) {
            addParameter(toParameterValue(action));
        }
    }
}
