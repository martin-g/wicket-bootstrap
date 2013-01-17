package de.agilecoders.wicket.javascript.jasny;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Objects;
import org.apache.wicket.util.string.Strings;

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

            response.render(OnDomReadyHeaderItem.forScript(JQuery.$(component).chain("inputmask", config).get()));

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
        return "_";
    }

    private static class InputMaskConfig extends AbstractConfig {

        private InputMaskConfig withMask(String mask) {
            put(Key.Mask, mask);
            return this;
        }

        private InputMaskConfig withPlaceholder(String placeholder) {
            put(Key.Placeholder, placeholder);
            return this;
        }

        private enum Key implements IKey {
            Mask("mask", String.class, null),

            Placeholder("withPlaceholder", String.class, "_");

            private final String key;
            private final Class type;
            private final Object defaultValue;

            /**
             * Construct.
             *
             * @param key          string representation of this key
             * @param type         The object type
             * @param defaultValue The default value
             */
            private Key(final String key, final Class type, final Object defaultValue) {
                this.key = key;
                this.type = type;
                this.defaultValue = defaultValue;
            }

            @Override
            public String key() {
                return key;
            }

            @Override
            public void assertCorrectType(final Object value) {
                Preconditions.checkArgument(type.isInstance(value));
            }

            @Override
            public boolean isDefaultValue(final Object value) {
                return Objects.equal(value, defaultValue);
            }

            @Override
            public Object getDefaultValue() {
                return defaultValue;
            }
        }
    }
}
