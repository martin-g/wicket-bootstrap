package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;

/**
 * Default {@link AjaxLink} which is styled by bootstrap
 *
 * @author miha
 */
public abstract class BootstrapAjaxLink<T> extends AjaxLink<T> implements IBootstrapButton<BootstrapAjaxLink<T>> {

    private final Icon icon;
    private final Label label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     *
     * @param id the components id
     * @param type  the type of the button
     */
    public BootstrapAjaxLink(final String id, final Buttons.Type type) {
        this(id, null, type);
    }

    /**
     * Construct.
     *
     * @param id         The component id
     * @param model      mandatory parameter
     * @param type the type of the button
     */
    public BootstrapAjaxLink(String id, IModel<T> model, Buttons.Type type) {
        super(id, model);

        add(buttonBehavior = new ButtonBehavior(type, Buttons.Size.Medium));
        add(icon = new Icon("icon", IconType.NULL).invert());
        add(splitter = new WebMarkupContainer("splitter"));

        this.label = new Label("label", model);
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

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType());
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public BootstrapAjaxLink<T> setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon type
     * @return reference to the current instance
     */
    public BootstrapAjaxLink<T> setIconType(IconType iconType) {
        icon.setType(iconType);

        return this;
    }

    public BootstrapAjaxLink<T> setSize(Buttons.Size size) {
        buttonBehavior.withSize(size);

        return this;
    }

    public BootstrapAjaxLink<T> setType(Buttons.Type type) {
        this.buttonBehavior.withType(type);

        return this;
    }

    public BootstrapAjaxLink<T> setInverted(final boolean inverted) {
        icon.setInverted(inverted);
        return this;
    }
}
