package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * LongValue
 */
public class LongValue implements ISliderValue, INumericValue<Long> {

    private Long value;

    public LongValue() {
    }

    public LongValue(long value) {
        this.value = value;
    }

    @Override
    public ISliderValue fromString(String value) {
        return new LongValue(Long.parseLong(value));
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Long.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongValue)) return false;

        LongValue longValue = (LongValue) o;

        if (!value.equals(longValue.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
