package de.agilecoders.wicket.markup.html.bootstrap.border;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CssClassNameBorder<T extends CssClassNameProvider> extends Border {
    private T provider;

    public CssClassNameBorder(String id) {
        super(id);
    }

    public CssClassNameBorder(String id, IModel<?> model) {
        super(id, model);
    }

    public CssClassNameBorder<T> withCssClassNameProvider(T provider) {
        this.provider = provider;

        return this;
    }

    public T cssClassNameProvider() {
        return provider;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if(provider != null) {
            add(provider.newCssClassNameModifier());
        }
    }
}
