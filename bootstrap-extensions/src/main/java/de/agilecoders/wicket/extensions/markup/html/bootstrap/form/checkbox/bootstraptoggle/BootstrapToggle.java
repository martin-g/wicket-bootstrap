package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstraptoggle;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapCheckbox;

/**
 * An extension of {@link CheckBox} that transforms a normal HTML checkbox
 * to two styled buttons (Yes+No)
 *
 * <p>
 * Integrates <a href="http://www.bootstraptoggle.com/">Bootstrap Toggle</a>
 * </p>
 */
public class BootstrapToggle extends BootstrapCheckbox {

    private final BootstrapToggleConfig config;

    /**
     * Constructor.
     *
     * Should be used when this component has a parent with {@link org.apache.wicket.model.CompoundPropertyModel}
     *
     * @param id The component id
     */
    public BootstrapToggle(String id) {
        this(id, null, new BootstrapToggleConfig());
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to keep the selection
     */
    public BootstrapToggle(String id, IModel<Boolean> model) {
        this(id, model, new BootstrapToggleConfig());
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to keep the selection
     * @param config The configuration of this Bootstrap widget
     */
    public BootstrapToggle(String id, IModel<Boolean> model, BootstrapToggleConfig config) {
        super(id, model);

        this.config = Args.notNull(config, "config");
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param config The configuration of this Bootstrap widget
     */
    public BootstrapToggle(String id, BootstrapToggleConfig config) {
        this(id, null, config);
    }

    @Override
    protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
        final CheckBox checkBox = new CheckBox(id, model);
        checkBox.setOutputMarkupId(true);
        checkBox.add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);

                response.render(CssHeaderItem.forReference(new CssResourceReference(BootstrapToggle.class, "res/css/bootstrap-toggle.css")));
                response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(BootstrapToggle.class, "res/js/bootstrap-toggle.js")));
                response.render(OnDomReadyHeaderItem.forScript($(checkBox).chain("bootstrapToggle", getConfig()).get()));
            }
        });
        return checkBox;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        IModel<String> offLabel = getOffLabel();
        if (offLabel != null) {
            getConfig().withOffLabel(offLabel.getObject());
        }

        IModel<String> onLabel = getOnLabel();
        if (onLabel != null) {
            getConfig().withOnLabel(onLabel.getObject());
        }
    }

    /**
     * @return The configuration for the widget.
     */
    public BootstrapToggleConfig getConfig() {
        return config;
    }

    /*
      I18n-able settings for BootstrapCheckBoxPicker. Use ResourceModel("some.key') if customization is needed
     */

    protected IModel<String> getOffLabel() {
        return Model.of("Off");
    }

    protected IModel<String> getOnLabel() {
        return Model.of("On");
    }
}
