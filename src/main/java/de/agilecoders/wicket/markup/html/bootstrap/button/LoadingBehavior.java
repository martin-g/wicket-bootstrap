package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class LoadingBehavior extends BootstrapJavascriptBehavior {

    private final IModel<String> model;

    public LoadingBehavior(IModel<String> model) {
        this.model = model;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new AttributeAppender("onclick", "$('#" + component.getMarkupId() + "').button('loading');"));
        component.setOutputMarkupId(true);
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(new AttributeModifier("data-loading-text", model));
    }
}
