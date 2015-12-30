package de.agilecoders.wicket.extensions.slider;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

/**
 * BootstrapSlider
 */
public class BootstrapSlider<T extends ISliderValue> extends TextField<T> {
    
    public static class BootstrapDoubleSliderConverter implements IConverter<ISliderValue>
    {
        @Override
        public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
            if(value.contains("[")) {
                return new DoubleRangeValue().fromString(value);
            } else {
                return new DoubleValue().fromString(value);
            }
        }

        @Override
        public String convertToString(ISliderValue value, Locale locale) {
            return value.toString();
        }
    }

    public static class BootstrapLongSliderConverter implements IConverter<ISliderValue>
    {
        @Override
        public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
            if(value.contains("[")) {
                return new LongRangeValue().fromString(value);
            } else {
                return new LongValue().fromString(value);
            }
        }

        @Override
        public String convertToString(ISliderValue value, Locale locale) {
            return value.toString();
        }
    }
    
    private  IConverter<ISliderValue> converter;
    
    public BootstrapSlider(String id, IModel<T> model, Class<T> typeClass)
    {
        super(id, model, typeClass);
        try {
            if(Double.class.isAssignableFrom(typeClass.newInstance().getNumberType())) {
                converter = new BootstrapDoubleSliderConverter();
            } else {
                converter = new BootstrapLongSliderConverter();
            }
        } catch (InstantiationException e) {
            throw new WicketRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new WicketRuntimeException(e);
        }
    }

    @Override
    public <C> IConverter<C> getConverter(final Class<C> type)
    {
        if (ISliderValue.class.isAssignableFrom(type))
        {
            return (IConverter<C>)converter;
        }
        else
        {
            return super.getConverter(type);
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
    }
}
