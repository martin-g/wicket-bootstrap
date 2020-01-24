package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * An configuration object representing a step in a tour
 */
public class TourStep extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    /**
     * Path to the page on which the step should be shown. this allows you to build tours that span several pages!.
     */
    private static final IKey<String> Path = newKey("path", "");

    /**
     * jQuery selector to the HTML element on which the step popover should be shown.
     */
    private static final IKey<String> Element = newKey("element", "");

    /**
     * How to position the popover - top | bottom | left | right..
     */
    private static final IKey<TooltipConfig.Placement> Placement = newKey("placement", TooltipConfig.Placement.right);

    /**
     * The step's title
     */
    private static final IKey<IModel> Title = newKey("title", (IModel) Model.of("Step"));

    /**
     * A flag indicating whether to apply a css fade transition to the tooltip.
     */
    private static final IKey<Boolean> Animation = newKey("animation", Boolean.TRUE);

    /**
     * The step's content
     */
    private static final IKey<IModel> Content = newKey("content", (IModel) Model.of(""));

    /**
     * The step's content
     */
    private static final IKey<Boolean> Backdrop = newKey("backdrop", Boolean.FALSE);

    public TourStep backdrop(boolean backdrop) {
        put(Backdrop, backdrop);
        return this;
    }

    public TourStep path(String path) {
        put(Path, path);
        return this;
    }

    public TourStep element(Component element) {
        element.setOutputMarkupId(true);
        return element("#" + element.getMarkupId());
    }

    public TourStep element(String cssSelector) {
        put(Element, cssSelector);
        return this;
    }

    public TourStep placement(TooltipConfig.Placement placement) {
        put(Placement, placement);
        return this;
    }

    public TourStep title(IModel<String> title) {
        put(Title, wrap(title));
        return this;
    }

    public TourStep content(IModel<String> content) {
        put(Content, wrap(content));
        return this;
    }

    public TourStep animate(boolean animate) {
        put(Animation, animate);
        return this;
    }
}
