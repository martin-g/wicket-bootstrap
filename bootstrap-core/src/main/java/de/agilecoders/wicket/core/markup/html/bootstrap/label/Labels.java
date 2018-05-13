package de.agilecoders.wicket.core.markup.html.bootstrap.label;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

/**
 * Helper class that holds all special style modification
 * types of a label.
 *
 * @author drummer
 */
public final class Labels {

    /**
     * private constructor to prevent instantiation
     */
    private Labels() {
        throw new UnsupportedOperationException();
    }

    /**
     * Defines all possible label styles.
     * <a href="https://getbootstrap.com/docs/3.3/components/#labels"></a>
     */
    public enum Type implements ICssClassNameProvider {
        DEFAULT("label-default"),
        PRIMARY("label-primary"),
        SUCCESS("label-success"),
        WARNING("label-warning"),
        INFO("label-info"),
        DANGER("label-danger");

        private final String cssClassName;

        Type(final String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String cssClassName() {
            return cssClassName;
        }
    }

    /**
     * helper method that adds all necessary css styles to given component tag.
     *
     * @param component          the component to which given tag belongs, needed because of enabled state
     * @param tag                the component tag
     * @param classNameProviders all css class names to add
     */
    public static void onComponentTag(final Component component, final ComponentTag tag, final ICssClassNameProvider... classNameProviders) {
        Args.notNull(classNameProviders, "classNameProviders");

        final CssClassNames.Builder builder = CssClassNames.newBuilder().add("label");

        for (final ICssClassNameProvider provider : classNameProviders) {
            builder.add(provider.cssClassName());
        }

        Attributes.addClass(tag, builder.asString());
    }
}
