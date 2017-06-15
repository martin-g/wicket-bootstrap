package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.LongRangeValue;
import de.agilecoders.wicket.extensions.slider.util.LongValue;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

class BootstrapLongSliderConverter implements IConverter<ISliderValue> {
    @Override
    public ISliderValue convertToObject(String value, Locale locale) throws ConversionException {
        if (value.contains("[")) {
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
