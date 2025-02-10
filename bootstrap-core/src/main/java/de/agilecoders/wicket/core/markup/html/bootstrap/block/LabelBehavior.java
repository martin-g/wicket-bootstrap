package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * #### Description
 *
 * A Label is a highlighted text with rounded corners. The {@link de.agilecoders.wicket.core.markup.html.bootstrap.block.LabelType}
 * defines the color of a labels background. The tag name of a label must be `span`.
 *
 * documentation: http://getbootstrap.com/components/#labels
 *
 * #### Usage
 *
 * ```java
 * // use constructor to set type:
 * component.add(new LabelBehavior(LabelType.Success));
 * // use setter to set type:
 * component.add(new LabelBehavior()
 *                  .setType(LabelType.Success));
 * ```
 *
 * ```html
 * {@code <span wicket:id="componentId">content</span>}
 * ```
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
