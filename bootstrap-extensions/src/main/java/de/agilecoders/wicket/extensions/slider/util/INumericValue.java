package de.agilecoders.wicket.extensions.slider.util;

import de.agilecoders.wicket.extensions.slider.ISliderValue;

/**
 *INumericValue: a numeric ISlider value
 */
public interface INumericValue<T extends Number> extends ISliderValue {

    public T getValue();

    public void setValue(T value);
}
