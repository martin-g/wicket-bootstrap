package de.agilecoders.wicket.extensions.slider;

import java.io.Serializable;

/**
 * ISliderValue represents a value for the slider component: either a numeric value or a range [a,b],
 * It know how to represent itself as a String and read itself form a String.
 */
public interface ISliderValue extends Serializable {
    
    ISliderValue fromString(String value);
    
    String toString();
    
    Class<? extends Number> getNumberType();
}
