package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Default {@link Link} which is styled by bootstrap.
 * <p/>
 * <p>
 * You can use a link like:
 * <p/>
 *
 * <pre>
 * add(new BootstrapLink(&quot;myLink&quot;)
 * {
 *     public void onClick()
 *     {
 *         // do something here...
 *     }
 * );
 * </pre>
 * <p/>
 * and in your HTML file:
 * <p/>
 *
 * <pre>
 *  &lt;a href=&quot;#&quot; wicket:id=&quot;myLink&quot;&gt;click here&lt;/a&gt;
 * </pre>
 * <p/>
 * </p>
 * The following snippet shows how to pass a parameter from the Page creating the Page to the Page
 * responded by the Link.
 * <p/>
 *
 * <pre>
 * add(new BootstrapLink&lt;MyObject&gt;(&quot;link&quot;, listItem.getModel(), Type.Primary )
 * {
 *     public void onClick()
 *     {
 *         MyObject obj = getModelObject();
 *         setResponsePage(new MyPage(obj));
 *     }
 * </pre>
 *
 * @author miha
 */
public abstract class BootstrapLink<T> extends Link<T> implements IBootstrapButton<BootstrapLink<T>> {
    private static final long serialVersionUID = 1L;
    private Icon icon;
    private Component label;
    private Component splitter;
    private ButtonBehavior buttonBehavior;
    /** To use the splitter or not (true by default). */
    private boolean useSplitter = true;
    private final Buttons.Type type;

    /**
     * Construct.
     *
     * @param id the components id
     * @param model mandatory parameter
     */
    public BootstrapLink(final String id, final IModel<T> model) {
        this(id, model, Buttons.Type.Link);
    }

    /**
     * Construct.
     *
     * @param id the components id
     * @param type the type of the button
     */
    public BootstrapLink(final String id, final Buttons.Type type) {
        this(id, null, type);
    }

    /**
     * Construct.
     *
     * @param id The component id
     * @param model mandatory parameter
     * @param type the type of the button
     */
    public BootstrapLink(final String id, final IModel<T> model, final Buttons.Type type) {
        super(id, model);
        this.type = type;
        add(getButtonBehavior());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getIcon(), getSplitter(), getLinkLabel());
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

    private Component getLinkLabel() {
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
        return new Label(markupId, new Model<>("")).setRenderBodyOnly(true);
    }

    /**
     * creates a new splitter component. The splitter is visible only
     * if icon is visible and useSplitter is true.
     *
     * @param markupId the component id of the splitter
     * @return new splitter component
     */
    protected Component newSplitter(final String markupId) {
        return new WebMarkupContainer(markupId).setRenderBodyOnly(true).setEscapeModelStrings(false).setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (useSplitter) {
            getSplitter().setVisible(getIcon().hasIconType() && StringUtils.isNotEmpty(getLinkLabel().getDefaultModelObjectAsString()));
        }
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public BootstrapLink<T> setLabel(IModel<?> label) {
        getLinkLabel().setDefaultModel(label);
        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapLink<T> setIconType(IconType iconType) {
        getIcon().setType(iconType);
        return this;
    }

    /**
     * sets the size of the button
     *
     * @param size The button size
     * @return this instance for chaining
     */
    @Override
    public BootstrapLink<T> setSize(Buttons.Size size) {
        getButtonBehavior().setSize(size);
        return this;
    }

    /**
     * Sets the type of the button
     *
     * @param type The type of the button
     * @return this instance for chaining
     */
    @Override
    public BootstrapLink<T> setType(Buttons.Type type) {
        getButtonBehavior().setType(type);
        return this;
    }

    /**
     * Sets whether this button should display inline or block
     *
     * @param block <code>true</code>, for block mode
     * @return this instance for chaining
     */
    public BootstrapLink<T> setBlock(boolean block) {
        getButtonBehavior().setBlock(block);
        return this;
    }

    /**
     * @param value whether to use splitter between the icon and the label or not
     * @return this instance for chaining
     */
    public BootstrapLink<T> useSplitter(boolean value) {
        this.useSplitter = value;
        return this;
    }
}
