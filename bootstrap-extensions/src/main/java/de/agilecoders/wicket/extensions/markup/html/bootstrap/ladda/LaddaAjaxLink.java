package de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

/**
 *
 */
public abstract class LaddaAjaxLink<T> extends BootstrapAjaxLink<T> {

    private final LaddaBehavior laddaBehavior = new LaddaBehavior();
    
    public LaddaAjaxLink(String id, Buttons.Type type) {
        super(id, type);
    }

    public LaddaAjaxLink(String id, IModel<T> model, Buttons.Type type) {
        super(id, model, type);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(laddaBehavior);
    }

    public LaddaAjaxLink<T> withStyle(LaddaBehavior.Style style) {
        this.laddaBehavior.withStyle(style);
        return this;
    }

    public LaddaAjaxLink<T> withSize(LaddaBehavior.Size size) {
        this.laddaBehavior.withSize(size);
        return this;
    }

    public LaddaAjaxLink<T> withSpinnerColor(String color) {
        this.laddaBehavior.withSpinnerColor(color);
        return this;
    }

    public LaddaAjaxLink<T> withSpinnerSize(int size) {
        this.laddaBehavior.withSpinnerSize(size);
        return this;
    }


    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        attributes.getAjaxCallListeners().add(new LaddaAjaxCallListener());
    }

    @Override
    protected Component newLabel(String markupId, IModel<T> model) {
        Component label = super.newLabel(markupId, model);
        label.setRenderBodyOnly(false);
        label.add(AttributeModifier.append("class", "ladda-label"));
        return label;
    }
}
