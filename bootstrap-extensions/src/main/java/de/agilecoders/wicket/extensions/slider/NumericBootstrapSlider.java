package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.*;
import org.apache.wicket.model.IModel;

/**
 * NumericBootstrapSlider: version of slider that works with numeric values.
 */
public class NumericBootstrapSlider<T extends Number> extends BootstrapSlider<INumericValue, T> {

    public NumericBootstrapSlider(String id, IModel<T> model) {
        super(id, new NumericModel(model), (Class<INumericValue>)getSliderClass(model.getObject().getClass()));
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
