package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * DoubleValue
 */
public class IntegerValue implements ISliderValue, INumericValue<Integer> {

    private int value;

    public IntegerValue() {
    }

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public ISliderValue fromString(String value) {
        return new IntegerValue(Integer.parseInt(value));
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Integer.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerValue)) return false;

        IntegerValue longValue = (IntegerValue) o;

        if (value != longValue.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
