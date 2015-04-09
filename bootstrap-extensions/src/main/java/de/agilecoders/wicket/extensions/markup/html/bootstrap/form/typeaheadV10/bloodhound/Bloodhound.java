package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.Source;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.string.StringValue;

/**
 * A <a href="https://github.com/twitter/typeahead.js/blob/master/doc/bloodhound.md">Bloodhound</a>
 * source.
 */
public abstract class Bloodhound<T> extends AbstractAjaxBehavior implements Source<T> {

    private final String name;

    private final BloodhoundConfig config;

    /** The parameter holding the query term in the callback url **/
    private static final String QUERY_PARAM = "term";

    /**
     * This constructor will initialize a default {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.BloodhoundConfig}
     *
     * @param name the name of this bloodhound instance
     */
    public Bloodhound(String name) {
        this(name, new BloodhoundConfig());
    }

    /**
     * @param name the name of this bloodhound instance
     */
    public Bloodhound(String name, BloodhoundConfig config) {
        this.name = name;
        this.config = config;
    }

    /**
     * @return the name of this instance
     */
    public String getName() {
        return name;
    }

    @Override
    public Json.RawValue getFunction() {
        return new Json.RawValue(String.format("%s.ttAdapter()", name));
    }

    @Override
    protected void onBind() {
        super.onBind();

        Remote remote = config.getRemote();

        if (remote == null) {
            remote = Remote.of(getRemoteUrl(Remote.DEFAULT_WILDCARD));
            config.withRemote(remote);
        } else {
            remote.withUrl(getRemoteUrl(remote.getWildcard() != null ? remote.getWildcard() : Remote.DEFAULT_WILDCARD));
        }
    }

    protected CharSequence getRemoteUrl(String wildcard) {
        return String.format("%s&%s=%s", getCallbackUrl(), QUERY_PARAM, wildcard);
    }

    @Override
    public abstract Iterable<T> getChoices(String input);

    public BloodhoundConfig getConfig() {

        // prevent StalePageException
        // see https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/493
        if (config != null && config.getRemote() != null) {
          Remote remote = config.getRemote();
          remote.withUrl(getRemoteUrl(remote.getWildcard() != null ? remote.getWildcard() : Remote.DEFAULT_WILDCARD));
        }

        return config;
    }

    @Override
    public void onRequest() {

        RequestCycle requestCycle = getComponent().getRequestCycle();
        Request request = requestCycle.getRequest();
        IRequestParameters parameters = request.getRequestParameters();
        StringValue input = parameters.getParameterValue(QUERY_PARAM);

        final Iterable<T> choices = getChoices(input.toString(""));

        String jsonArray = createJson(choices);

        requestCycle.scheduleRequestHandlerAfterCurrent(
            new TextRequestHandler("application/json", "UTF-8", jsonArray));

    }

    /**
     * Serializes the returned choices into JSON array.
     *
     * @param choices the choices for the term
     * @return JSON array with all choices
     */
    protected String createJson(final Iterable<T> choices) {

        StringBuilder json = new StringBuilder();
        json.append('[');
        for (T choice : choices) {
            if (json.length() > 1) {
                json.append(',');
            }
            json.append(renderChoice(choice));
        }
        json.append(']');

        return json.toString();
    }
}
