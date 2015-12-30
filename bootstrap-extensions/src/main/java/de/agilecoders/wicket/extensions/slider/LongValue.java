package de.agilecoders.wicket.extensions.slider;

/**
 * DoubleValue
 */
public class LongValue implements ISliderValue {

    private long value;

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

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
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

        if (value != longValue.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
