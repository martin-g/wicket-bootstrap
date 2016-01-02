package de.agilecoders.wicket.extensions.slider.util;

import org.apache.wicket.model.IModel;

/**
 * NumericModel: wrapper model around a numeric model (it bridges with internal slider representation of data).
 */
public class NumericModel<T extends Number> implements IModel<INumericValue<T>> {

    private IModel<T> model;
    
    public NumericModel(IModel<T> model) {
        this.model = model;
    }

    @Override
    public INumericValue<T> getObject() {
        INumericValue<T> value =  (INumericValue<T>)getValue(model.getObject().getClass());
        value.setValue(model.getObject());
        return value;
    }

    @Override
    public void setObject(INumericValue<T> object) {
        model.setObject(object.getValue());
        
    }

    @Override
    public void detach() {
        model.detach();
    }
    
    public static INumericValue<?> getValue(Class<?> typeClass) {
        if(Double.class.isAssignableFrom(typeClass)) {
            return new DoubleValue();
        }
        if(Long.class.isAssignableFrom(typeClass)) {
            return new LongValue();
        }
        if(Integer.class.isAssignableFrom(typeClass)) {
            return new IntegerValue();
        }
        return new DoubleValue();
    }
}
