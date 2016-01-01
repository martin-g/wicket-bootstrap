package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 * Range
 */
public class DoubleRangeValue implements ISliderValue {

    private double min;
    private double max;

    public DoubleRangeValue() {
    }

    public DoubleRangeValue(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ISliderValue fromString(String value) {
        String toParser = value.indexOf('[')>=0 ? value.substring(1, value.length()-1): value;
        String[] values = toParser.split(",");
        return new DoubleRangeValue(Double.parseDouble(values[0]),Double.parseDouble(values[1]));
    }

    @Override
    public Class<? extends Number> getNumberType() {
        return Double.class;
    }

    @Override
    public String toString() {
        return "["+min+","+max+"]";
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
    
}
