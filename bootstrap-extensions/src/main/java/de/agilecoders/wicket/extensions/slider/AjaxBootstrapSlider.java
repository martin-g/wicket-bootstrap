package de.agilecoders.wicket.extensions.slider;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * AjaxBootstrapSlider: ajaxified version of Slider.
 */
public class AjaxBootstrapSlider<T extends ISliderValue, N extends Number> extends BootstrapSlider<T,N> {

    private AbstractDefaultAjaxBehavior behavior;
    
    public enum SliderEvent {
        slide,	//This event fires when the slider is dragged        
        slideStart,	//This event fires when dragging starts	The new slider value
        slideStop,	//This event fires when the dragging stops or has been clicked on	The new slider value
        change 
    }
    
    public interface EventHandler<T extends ISliderValue> extends Serializable {
        
        public void onAjaxEvent(AjaxRequestTarget target, T newValue);
    }
    
    private final Map<SliderEvent, EventHandler> handlers = new HashMap<SliderEvent, EventHandler>();
    
    public AjaxBootstrapSlider(String id, IModel<T> model, Class<T> typeClass) {
        super(id, model, typeClass);

        behavior = new AbstractDefaultAjaxBehavior() {
            @Override
            protected void respond(AjaxRequestTarget target) {
                SliderEvent event = SliderEvent.valueOf(RequestCycle.get().getRequest().getRequestParameters().getParameterValue("event").toString());
                AjaxBootstrapSlider.EventHandler<T> handler = handlers.get(event);
                T value = (T)newInstance().fromString(RequestCycle.get().getRequest().getRequestParameters().getParameterValue("value").toString());
                getModel().setObject(value);
                if(handler != null) {
                    handler.onAjaxEvent(target, value);
                }
            }
        };        
        add(behavior);
    }
    
    public AjaxBootstrapSlider addHandler(SliderEvent event, EventHandler<T> handler) {
        handlers.put(event, handler);
        return this;
    }

    @Override
    protected void configEvents(StringBuilder builder) {
        CharSequence url = behavior.getCallbackUrl();
        for(SliderEvent event: handlers.keySet()) {
            builder.append(".on('").append(event.name()).append("',").append("function(value) {\n");
            builder.append("var ep={};\n").append("ep['event']='").append(event.name()).append("';\n");
            builder.append("ep['value']=value['value'];\n");
            builder.append("Wicket.Ajax.get({'u': '").append(url).append("', 'ep': ep").append("});");
            builder.append("\n})");
        }
    }
}
