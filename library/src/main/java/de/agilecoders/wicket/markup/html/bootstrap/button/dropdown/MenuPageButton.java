package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MenuPageButton<T extends Page> extends BookmarkablePageLink<T> implements Activatable {
    private Icon icon;
    private Label label;

    public <T extends Page> MenuPageButton(Class<T> pageClass) {
        this(pageClass, new PageParameters());
    }

    public <T extends Page> MenuPageButton(Class<T> pageClass, PageParameters parameters, IModel<String> label) {
        this(pageClass, parameters);

        setLabel(label);
    }

    public <T extends Page> MenuPageButton(Class<T> pageClass, IModel<String> label) {
        this(pageClass, new PageParameters());

        setLabel(label);
    }

    public <T extends org.apache.wicket.Page> MenuPageButton(Class<T> pageClass, PageParameters parameters) {
        super(ButtonList.getButtonMarkupId(), pageClass, parameters);

        this.icon = new Icon("icon", IconType.NULL);

        this.label = new Label("label", new Model<String>(""));
        this.label.setRenderBodyOnly(true);
    }

    public MenuPageButton<T> setLabel(IModel<String> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(icon);
        add(label);
    }

    public MenuPageButton<T> setIcon(Icon icon) {
        this.icon = icon;

        return this;
    }

    @Override
    public boolean isActive(Component button) {
        return getPageClass().equals(button.getPage().getClass());
    }
}
