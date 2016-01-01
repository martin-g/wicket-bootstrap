package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * Range
 */
public class IntegerRangeValue implements ISliderValue {

    private int min;
    private int max;

    public IntegerRangeValue() {
    }

    public IntegerRangeValue(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ISliderValue fromString(String value) {
        String toParser = value.indexOf('[')>=0 ? value.substring(1, value.length()-1): value;
        String[] values = toParser.split(",");
        return new IntegerRangeValue(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Integer.class;
    }

    @Override
    public String toString() {
        return "["+min+","+max+"]";
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerRangeValue)) return false;

        IntegerRangeValue that = (IntegerRangeValue) o;

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
