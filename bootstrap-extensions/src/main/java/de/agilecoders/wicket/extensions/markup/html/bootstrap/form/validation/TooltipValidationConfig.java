package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.IKey;

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
    private static final IKey<String> Delay = newKey("delay", "0");
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
    public TooltipValidationConfig useAnimation(Boolean use) {
        put(Animation, use);
        return this;
    }

    /**
     * Set container.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setContainer(String value) {
        put(Container, value);
        return this;
    }

    /**
     * Set delay.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setDelay(String value) {
        put(Delay, value);
        return this;
    }

    /**
     * Use html.
     *
     * @param use flag
     * @return current config instance
     */
    public TooltipValidationConfig useHtml(Boolean use) {
        put(Html, use);
        return this;
    }

    /**
     * Set placement.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setPlacement(String value) {
        put(Placement, value);
        return this;
    }

    /**
     * Set selector.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setSelector(String value) {
        put(Selector, value);
        return this;
    }

    /**
     * Set template.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setTemplate(String value) {
        put(Template, value);
        return this;
    }

    /**
     * Set title.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setTitle(String value) {
        put(Title, value);
        return this;
    }

    /**
     * Set trigger.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setTrigger(String value) {
        put(Trigger, value);
        return this;
    }

    /**
     * Set viewport.
     *
     * @param value value to set
     * @return current config instance
     */
    public TooltipValidationConfig setViewport(String value) {
        put(Viewport, value);
        return this;
    }
}
