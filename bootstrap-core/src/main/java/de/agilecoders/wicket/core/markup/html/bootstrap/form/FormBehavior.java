package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;

import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FormBehavior extends BootstrapBaseBehavior {

    private FormType type;

    public FormBehavior() {
        this(FormType.Default);
    }

    public FormBehavior(FormType type) {
        this.type = type;
    }

    public FormBehavior type(FormType type) {
        this.type = type;
        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(type.newCssClassNameModifier());
    }
}
