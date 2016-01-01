package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.*;
import org.apache.wicket.model.IModel;

/**
 * AjaxNumericBootstrapSlider
 */
public class AjaxNumericBootstrapSlider<T extends Number> extends AjaxBootstrapSlider<INumericValue<T>, T> {

    public AjaxNumericBootstrapSlider(String id, IModel<T> model) {
        super(id, new NumericModel(model), (Class<INumericValue<T>>)getSliderClass(model.getObject().getClass()));
    }
    
    private static  Class<? extends INumericValue> getSliderClass(Class<?> typeClass) {
        if(Double.class.isAssignableFrom(typeClass)) {
            return DoubleValue.class;
        } else if(Integer.class.isAssignableFrom(typeClass)) {
            return IntegerValue.class;
        } else {
            return LongValue.class;
        }
        
    }
}
