package de.agilecoders.wicket.themes.markup.html.bootstrap;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

/**
 * # Description
 *
 * The BootstrapThemeThemeCssReference uses the latest version of bootstrap-theme.css from the `org.webjars.bootstrap`
 * dependency which is defined in `pom.xml`. This reference has a dependency to base bootstrap css reference, but there
 * will be no version conflicts between this reference and the `bootstrap.css` from `BootstrapCssReference` because both
 * will are loaded from same jar file.
 *
 * # Usage
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
public class BootstrapThemeThemeCssReference extends WebjarsCssResourceReference {
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
        super("/bootstrap/current/css/bootstrap-theme.css");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
    }
}