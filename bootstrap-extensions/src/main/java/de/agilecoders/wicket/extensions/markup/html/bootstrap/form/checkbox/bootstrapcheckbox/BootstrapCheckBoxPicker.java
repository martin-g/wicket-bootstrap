package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * An extension of {@link CheckBox} that transforms a normal HTML checkbox
 * to two styled buttons (Yes+No)
 *
 * <p>
 * Integrates <a href="http://vsn4ik.github.io/bootstrap-checkbox/">Bootstrap-Checkbox</a>
 * </p>
 */
public class BootstrapCheckBoxPicker extends CheckBox {

    private final BootstrapCheckBoxPickerConfig config;

    /**
     * Constructor.
     *
     * Should be used when this component has a parent with {@link org.apache.wicket.model.CompoundPropertyModel}
     *
     * @param id The component id
     */
    public BootstrapCheckBoxPicker(String id) {
        this(id, null, new BootstrapCheckBoxPickerConfig());
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to keep the selection
     */
    public BootstrapCheckBoxPicker(String id, IModel<Boolean> model) {
        this(id, model, new BootstrapCheckBoxPickerConfig());
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to keep the selection
     * @param config The configuration of this Bootstrap widget
     */
    public BootstrapCheckBoxPicker(String id, IModel<Boolean> model, BootstrapCheckBoxPickerConfig config) {
        super(id, model);

        this.config = Args.notNull(config, "config");
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param config The configuration of this Bootstrap widget
     */
    public BootstrapCheckBoxPicker(String id, BootstrapCheckBoxPickerConfig config) {
        this(id, null, config);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        IModel<String> offLabel = getOffLabel();
        if (offLabel != null) {
            tag.put("data-off-label", offLabel.getObject());
        }

        IModel<String> onLabel = getOnLabel();
        if (onLabel != null) {
            tag.put("data-on-label", onLabel.getObject());
        }

        IModel<String> offTitle = getOffTitle();
        if (offTitle != null) {
            tag.put("data-off-title", offTitle.getObject());
        }

        IModel<String> onTitle = getOnTitle();
        if (onTitle != null) {
            tag.put("data-on-title", onTitle.getObject());
        }

        IModel<String> warningMessage = getWarningMessage();
        if (warningMessage != null) {
            tag.put("data-warning-message", warningMessage.getObject());
        }
    }

    /**
     * @return The configuration for the widget.
     */
    public BootstrapCheckBoxPickerConfig getConfig() {
        return config;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(BootstrapCheckBoxPicker.class, "js/bootstrap-checkbox.js")));
        response.render(OnDomReadyHeaderItem.forScript($(this).chain("checkboxpicker", getConfig()).get()));
        
        // Bootstrap4 dropped hidden for invisible, so we have to hide input manually.
        response.render(OnDomReadyHeaderItem.forScript(String.format("$(%s).hide();", getMarkupId())));
    }

    /*
      I18n-able settings for BootstrapCheckBoxPicker. Use ResourceModel("some.key') if customization is needed
     */

    protected IModel<String> getOffLabel() {
        return Model.of("No");
    }

    protected IModel<String> getOnLabel() {
        return Model.of("Yes");
    }

    protected IModel<String> getOffTitle() {
        return null;
    }

    protected IModel<String> getOnTitle() {
        return null;
    }

    protected IModel<String> getWarningMessage() {
        return Model.of("Please do not use bootstrap-checkbox element in label element.");
    }
}
