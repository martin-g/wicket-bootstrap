package de.agilecoders.wicket.extensions.slider;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import java.util.HashMap;
import java.util.Map;

/**
 * AjaxBootstrapSlider
 */
public class AjaxBootstrapSlider<T extends ISliderValue, N extends Number> extends BootstrapSlider<T,N> {

    private AbstractDefaultAjaxBehavior behavior;
    
    public static enum SliderEvent {
        slide,	//This event fires when the slider is dragged        
        slideStart,	//This event fires when dragging starts	The new slider value
        slideStop,	//This event fires when the dragging stops or has been clicked on	The new slider value
        change 
    }
    
    public static interface EventHandler<T extends ISliderValue> {
        
        public void onAjaxEvent(AjaxRequestTarget target, T newValue);
    }
    
    private Map<SliderEvent, EventHandler> handlers = new HashMap<SliderEvent, EventHandler>();
    
    public AjaxBootstrapSlider(String id, IModel<T> model, Class<T> typeClass) {
        super(id, model, typeClass);

        behavior = new AbstractDefaultAjaxBehavior() {
            @Override
            protected void respond(AjaxRequestTarget target) {
                
            }
        };        
        add(behavior);
    }
    
    public AjaxBootstrapSlider addHandler(SliderEvent event, EventHandler<T> handler) {
        handlers.put(event, handler);
        return this;
    }
}
