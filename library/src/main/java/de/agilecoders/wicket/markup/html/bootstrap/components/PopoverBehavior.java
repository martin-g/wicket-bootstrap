package de.agilecoders.wicket.markup.html.bootstrap.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.model.IModel;

/**
 * Add small overlays of content, like those on the iPad, to any element for housing secondary information.
 * Hover over the button to trigger the popover.
 *
 * @author miha
 * @version 1.0
 */
public class PopoverBehavior extends TooltipBehavior {

    private final IModel<String> body;

    /**
     * Construct.
     *
     * @param label Title of the popover
     */
    public PopoverBehavior(IModel<String> label) {
        this(label, null);
    }

    /**
     * Construct.
     *
     * @param label Title of the popover
     * @param body  Body of the popover
     */
    public PopoverBehavior(IModel<String> label, IModel<String> body) {
        super(label);
        placement(Placement.Right);
        trigger(Trigger.Click);
        this.body = body;
    }

    @Override
    protected void addAttributes(Component component) {
        super.addAttributes(component);
    	component.add(AttributeModifier.replace("rel", "popover"));
    }

    protected JSONObject createJsonObject() throws JSONException {
        JSONObject json = super.createJsonObject();
        if (body != null) {
        	json.put("content", body.getObject());
        }

        return json;
    }

    protected String buildScript() {
        try {
            return "popover(" + createJsonObject().toString() + ")";
        } catch (JSONException e) {
            throw new WicketRuntimeException(e);
        }
    }

    
}
