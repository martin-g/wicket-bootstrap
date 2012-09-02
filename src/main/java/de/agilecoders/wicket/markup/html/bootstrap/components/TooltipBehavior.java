package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class TooltipBehavior extends BootstrapJavascriptBehavior {

    private boolean animate = false;
    private Placement placement = Placement.Bottom;
    private Trigger trigger = Trigger.Hover;
    private Duration delay = Duration.milliseconds(100);

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
        component.add(new AttributeModifier("rel", "tooltip"));
        component.add(new AttributeModifier("title", label.getObject()));
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(OnDomReadyHeaderItem.forScript("$('#" + component.getMarkupId() + "').tooltip(" + buildScript() + ")"));
    }

    protected JSONObject createJsonObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("placement", placement.name().toLowerCase());
        json.put("delay", delay.getMilliseconds());
        json.put("trigger", trigger.name().toLowerCase());

        if (animate) {
            json.put("animation", animate);
        }

        return json;
    }

    private String buildScript() {
        try {
            return createJsonObject().toString();
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

    public enum Trigger {
        Hover, Focus, Manual
    }

    public enum Placement {
        Top, Bottom, Left, Right
    }
}
