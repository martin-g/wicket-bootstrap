package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.password.strength;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.util.lang.Args;

/**
 * A behavior that can be added to {@link org.apache.wicket.markup.html.form.PasswordTextField}
 * to add client-side password strength checks
 */
public class PasswordStrengthBehavior extends Behavior {

    private final PasswordStrengthConfig config;

    /**
     * Constructor with default configuration
     */
    public PasswordStrengthBehavior() {
        this(new PasswordStrengthConfig());
    }

    /**
     * Constructor with custom configuration
     *
     * @param config The custom configuration
     */
    public PasswordStrengthBehavior(PasswordStrengthConfig config) {
        this.config = Args.notNull(config, "config");
    }

    /**
     * @return The configuration to use
     */
    public PasswordStrengthConfig getConfig() {
        return config;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        if (!(component instanceof PasswordTextField)) {
            throw new IllegalArgumentException(PasswordStrengthBehavior.class.getName() +
                       " could be used only with " + PasswordTextField.class.getName());
        }
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(getHeaderItem());
        response.render($(component).chain("pwstrength", getConfig()).asDomReadyScript());
    }

    protected JavaScriptHeaderItem getHeaderItem() {
        return JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(PasswordStrengthBehavior.class, "res/pwstrength-bootstrap-1.2.5.js"));
    }
}
