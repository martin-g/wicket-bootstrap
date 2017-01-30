package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * Represents a stack of the progress bar.
 */
public class Stack extends GenericPanel<Integer> {

    /**
     * A label for the stack's progress
     */
    private Label srOnly;

    /**
     * The color type of the stack
     */
    private ProgressBar.Type type = ProgressBar.Type.DEFAULT;

    /**
     * A flag that is used to decide whether to show the label or not.
     * By default the label is not shown.
     */
    private boolean labeled = false;

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The progress of this stack
     */
    public Stack(String id, IModel<Integer> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        srOnly = new Label("srOnly", createLabelModel());
        add(srOnly);
    }

    public ProgressBar.Type type() {
        return type;
    }

    public Stack type(ProgressBar.Type type) {
        this.type = type;
        return this;
    }

    public boolean labeled() {
        return labeled;
    }

    public Stack labeled(boolean labeled) {
        this.labeled = labeled;
        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (labeled()) {
            srOnly.setRenderBodyOnly(true);
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Integer value = getModelObject();
        Attributes.set(tag, "style", String.format("width: %s%%", value));
        Attributes.set(tag, "aria-valuenow", String.valueOf(value));
        Attributes.set(tag, "aria-valuemin", String.valueOf(ProgressBar.MIN));
        Attributes.set(tag, "aria-valuemax", String.valueOf(ProgressBar.MAX));

        if (!ProgressBar.Type.DEFAULT.equals(type)) {
            Attributes.addClass(tag, type().cssClassName());
        }
    }


    /**
     * Creates a model that is used for the stack's label
     *
     * @return A model with the label
     */
    protected IModel<String> createLabelModel() {
        return () -> String.format("%s%%", Stack.this.getModelObject());
    }
}
