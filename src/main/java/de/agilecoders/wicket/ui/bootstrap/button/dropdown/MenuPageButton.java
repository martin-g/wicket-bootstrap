package de.agilecoders.wicket.ui.bootstrap.button.dropdown;

import de.agilecoders.wicket.ui.bootstrap.image.Icon;
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
public class MenuPageButton<T extends Page> extends BookmarkablePageLink<T> {
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
        super("menuElement", pageClass, parameters);

        this.icon = new Icon("icon", Icon.Type.NULL);

        this.label = new Label("label", new Model<>(""));
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
}
