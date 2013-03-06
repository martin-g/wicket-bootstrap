package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
abstract public class InputBorder extends Border {
    private InputBorder(String id) {
        super(id);

        add(newCssClassNameAppender());
    }

    public InputBorder(String id, IconType iconType) {
        this(id);

        addComponent(new Icon(id(), iconType));
    }

    public InputBorder(String id, IModel<String> label) {
        this(id);

        addComponent(new Label(id(), label));
    }

    public InputBorder(String id, Component component) {
        this(id);

        addComponent(component);
    }


    protected final void addComponent(Component component) {
        if (!id().equals(component.getId())) {
            throw new IllegalArgumentException("invalid markup id. Must be " + id() + "instead of " + component.getId());
        }

        component.add(new CssClassNameAppender("add-on"));
        add(component);
    }

    protected abstract String id();

    protected abstract CssClassNameAppender newCssClassNameAppender();
}
