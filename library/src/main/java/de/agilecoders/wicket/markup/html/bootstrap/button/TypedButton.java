package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A bootstrap styled {@link Button}
 *
 * @author miha
 */
public class TypedButton extends Button implements BootstrapButton<TypedButton> {

    private final Icon icon;
    private final Label label;
    private final Component splitter;
    private final ButtonBehavior buttonBehavior;

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param buttonType  The type of the button
     */
    public TypedButton(final String componentId, final ButtonType buttonType) {
        this(componentId, new Model<String>(), buttonType);
    }

    /**
     * Construct.
     *
     * @param componentId The component id
     * @param model       The label model
     * @param buttonType  The type of the button
     */
    public TypedButton(final String componentId, final IModel<String> model, final ButtonType buttonType) {
        super(componentId, model);

        add(buttonBehavior = new ButtonBehavior(buttonType, ButtonSize.Medium));
        add(icon = new Icon("icon", IconType.NULL).invert());
        add(splitter = new WebMarkupContainer("splitter"));

        this.label = new Label("label", model);
        this.label.setRenderBodyOnly(true);
        add(label);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        splitter.setVisible(icon.hasIconType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    /**
     * sets the label of the button.
     *
     * @param label the new button label
     * @return reference to the current instance
     */
    public TypedButton setLabel(IModel<?> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return reference to the current instance
     */
    public TypedButton setIconType(final IconType iconType) {
        this.icon.setType(iconType);

        return this;
    }

    public TypedButton setSize(ButtonSize buttonSize) {
        this.buttonBehavior.withSize(buttonSize);

        return this;
    }

    @Override
    public TypedButton setType(ButtonType buttonType) {
        this.buttonBehavior.withType(buttonType);

        return this;
    }

}
