package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.DoubleRangeValue;
import de.agilecoders.wicket.extensions.slider.util.DoubleValue;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

class BootstrapDoubleSliderConverter implements IConverter<ISliderValue> {
    @Override
    public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
        if (value.contains("[")) {
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
