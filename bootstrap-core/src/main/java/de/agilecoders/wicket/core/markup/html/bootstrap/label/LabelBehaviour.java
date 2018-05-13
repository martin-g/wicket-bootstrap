package de.agilecoders.wicket.core.markup.html.bootstrap.label;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default label behavior that controls the size and type
 * of a label.
 *
 * @author drummer
 */
public class LabelBehaviour extends BootstrapBaseBehavior {

    private final IModel<Labels.Type> labelType;

    public LabelBehaviour() {
        this(Labels.Type.DEFAULT);
    }

    public LabelBehaviour(Labels.Type type) {
        this(Model.of(type));
    }

    public LabelBehaviour(IModel<Labels.Type> type) {
        this.labelType = type;
    }

    public LabelBehaviour setType(Labels.Type type) {
        this.labelType.setObject(type);
        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "span");
        Labels.onComponentTag(component, tag, labelType.getObject());
    }
}
