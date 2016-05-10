package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;

/**
 * A specialization of a Checkbox with Bootstrap styling
 *
 * @see <a href="http://getbootstrap.com/css/#forms">Bootstrap forms</a>
 */
public class BootstrapCheckbox extends FormComponentPanel<Boolean> {

    private final WebMarkupContainer wrapper;
    private final IModel<?> labelModel;
    private boolean inline = false;
    private CheckBox checkbox;

    /**
     * Constructor.
     *
     * @param id The component id
     */
    public BootstrapCheckbox(String id) {
        this(id, null);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model for the checkbox
     */
    public BootstrapCheckbox(String id, IModel<Boolean> model) {
        this(id, model, null);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model for the checkbox
     * @param labelModel A model for the checkbox's label
     */
    public BootstrapCheckbox(String id, IModel<Boolean> model, IModel<?> labelModel) {
        super(id, model);

        setType(Boolean.class);

        this.labelModel = labelModel;

        setRenderBodyOnly(true);

        wrapper = new WebMarkupContainer("wrapper");
        add(wrapper);
        MarkupContainer label = newLabelContainer("label");
        wrapper.add(label);
        label.add(newLabel("post-label"));
        checkbox = newCheckBox("checkbox", getModel());
        label.add(checkbox);

    }

    @Override
    protected void onDetach() {
        super.onDetach();

        if (labelModel != null) {
            labelModel.detach();
        }
    }

    protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
        return new CheckBox(id, model);
    }

    protected Component newLabel(String id) {
        return new Label(id, labelModel) {
            @Override
            protected void onConfigure() {
                super.onConfigure();

                setVisible(labelModel != null && labelModel.getObject() != null);
            }
        };
    }

    protected MarkupContainer newLabelContainer(String id) {
        return new WebMarkupContainer(id) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                if (isInline()) {
                    Attributes.addClass(tag, "checkbox-inline");
                }
            }
        };
    }

    public boolean isInline() {
        return inline;
    }

    public BootstrapCheckbox setInline(boolean inline) {
        this.inline = inline;
        wrapper.setRenderBodyOnly(inline);
        return this;
    }

    @Override
    public BootstrapCheckbox setLabel(IModel<String> label) {
        checkbox.setLabel(label);
        return this;
    }

    @Override
    public IModel<String> getLabel() {
        return checkbox.getLabel();
    }

    @Override
    public void convertInput() {
        setConvertedInput(checkbox.getConvertedInput());
    }
}
