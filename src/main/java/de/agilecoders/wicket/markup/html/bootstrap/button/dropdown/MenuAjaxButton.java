package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 * TODO: not functional
 *
 * @author miha
 * @version 1.0
 */
@Deprecated
public abstract class MenuAjaxButton<T> extends AjaxLink<T> {

    private Icon icon;
    private Label label;
    private final IModel<String> model;

    public MenuAjaxButton(IModel<String> model) {
        super("menuElement");
        this.model = model;

        this.icon = new Icon("icon", IconType.NULL);
        this.setEscapeModelStrings(false);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        String body = model.getObject();

        if (!IconType.NULL.equals(icon.type())) {
            final String invertPostfix = icon.isInverted() ? "icon-white" : "";

            body = "<i class='" + icon.type().cssClassName() + " " + invertPostfix + "'></i> " + body;
        }

        setBody(Model.of(body));
    }

    public MenuAjaxButton setIcon(Icon icon) {
        if (!"icon".equals(icon.getId())) {
            throw new MarkupException("icon must use 'icon' as markup id. Please use 'new Icon(IconType)' to create your Icon instance.");
        }

        this.icon = icon;

        return this;
    }

}
