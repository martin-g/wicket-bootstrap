package de.agilecoders.wicket.core.markup.html.bootstrap.button;

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
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Renders a stable button link which can be cached in a web browser and used at a later time. The button
 * will be styled according to the given button type and size.
 * <p/>
 * <code>
 * // creates a big red bookmarkable page link that looks like a button
 * new BootstrapBookmarkablePageLink<Page>("componentId", Page.class, Type.Danger).setSize(Size.Large);
 * </code>
 *
 * @param <T>  The type of the model object for this link
 * @author miha
 */
public class BootstrapBookmarkablePageLink<T> extends BookmarkablePageLink<T> implements IBootstrapButton<BootstrapBookmarkablePageLink<T>>, Activatable {
    private static final long serialVersionUID = 1L;
    private Component label;
    private Icon icon;
    private ButtonBehavior buttonBehavior;
    private Component splitter;
    private final Buttons.Type type;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass   The class of page to link to
     * @param type        The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <P>         type of the page class
     */
    public <P extends Page> BootstrapBookmarkablePageLink(final String componentId, final Class<P> pageClass, final Buttons.Type type) {
        this(componentId, pageClass, new PageParameters(), type);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass   The class of page to link to
     * @param parameters  The parameters to pass to the new page when the link is clicked
     * @param type        The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <P>         type of the page class
     */
    public <P extends Page> BootstrapBookmarkablePageLink(final String componentId, final Class<P> pageClass, final PageParameters parameters, final Buttons.Type type) {
        super(componentId, pageClass, parameters);
        this.type = type;
        add(getButtonBehavior());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(getIcon(), getSplitter(), getPageLabel());
    }

    private ButtonBehavior getButtonBehavior() {
        if (buttonBehavior == null) {
            buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium);
        }
        return buttonBehavior;
    }

    private Icon getIcon() {
        if (icon == null) {
            icon = newIcon("icon");
        }
        return icon;
    }

    private Component getSplitter() {
        if (splitter == null) {
            splitter = newSplitter("splitter");
        }
        return splitter;
    }

    private Component getPageLabel() {
        if (label == null) {
            label = newLabel("label");
        }
        return label;
    }

    /**
     * creates a new icon component
     *
     * @param markupId the component id of the icon
     * @return new icon component
     */
    protected Icon newIcon(final String markupId) {
        return new Icon(markupId, (IconType) null);
    }

    /**
     * creates a new label component
     *
     * @param markupId the component id of the label
     * @return new label component
     */
    protected Component newLabel(final String markupId) {
        return new Label(markupId, new Model<>(""))
                .setRenderBodyOnly(true);
    }

    /**
     * creates a new splitter component. The splitter is visible only
     * if icon is visible.
     *
     * @param markupId the component id of the splitter
     * @return new splitter component
     */
    protected Component newSplitter(final String markupId) {
        return new WebMarkupContainer(markupId)
                .setRenderBodyOnly(true)
                .setEscapeModelStrings(false);
    }

    @Override
    protected final IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    /**
     * sets the size of the button according to the given {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size}.
     *
     * @param size the size of the button
     * @return reference to the current instance
     */
    @Override
    public BootstrapBookmarkablePageLink<T> setSize(Buttons.Size size) {
        getButtonBehavior().setSize(size);
        return this;
    }

    @Override
    public BootstrapBookmarkablePageLink<T> setType(Buttons.Type type) {
        getButtonBehavior().setType(type);
        return this;
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public BootstrapBookmarkablePageLink<T> setLabel(IModel<?> label) {
        getPageLabel().setDefaultModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return reference to the current instance
     */
    public BootstrapBookmarkablePageLink<T> setIconType(final IconType iconType) {
        getIcon().setType(iconType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        getSplitter().setVisible(getIcon().hasIconType() && !Strings.isEmpty(getPageLabel().getDefaultModelObjectAsString()));
    }

    @Override
    public boolean isActive(final Component button) {
        Args.notNull(button, "button");

        return button.getPage().getClass().equals(getPageClass());
    }
}
