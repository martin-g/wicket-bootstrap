package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;

/**
 * The behavior that returns JSON response for the typeahead widget.
 */
public abstract class TypeaheadBehavior<T> extends AbstractAjaxBehavior
{
    public void onRequest() {

        RequestCycle requestCycle = getComponent().getRequestCycle();
        Request request = requestCycle.getRequest();
        IRequestParameters parameters = request.getRequestParameters();
        StringValue input = parameters.getParameterValue("term");

        final Iterable<T> choices = getChoices(input.toString(""));

        String jsonArray = createJson(choices);

        requestCycle.scheduleRequestHandlerAfterCurrent(new TextRequestHandler("application/json", "UTF-8", jsonArray));
    }

    /**
     * Serializes the returned choices into JSON array.
     *
     * @param choices
     *            the choices for the term
     * @return JSON array with all choices
     */
    protected String createJson(final Iterable<T> choices) {

        StringBuilder json = new StringBuilder();
        json.append('[');
        for (T choice : choices)
        {
            if (json.length() > 1)
            {
                json.append(',');
            }
            json.append('"').append(choice).append('"');
        }
        json.append(']');

        return json.toString();
    }

    /**
     * Finds the possible choices for the provided input
     *
     * @param input
     *            the term provided by the user.
     * @return a collection of all possible choices for this input
     */
    protected abstract Iterable<T> getChoices(String input);
}
