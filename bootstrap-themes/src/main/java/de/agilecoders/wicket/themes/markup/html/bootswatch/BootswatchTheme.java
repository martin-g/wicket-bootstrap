package de.agilecoders.wicket.themes.markup.html.bootswatch;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A {@link de.agilecoders.wicket.core.settings.ITheme theme} for Bootstrap
 * provided by <a href="http://bootswatch.com/">Bootswatch.com</a>.
 *
 * @author miha
 */
public enum BootswatchTheme implements ITheme {
    Cerulean, Cosmo, Cyborg, Darkly, Flatly, Journal, Lumen, Paper, Readable,
    Sandstone, Simplex, Slate, Spacelab, Superhero, United, Yeti;

    /**
     * The placeholders are:
     * - the version
     * - the theme name
     * Example: //netdna.bootstrapcdn.com/bootswatch/3.1.1/cosmo/bootstrap.min.css
     */
    private static final String CDN_PATTERN = "//netdna.bootstrapcdn.com/bootswatch/%s/%s/bootstrap.min.css";

    private String cdnUrl;
    private final ResourceReference reference;

    /**
     * Construct.
     */
    private BootswatchTheme() {
        this.reference = new BootswatchCssReference(name().toLowerCase());
    }

    @Override
    public Iterable<String> getCdnUrls() {
        return Arrays.asList(cdnUrl);
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Collections.<HeaderItem>singletonList(CssHeaderItem.forReference(reference).setId(BOOTSTRAP_THEME_MARKUP_ID));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        if (useCdnResources()) {
            if (cdnUrl == null) {
                cdnUrl = String.format(CDN_PATTERN, getVersion(), name().toLowerCase());
            }
            response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse(cdnUrl))));
        }
        else {
            for (HeaderItem headerItem : getDependencies()) {
                response.render(headerItem);
            }
        }
    }

    /**
     * @return true, if cdn resources should be used instead of webjars.
     */
    private boolean useCdnResources() {
        boolean result = false;

        if (Application.exists()) {
            IBootstrapSettings settings = Bootstrap.getSettings();
            if (settings != null) {
                result = settings.useCdnResources();
            }
        }

        return result;
    }

    /**
     * @return The configured version of Bootstrap
     */
    private String getVersion() {
        String version = IBootstrapSettings.VERSION;
        if (Application.exists()) {
            IBootstrapSettings settings = Bootstrap.getSettings();
            if (settings != null) {
                version = settings.getVersion();
            }
        }

        return version;
    }
}
