package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import java.io.Serializable;

import org.apache.wicket.core.util.lang.PropertyResolver;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

public class DefaultRadioChoiceRenderer<T extends Serializable> implements IRadioChoiceRenderer<T> {

    private Buttons.Type type;
    private String propertyLabel;

    public DefaultRadioChoiceRenderer(Buttons.Type type, String propertyLabel) {
        this.type = type!=null?type: Buttons.Type.Default;
        this.propertyLabel = propertyLabel;
    }

    /* (non-Javadoc)
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    @Override
    public void detach() {
        // NOP
    }

    /* (non-Javadoc)
     * @see de.agilecoders.wicket.core.markup.html.bootstrap.form.IRadioChoiceRenderer#modelOf(java.lang.Object)
     */
    @Override
    public IModel<T> modelOf(T option) {
        return Model.of(option);
    }

    /* (non-Javadoc)
     * @see de.agilecoders.wicket.core.markup.html.bootstrap.form.IRadioChoiceRenderer#lableOf(java.lang.Object)
     */
    @Override
    public IModel<String> lableOf(T option) {
        return Model.of(propertyLabel!= null? PropertyResolver.getValue(propertyLabel, option).toString() : option.toString());
    }

    /* (non-Javadoc)
     * @see de.agilecoders.wicket.core.markup.html.bootstrap.form.IRadioChoiceRenderer#getButtonClass(java.lang.Object)
     */
    @Override
    public String getButtonClass(T option) {
        return type.cssClassName();
    }

}
