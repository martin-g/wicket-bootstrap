package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

/**
 *
 */
public class LaddaAjaxButton extends BootstrapAjaxButton {

    private final LaddaBehavior laddaBehavior = new LaddaBehavior();

    public LaddaAjaxButton(String componentId, Buttons.Type type) {
        super(componentId, type);
    }

    public LaddaAjaxButton(String componentId, IModel<String> model, Buttons.Type type) {
        super(componentId, model, type);
    }

    public LaddaAjaxButton(String id, Form<?> form, Buttons.Type type) {
        super(id, form, type);
    }

    public LaddaAjaxButton(String id, IModel<String> model, Form<?> form, Buttons.Type type) {
        super(id, model, form, type);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(laddaBehavior);
    }

    public LaddaAjaxButton withStyle(LaddaBehavior.Effect effect) {
        this.laddaBehavior.withStyle(effect);
        return this;
    }

    public LaddaAjaxButton withSpinnerColor(String color) {
        this.laddaBehavior.withSpinnerColor(color);
        return this;
    }

    public LaddaAjaxButton withSpinnerSize(int size) {
        this.laddaBehavior.withSpinnerSize(size);
        return this;
    }
    
    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new LaddaAjaxCallListener());
    }

    @Override
    protected Component newLabel(String markupId, IModel<String> model) {
        Component label = super.newLabel(markupId, model);
        label.add(AttributeModifier.append("class", "ladda-label"));
        return label;
    }
}
