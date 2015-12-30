package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * Range
 */
public class LongRangeValue implements ISliderValue {

    private long min;
    private long max;

    public LongRangeValue() {
    }

    public LongRangeValue(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ISliderValue fromString(String value) {
        String toParser = value.substring(1, value.length()-1);
        String[] values = toParser.split(",");
        return new LongRangeValue(Long.parseLong(values[0]),Long.parseLong(values[1]));
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Long.class;
    }

    @Override
    public String toString() {
        return "["+min+","+max+"]";
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongRangeValue)) return false;

        LongRangeValue that = (LongRangeValue) o;

        if (max != that.max) return false;
        if (min != that.min) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (min ^ (min >>> 32));
        result = 31 * result + (int) (max ^ (max >>> 32));
        return result;
    }
}
