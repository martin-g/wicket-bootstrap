package de.agilecoders.wicket.markup.html.bootstrap.components;

import org.apache.wicket.util.time.Duration;

/**
 * {@link PopoverBehavior} configuration
 *
 * @author miha
 */
public class PopoverConfig extends TooltipConfig {

    /**
     * how to position the tooltip - top | bottom | left | right
     */
    private static final IKey<String> Placement = newKey("placement", "right");

    /**
     * how tooltip is triggered - click | hover | focus | manual
     */
    private static final IKey<String> Trigger = newKey("trigger", "click");

    @Override
    public PopoverConfig withPlacement(final IPlacement value) {
        put(Placement, value.value());
        return this;
    }

    @Override
    public PopoverConfig withTrigger(final OpenTrigger value) {
        put(Trigger, value.name());
        return this;
    }

    public PopoverConfig withHoverTrigger() {
        return withTrigger(TooltipConfig.OpenTrigger.hover);
    }

    @Override
    public PopoverConfig withAnimation(boolean value) {
        return (PopoverConfig) super.withAnimation(value);
    }

    @Override
    public PopoverConfig withSelector(String value) {
        return (PopoverConfig) super.withSelector(value);
    }

    @Override
    public PopoverConfig withTitle(String value) {
        return (PopoverConfig) super.withTitle(value);
    }

    @Override
    public PopoverConfig withContent(String value) {
        return (PopoverConfig) super.withContent(value);
    }

    @Override
    public PopoverConfig withDelay(Duration value) {
        return (PopoverConfig) super.withDelay(value);
    }

    @Override
    public PopoverConfig withHtml(boolean value) {
        return (PopoverConfig) super.withHtml(value);
    }
}
