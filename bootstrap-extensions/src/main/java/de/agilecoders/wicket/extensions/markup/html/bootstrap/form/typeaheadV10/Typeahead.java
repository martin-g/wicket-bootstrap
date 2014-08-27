package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import com.fasterxml.jackson.databind.JsonNode;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead.*;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound;
import de.agilecoders.wicket.jquery.JQuery;
import de.agilecoders.wicket.jquery.util.Json;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.CallbackParameter;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A typeahead field implementing twitters <a href="http://twitter.github.io/typeahead.js/">typeahead.js</a>
 * >= v0.10
 *
 * The typeaheadV10 package reflects a major <a href="https://github.com/twitter/typeahead.js/blob/master/CHANGELOG.md#0100-february-2-2014">API
 * change in typeahead.js 0.10.0</a>
 */
public class Typeahead<T> extends TextField<T> {

    private static final Logger LOG = LoggerFactory.getLogger(Typeahead.class);

    private final TypeaheadConfig config;

    private TypeaheadBehavior selectBehavior;

    public Typeahead(String id, IModel<T> model, TypeaheadConfig config) {
        super(id, model);
        this.config = config;
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        for (DataSet dataSet : config.getDatasets()) {
            Source source = dataSet.getSource();
            if (source instanceof Behavior) {
                Behavior behavior = (Behavior) source;
                if (!getBehaviors().contains(behavior)) {
                    add(behavior);
                }
            }
            // we need to set the source late in the component lifecycle
            // as it might need to retrieve a callbackUrl which will fail
            // in the initialization phase
            dataSet.withSource(source);
        }

        if (selectBehavior == null && config.isSelectEvent()) {
            add(selectBehavior = new TypeaheadBehavior(TypeaheadEvent.Type.SELECTED));
        }
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        response.render(newTypeaheadJsHeaderItem());
        response.render(getDomReadyScript(config));
    }

    protected HeaderItem newTypeaheadJsHeaderItem() {
        return JavaScriptHeaderItem.forReference(TypeaheadJsReference.instance());
    }

    public OnDomReadyHeaderItem getDomReadyScript(TypeaheadConfig config) {

        StringBuilder builder = new StringBuilder();

        for (DataSet dataSet : config.getDatasets()) {
            // initialize Bloodhound instances it the source is a Bloodhound object
            if (dataSet.getSource() instanceof Bloodhound) {
                Bloodhound bh = (Bloodhound) dataSet.getSource();
                String bhParams = bh.getConfig().isEmpty() ? "" : bh.getConfig().toJsonString();
                builder
                    .append(String.format("var %s = new Bloodhound(%s);", bh.getName(), bhParams));
                builder.append(String.format("%s.initialize();", bh.getName()));
            }
        }

        JQuery functionCall = $(this).chain("typeahead", config, config.getDatasets());
        builder.append(functionCall.get());

        if (selectBehavior != null) {

            CharSequence function = selectBehavior.getCallbackFunction(
                CallbackParameter.context("object"),
                CallbackParameter.converted("datum", "JSON.stringify(datum)"),
                CallbackParameter.explicit("name"));

            builder.append(String.format("$('#%s').bind('typeahead:selected', %s);", getMarkupId(), function));
        }

        return OnDomReadyHeaderItem.forScript(builder.toString());
    }

    private static class TypeaheadBehavior extends AbstractDefaultAjaxBehavior {

        private final TypeaheadEvent.Type type;

        public TypeaheadBehavior(TypeaheadEvent.Type type) {
            this.type = type;
        }

        @Override
        protected void respond(AjaxRequestTarget target) {

            RequestCycle requestCycle = getComponent().getRequestCycle();
            Request request = requestCycle.getRequest();
            IRequestParameters parameters = request.getRequestParameters();
            Component component = getComponent();

            try {
                JsonNode jsonNode = Json.parse(parameters.getParameterValue("datum").toString());
                component.send(component.getPage(), Broadcast.BREADTH, new TypeaheadEvent(type, jsonNode));
            } catch (IllegalStateException ise) {
                LOG.warn("Unable to get page for sending typeahead event");
            } catch (Json.ParseException pe) {
                LOG.warn("Unable to parse selected typeahead datum");
            }
        }
    }


    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "input");
        checkComponentTagAttribute(tag, "type", "text");
        tag.put("autocomplete", "off");
    }

}
