package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Renders a stable button link which can be cached in a web browser and used at a later time. The button
 * will be styled according to the given button type and size.
 * <p/>
 * <code>
 * // creates a big red button
 * new TypedPageButton<Page>("componentId", Page.class, ButtonType.Danger).setSize(ButtonSize.Large);
 * </code>
 *
 * @author miha
 * @version 1.0
 */
public class TypedPageButton<T> extends BookmarkablePageLink<T> implements BootstrapButton<TypedPageButton>, Bookmarkable {

    private final Label label;

    // TODO: should be immutable
    private Icon icon;
    private final ButtonBehavior buttonBehavior;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass   The class of page to link to
     * @param buttonType  The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <T>         type of the page class
     */
    public <T extends Page> TypedPageButton(final String componentId, final Class<T> pageClass, final ButtonType buttonType) {
        this(componentId, pageClass, new PageParameters(), buttonType);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass   The class of page to link to
     * @param parameters  The parameters to pass to the new page when the link is clicked
     * @param buttonType  The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <T>         type of the page class
     */
    public <T extends Page> TypedPageButton(final String componentId, final Class<T> pageClass, final PageParameters parameters, final ButtonType buttonType) {
        super(componentId, pageClass, parameters);

        buttonBehavior = new ButtonBehavior(buttonType, ButtonSize.Medium);

        this.icon = new Icon("icon", IconType.NULL);

        this.label = new Label("label", new Model<String>(""));
        this.label.setRenderBodyOnly(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    /**
     * sets the size of the button according to the given {@link ButtonSize}.
     *
     * @param buttonSize the size of the button
     * @return reference to the current instance
     */
    public TypedPageButton setSize(ButtonSize buttonSize) {
        this.buttonBehavior.withSize(buttonSize);

        return this;
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public TypedPageButton<T> setLabel(IModel<String> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param icon the new button icon
     * @return reference to the current instance
     */
    public TypedPageButton<T> setIcon(Icon icon) {
        this.icon = icon.invert();

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(icon, label);
    }
}
