package de.agilecoders.wicket.extensions.slider;

import de.agilecoders.wicket.extensions.slider.util.*;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.extensions.slider.NumericBootstrapSlider.getSliderClass;

/**
 * AjaxNumericBootstrapSlider: version of Ajaxified slider that makes easier to work with numeric values.
 */
public class AjaxNumericBootstrapSlider<T extends Number> extends AjaxBootstrapSlider<INumericValue<T>, T> {

    public AjaxNumericBootstrapSlider(String id, IModel<T> model) {
        super(id, new NumericModel(model), (Class<INumericValue<T>>)getSliderClass(model.getObject().getClass()));
    }
}
