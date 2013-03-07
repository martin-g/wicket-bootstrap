package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A Label is a highlighted text with rounded corners.
 *
 * @author miha
 */
public class LabelBehavior extends Behavior {
    private final IModel<LabelType> type;

    /**
     * Construct.
     */
    public LabelBehavior() {
        this(LabelType.Default);
    }

    /**
     * Construct.
     *
     * @param type The type of the label
     */
    public LabelBehavior(final LabelType type) {
        this(Model.of(type));
    }

    /**
     * Construct.
     *
     * @param type The type of the label as model
     */
    public LabelBehavior(final IModel<LabelType> type) {
        super();

        this.type = type;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
        Attributes.addClass(tag, className(), getType().cssClassName(className()));
    }

    /**
     * @return current label type
     */
    public final LabelType getType() {
        return type.getObject();
    }

    /**
     * sets the label type
     *
     * @param type the label type
     * @return this instance
     */
    public final LabelBehavior setType(final LabelType type) {
        this.type.setObject(type);

        return this;
    }

    /**
     * @return the css class name as string
     */
    protected String className() {
        return "label";
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
    }
}
