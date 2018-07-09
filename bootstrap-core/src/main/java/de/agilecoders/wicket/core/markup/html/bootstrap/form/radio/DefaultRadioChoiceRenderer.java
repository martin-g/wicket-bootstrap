package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import java.io.Serializable;

import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

public class DefaultRadioChoiceRenderer<T extends Serializable> implements IRadioChoiceRenderer<T> {

    private final Buttons.Type type;

    private final String propertyLabel;

    public DefaultRadioChoiceRenderer(Buttons.Type type, String propertyLabel) {
        this.type = type != null ? type : Buttons.Type.Secondary;
        this.propertyLabel = propertyLabel;
    }

    @Override
    public void detach() {
        // NOP
    }

    @Override
    public IModel<T> modelOf(T option) {
        return Model.of(option);
    }

    @Override
    public IModel<String> lableOf(T option) {
        String label = option != null ? option.toString() : "";
        if (propertyLabel != null) {
            Object value = PropertyResolver.getValue(propertyLabel, option);
            if (value != null) {
                label = value.toString();
            }
        }
        return Model.of(label);
    }

    @Override
    public String getButtonClass(T option) {
        return type.cssClassName();
    }
}
