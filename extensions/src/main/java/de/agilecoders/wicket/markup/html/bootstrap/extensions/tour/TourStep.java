package de.agilecoders.wicket.markup.html.bootstrap.extensions.tour;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.markup.html.bootstrap.components.TooltipConfig;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Objects;

/**
 * An configuration object representing a step in a tour
 */
public class TourStep extends AbstractConfig {

    private static class Key<T> implements IKey {

        /**
         * Path to the page on which the step should be shown. this allows you to build tours that span several pages!.
         */
        private static final Key<String> Path = new Key<String>("path", String.class, "");

        /**
         * jQuery selector to the HTML element on which the step popover should be shown.
         */
        private static final Key<String> Element = new Key<String>("element", String.class, "");

        /**
         * How to position the popover - top | bottom | left | right..
         */
        private static final Key<TooltipConfig.Placement> Placement =
                new Key<TooltipConfig.Placement>("placement", TooltipConfig.Placement.class, TooltipConfig.Placement.right);

        /**
         * The step's title
         */
        private static final Key<IModel> Title = new Key<IModel>("title", IModel.class, Model.of("Step"));

        /**
         * A flag indicating whether to apply a css fade transition to the tooltip.
         */
        private static final Key<Boolean> Animation = new Key<Boolean>("animation", Boolean.class, Boolean.TRUE);

        /**
         * The step's content
         */
        private static final Key<IModel> Content = new Key<IModel>("content", IModel.class, Model.of(""));

        private final String key;
        private final Class<T> type;
        private final T defaultValue;

        /**
         * Construct.
         *
         * @param key          string representation of this key
         * @param type         The object type
         * @param defaultValue The default value
         */
        private Key(final String key, final Class<T> type, final T defaultValue) {
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

    public TourStep path(String path) {
        put(Key.Path, path);
        return this;
    }

    public TourStep element(Component element) {
        element.setOutputMarkupId(true);
        return element("#" + element.getMarkupId());
    }

    public TourStep element(String cssSelector) {
        put(Key.Element, cssSelector);
        return this;
    }

    public TourStep placement(TooltipConfig.Placement placement) {
        put(Key.Placement, placement);
        return this;
    }

    public TourStep title(IModel<String> title) {
        put(Key.Title, wrap(title));
        return this;
    }

    public TourStep content(IModel<String> content) {
        put(Key.Content, wrap(content));
        return this;
    }

    public TourStep animate(boolean animate) {
        put(Key.Animation, animate);
        return this;
    }
}
