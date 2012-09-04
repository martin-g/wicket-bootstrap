package de.agilecoders.wicket.markup.html.bootstrap.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class TooltipBehavior extends BootstrapJavascriptBehavior {

    private boolean animate = false;
    private Placement placement = Placement.Top;
    private Trigger trigger = Trigger.Hover;
    private Duration delay = Duration.NONE;
    private boolean html = true;

    private final IModel<String> label;

    public TooltipBehavior(IModel<String> label) {
        this.label = label;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
        addAttributes(component);
    }

    protected void addAttributes(final Component component) {
        //rel="tooltip" title="first tooltip"
        component.add(AttributeModifier.replace("rel", "tooltip"));
        if (label != null) {
        	component.add(AttributeModifier.replace("title", label.getObject()));
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(OnDomReadyHeaderItem.forScript("$('#" + component.getMarkupId() + "')." + buildScript()));
    }

    protected JSONObject createJsonObject() throws JSONException {
        JSONObject json = new JSONObject();

        if (!html) { // default true, so only add if not default
        	json.put("html", html);
        }

        if (placement != null) {
        	json.put("placement", placement.name().toLowerCase());
        }
        if (delay != null) {
        	json.put("delay", delay.getMilliseconds());
        }
        if (trigger != null) {
        	json.put("trigger", trigger.name().toLowerCase());
        }

        if (!animate) { // default true, so only add if not default
            json.put("animation", animate);
        }

        return json;
    }

    protected String buildScript() {
        try {
            return "tooltip(" + createJsonObject().toString() + ")";
        } catch (JSONException e) {
            throw new WicketRuntimeException(e);
        }
    }

    public boolean isAnimated() {
        return animate;
    }

    public TooltipBehavior animate(boolean animate) {
        this.animate = animate;
        return this;
    }

    public Placement placement() {
        return placement;
    }

    public TooltipBehavior placement(Placement placement) {
        this.placement = placement;
        return this;
    }

    public Trigger trigger() {
        return trigger;
    }

    public TooltipBehavior trigger(Trigger trigger) {
        this.trigger = trigger;
        return this;
    }

    public Duration delay() {
        return delay;
    }

    public TooltipBehavior delay(Duration delay) {
        this.delay = delay;
        return this;
    }

    public TooltipBehavior html(boolean html) {
        this.html = html;
        return this;
    }

    public enum Trigger {
        Click, Hover, Focus, Manual
    }

    public enum Placement {
        Top, Bottom, Left, Right
    }
}
