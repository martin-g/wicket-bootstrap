package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.IntegerRangeValue;
import de.agilecoders.wicket.extensions.slider.util.IntegerValue;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

class BootstrapIntegerSliderConverter implements IConverter<ISliderValue> {
    @Override
    public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
        if (value.contains("[")) {
            return new IntegerRangeValue().fromString(value);
        } else {
            return new IntegerValue().fromString(value);
        }
    }

    @Override
    public String convertToString(ISliderValue value, Locale locale) {
        return value.toString();
    }
}
