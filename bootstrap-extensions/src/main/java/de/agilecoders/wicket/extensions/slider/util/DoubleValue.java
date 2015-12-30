package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * DoubleValue
 */
public class DoubleValue implements ISliderValue, INumericValue<Double> {

    private double value;

    public DoubleValue() {
    }

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public ISliderValue fromString(String value) {
        return new DoubleValue(Double.parseDouble(value));
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Double.class;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
