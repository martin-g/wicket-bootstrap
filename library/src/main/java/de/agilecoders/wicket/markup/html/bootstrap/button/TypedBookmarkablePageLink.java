package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
 * new TypedBookmarkablePageLink<Page>("componentId", Page.class, ButtonType.Danger).setSize(ButtonSize.Large);
 * </code>
 *
 * @author miha
 */
public class TypedBookmarkablePageLink<T> extends BookmarkablePageLink<T> implements BootstrapButton<TypedBookmarkablePageLink<T>>, Activatable, Invertible {

    private final Label label;
    private final Icon icon;
    private final ButtonBehavior buttonBehavior;
    private final Component splitter;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass   The class of page to link to
     * @param buttonType  The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <T>         type of the page class
     */
    public <T extends Page> TypedBookmarkablePageLink(final String componentId, final Class<T> pageClass, final ButtonType buttonType) {
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
    public <T extends Page> TypedBookmarkablePageLink(final String componentId, final Class<T> pageClass, final PageParameters parameters, final ButtonType buttonType) {
        super(componentId, pageClass, parameters);

        add(buttonBehavior = new ButtonBehavior(buttonType, ButtonSize.Medium));
        add(icon = new Icon("icon", IconType.NULL));

        add(splitter = new WebMarkupContainer("splitter"));
        this.splitter.setRenderBodyOnly(true).setEscapeModelStrings(false);

        this.label = new Label("label", new Model<String>(""));
        this.label.setRenderBodyOnly(true);
        add(label);
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
    public TypedBookmarkablePageLink<T> setSize(ButtonSize buttonSize) {
        this.buttonBehavior.withSize(buttonSize);

        return this;
    }

    @Override
    public TypedBookmarkablePageLink<T> setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public TypedBookmarkablePageLink<T> setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return reference to the current instance
     */
    public TypedBookmarkablePageLink<T> setIconType(final IconType iconType) {
        this.icon.setType(iconType);

        return this;
    }

    /**
     * @return current button behavior
     */
    protected final ButtonBehavior getButtonBehavior() {
        return buttonBehavior;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType());
    }

    @Override
    public boolean isActive(Component button) {
        return button.getPage().getClass().equals(getPageClass());
    }

    @Override
    public void setInverted(boolean inverted) {
        icon.setInverted(inverted);
    }
}
