package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.jquery.IKey;

import java.io.IOException;

/**
 * tooltip validation config
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 * @since 31.10.2014
 */
public class TooltipValidationConfig extends AbstractValidationConfig {

    private static final IKey<Boolean> Animation = newKey("animation", true);
    private static final IKey<String> Container = newKey("container", null);
    private static final IKey<Delay> Delay = newKey("delay", new Delay(0));
    private static final IKey<Boolean> Html = newKey("html", false);
    private static final IKey<String> Placement = newKey("placement", "top");
    private static final IKey<String> Selector = newKey("selector", null);
    private static final IKey<String> Template = newKey("template", null);
    private static final IKey<String> Title = newKey("title", "");
    private static final IKey<String> Trigger = newKey("trigger", "hover focus");
    private static final IKey<String> Viewport = newKey("viewport", "{selector: 'body', padding: 0}");
    private static final long serialVersionUID = 1L;

    /**
     * Use animation.
     *
     * @param use flag
     * @return current config instance
     */
    public TooltipValidationConfig withAnimation(boolean use) {
        put(Animation, use);
        return this;
    }

    /**
     * Set container.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withContainer(String value) {
        put(Container, value);
        return this;
    }

    /**
     * Set delay.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withDelay(Delay value) {
        put(Delay, value);
        return this;
    }

    /**
     * Use html.
     *
     * @param use flag
     * @return current config instance
     */
    public TooltipValidationConfig withHtml(boolean use) {
        put(Html, use);
        return this;
    }

    /**
     * Set placement.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withPlacement(TooltipConfig.Placement value) {
        put(Placement, value.name());
        return this;
    }

    /**
     * Set selector.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withSelector(String value) {
        put(Selector, value);
        return this;
    }

    /**
     * Set template.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withTemplate(String value) {
        put(Template, value);
        return this;
    }

    /**
     * Set title.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withTitle(String value) {
        put(Title, value);
        return this;
    }

    /**
     * Set trigger.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withTrigger(String value) {
        put(Trigger, value);
        return this;
    }

    /**
     * Set viewport.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig withViewport(String value) {
        put(Viewport, value);
        return this;
    }

    /**
     * See <a href="http://bootstrap-datepicker.readthedocs.org/en/latest/options.html#todaybtn">docs</a>.
     * Today button could be a boolean or string <em>"linked"</em>:
     * <cite>If true or “linked”, displays a “Today” button at the bottom of the datepicker to select the
     * current date. If true, the “Today” button will only move the current date into view; if “linked”,
     * the current date will also be selected.</cite>
     */
    @JsonSerialize(using = DelaySerializer.class)
    public static class Delay {
        private final int show;
        private final int hide;

        public Delay(int delay) {
            this(delay, delay);
        }

        public Delay(int show, int hide) {
            this.show = show;
            this.hide = hide;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Delay delay = (Delay) o;

            if (hide != delay.hide) {
                return false;
            }
            if (show != delay.show) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = show;
            result = 31 * result + hide;
            return result;
        }
    }

    /**
     * Serializer for Delay
     */
    private static class DelaySerializer extends JsonSerializer<Delay> {

        @Override
        public void serialize(Delay value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (value.show == value.hide) {
                jgen.writeNumber(value.show);
            } else {
                jgen.writeStartObject();
                jgen.writeObjectField("show", value.show);
                jgen.writeObjectField("hide", value.hide);
                jgen.writeEndObject();
            }
        }
    }
}
