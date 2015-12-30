package de.agilecoders.wicket.extensions.slider;

import java.io.Serializable;

/**
 * ISliderValue
 */
public interface ISliderValue extends Serializable {
    
    ISliderValue fromString(String value);
    
    String toString();
    
    Class<? extends Number> getNumberType();
}
