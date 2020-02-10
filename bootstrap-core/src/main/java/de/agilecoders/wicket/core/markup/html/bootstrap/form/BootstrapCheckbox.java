package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.util.Attributes;

/**
 * A specialization of a Checkbox with Bootstrap styling
 *
 * @see <a href="http://getbootstrap.com/css/#forms">Bootstrap forms</a>
 */
public class BootstrapCheckbox extends FormComponentPanel<Boolean> {
    private static final long serialVersionUID = 1L;
    private WebMarkupContainer wrapper;
    private boolean inline = false;
    private CheckBox checkbox;
    private final IModel<?> labelModel;

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
        this.labelModel = labelModel;

        setType(Boolean.class);

        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(getWrapper()
                .add(newLabel("label", labelModel))
                .add(getCheckbox())
                );
    }

    private WebMarkupContainer getWrapper() {
        if (wrapper == null) {
            wrapper = newCheckboxContainer("wrapper");
        }
        return wrapper;
    }

    private CheckBox getCheckbox() {
        if (checkbox == null) {
            checkbox = newCheckBox("checkbox", getModel());
        }
        return checkbox;
    }

    protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
        return new CheckBox(id, model);
    }

    protected Component newLabel(String id, IModel<?> labelModel) {
        return new Label(id, labelModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();

                IModel<?> model = getDefaultModel();
                setVisible(model != null && model.getObject() != null);
            }
        };
    }

    protected WebMarkupContainer newCheckboxContainer(String id) {
        return new WebMarkupContainer(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                if (isInline()) {
                    Attributes.addClass(tag, "form-check-inline");
                }
            }
        };
    }

    public boolean isInline() {
        return inline;
    }

    public BootstrapCheckbox setInline(boolean inline) {
        this.inline = inline;
        return this;
    }

    @Override
    public BootstrapCheckbox setLabel(IModel<String> label) {
        getCheckbox().setLabel(label);
        return this;
    }

    @Override
    public IModel<String> getLabel() {
        return getCheckbox().getLabel();
    }

    @Override
    public void convertInput() {
        setConvertedInput(getCheckbox().getConvertedInput());
    }

    @Override
    protected void detachModel() {
        super.detachModel();
        if (labelModel != null) {
            labelModel.detach();
        }
    }
}
