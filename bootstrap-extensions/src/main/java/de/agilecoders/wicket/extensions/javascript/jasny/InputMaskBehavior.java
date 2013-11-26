package de.agilecoders.wicket.extensions.javascript.jasny;


import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.string.Strings;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A behavior that applies a mask on an &lt;input&gt; field by using
 * <a href="http://jasny.github.com/bootstrap/javascript.html#inputmask">Jasny InputMask</a>.
 * <p/>
 * <p>An input mask consists of a mask and placeholder.<br/>
 * The possible characters for the mask are:
 * <ul>
 * <li>a - allows entering any alpha character ([a-zA-Z])</li>
 * <li>9 - allows entering any number character ([0-9])</li>
 * <li>? - allows entering any alpha or number character ([a-zA-Z0-9])</li>
 * <li>* - allows entering any character (.)</li>
 * </ul>
 * </p>
 */
public abstract class InputMaskBehavior extends Behavior {
    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        String mask = getMask();
        if (!Strings.isEmpty(mask)) {
            response.render(JavaScriptHeaderItem.forReference(JasnyJsReference.INSTANCE));

            InputMaskConfig config = new InputMaskConfig();
            config.withMask(mask);

            String placeholder = getPlaceholder();
            if (!Strings.isEmpty(placeholder)) {
                config.withPlaceholder(placeholder);
            }

            response.render($(component).chain("inputmask", config).asDomReadyScript());
        }
    }

    /**
     * @return The mask to apply on the input field
     */
    protected abstract String getMask();

    /**
     * @return The placeholder to use as replacement for non-entered characters
     */
    protected String getPlaceholder() {
        return InputMaskConfig.Placeholder.getDefaultValue();
    }

    /**
     * {@link InputMaskBehavior} configuration
     */
    private static class InputMaskConfig extends AbstractConfig {

        private static final IKey<String> Mask = newKey("mask", null);

        private static final IKey<String> Placeholder = newKey("withPlaceholder", "_");

        /**
         * the pattern
         *
         * @param mask pattern as string
         * @return this instance for chaining
         */
        private InputMaskConfig withMask(String mask) {
            put(Mask, mask);
            return this;
        }

        /**
         * default placeholder
         *
         * @param placeholder placeholder as string
         * @return this instance for chaining
         */
        private InputMaskConfig withPlaceholder(String placeholder) {
            put(Placeholder, placeholder);
            return this;
        }
    }
}
