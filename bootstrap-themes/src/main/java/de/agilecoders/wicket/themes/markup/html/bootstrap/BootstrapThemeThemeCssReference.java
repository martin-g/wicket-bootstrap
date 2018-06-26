package de.agilecoders.wicket.themes.markup.html.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import java.util.List;

/**
 * #### Description
 *
 * The BootstrapThemeThemeCssReference uses bootstrap stylesheet with enabled gradient and shadows
 * to mimic Bootstrap 3 `bootstrap-theme.css` stylesheet. Stylesheet is manually compiled, so
 * there may be version conflict with future versions.
 *
 * #### Usage
 *
 * this css resource reference can be used directly:
 * ```java
 * response.render(CssHeaderItem.forReference(BootstrapThemeThemeCssReference.instance()));
 * ```
 *
 * or together with bootstrap theme:
 *
 * ```
 * settings.setThemeProvider(new SingleThemeProvider(new BootstrapThemeTheme()));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class BootstrapThemeThemeCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * @return singleton instance of {@link de.agilecoders.wicket.themes.markup.html.bootstrap.BootstrapThemeThemeCssReference}
     */
    public static BootstrapThemeThemeCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final BootstrapThemeThemeCssReference INSTANCE = new BootstrapThemeThemeCssReference();
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private BootstrapThemeThemeCssReference() {
        super(BootstrapThemeThemeCssReference.class, "css/bootstrap-theme.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
    }
}