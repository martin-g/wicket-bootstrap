package de.agilecoders.wicket.extensions.slider;

/**
 * DoubleValue
 */
public class DoubleValue implements ISliderValue {

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
