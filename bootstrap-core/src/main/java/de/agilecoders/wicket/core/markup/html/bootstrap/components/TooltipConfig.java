package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.util.time.Duration;

/**
 * {@link TooltipBehavior} configuration
 *
 * @author miha
 */
public class TooltipConfig extends AbstractConfig {

    /**
     * apply a css fade transition to the tooltip
     */
    private static final IKey<Boolean> Animation = newKey("animation", true);

    /**
     * how to position the tooltip - top | bottom | left | right
     */
    private static final IKey<String> TooltipPlacement = newKey("placement", "top");

    /**
     * If a selector is provided, tooltip objects will be delegated to the specified targets.
     */
    private static final IKey<String> Selector = newKey("selector", "false");

    /**
     * default content value if `data-content` attribute isn't present
     */
    private static final IKey<String> Content = newKey("content", "");

    /**
     * default title value if `title` tag isn't present
     */
    private static final IKey<String> Title = newKey("title", "");

    /**
     * how tooltip is triggered - click | hover | focus | manual
     */
    private static final IKey<String> Trigger = newKey("trigger", "hover");

    /**
     * delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
     * <p/>
     * Not implemented:
     * If a number is supplied, delay is applied to both hide/show
     * Object structure is: delay: { show: 500, hide: 100 }
     */
    private static final IKey<Long> Delay = newKey("delay", 0L);

    /**
     * Insert html into the tooltip. If false, jquery's text method
     * will be used to insert content into the dom. Use text if you're worried about XSS attacks.
     */
    private static final IKey<Boolean> Html = newKey("html", false);

    /**
     * holds all possible tooltip positions
     */
    public enum Placement implements IPlacement {
        top, bottom, right, left;

        @Override
        public String value() {
            return name();
        }
    }

    /**
     * interface for tooltip placement. can be used to inject functions.
     */
    public static interface IPlacement {
        String value();
    }

    /**
     * all possible trigger for tooltip
     */
    public enum OpenTrigger {
        click, hover, focus, manual
    }

    /**
     * Construct.
     */
    public TooltipConfig() {
        super();
    }

    /**
     * apply a css fade transition to the tooltip
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withAnimation(final boolean value) {
        put(Animation, value);
        return this;
    }

    /**
     * how to position the tooltip - top | bottom | left | right
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withPlacement(final IPlacement value) {
        put(TooltipPlacement, value.value());
        return this;
    }

    /**
     * If a selector is provided, tooltip objects will be delegated to the specified targets.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withSelector(final String value) {
        put(Selector, value);
        return this;
    }

    /**
     * default title value if `title` tag isn't present
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withTitle(final String value) {
        put(Title, value);
        return this;
    }

    /**
     * default content value if `data-content` attribute isn't present
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withContent(final String value) {
        put(Content, value);
        return this;
    }

    /**
     * how tooltip is triggered - click | hover | focus | manual
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withTrigger(final OpenTrigger value) {
        put(Trigger, value.name());
        return this;
    }

    /**
     * delay showing and hiding the tooltip (ms) - does not apply to manual trigger type
     * <p/>
     * Not implemented:
     * If a number is supplied, delay is applied to both hide/show
     * Object structure is: delay: { show: 500, hide: 100 }
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withDelay(final Duration value) {
        put(Delay, value.getMilliseconds());
        return this;
    }

    /**
     * Insert html into the tooltip. If false, jquery's text method
     * will be used to insert content into the dom. Use text if you're worried about XSS attacks.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TooltipConfig withHtml(final boolean value) {
        put(Html, value);
        return this;
    }

}
