package de.agilecoders.wicket.core.markup.html.bootstrap.label;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * Bootstrapified Label.
 * <p>
 * <pre>
 *     	<span class="label">Default</span>
 *     	<span class="label label-primary">Primary</span>
 *     	<span class="label label-success">Success</span>
 *     	<span class="label label-warning">Warning</span>
 *     	<span class="label label-info">Info</span>
 *     	<span class="label label-danger">Danger</span>
 * </pre>
 *
 * @author drummer
 */
public class BootstrapLabel extends Label {

    private final LabelBehaviour labelBehaviour;

    public BootstrapLabel(final String componentId, final Labels.Type type) {
        this(componentId, null, type);
    }

    public BootstrapLabel(final String componentId, final IModel<String> model, final Labels.Type type) {
        super(componentId, model);
        add(labelBehaviour = new LabelBehaviour(type));
    }

    public BootstrapLabel setType(final Labels.Type type) {
        labelBehaviour.setType(type);
        return this;
    }
}
