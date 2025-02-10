package de.agilecoders.wicket.themes.markup.html.bootswatch;

import java.util.List;
import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;

/**
 * A {@link de.agilecoders.wicket.core.settings.ITheme theme} for Bootstrap
 * provided by <a href="http://bootswatch.com/">Bootswatch.com</a>.
 *
 * @author miha
 */
public enum BootswatchTheme implements ITheme {
    Cerulean, Cosmo, Cyborg, Darkly, Flatly, Journal, Litera, Lumen, Lux, Materia,
    Minty, Morph, Pulse, Quartz, Sandstone, Simplex, Sketchy, Slate, Solar, Spacelab,
    Superhero, United, Vapor, Yeti, Zephyr;

    /**
     * The placeholders are:
     * - the version
     * - the theme name
     * Example: ////cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cosmo/bootstrap.min.css
     * Example: ////cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cosmo/bootstrap.rtl.min.css
     */
    private static final String CDN_PATTERN = "////cdn.jsdelivr.net/npm/bootswatch@%s/dist/%s/bootstrap%s.min.css";

    private final String cdnUrl;
    private final ResourceReference reference;
    private final String rtlCdnUrl;
    private final ResourceReference rtlReference;

    /**
     * Construct.
     */
    BootswatchTheme() {
        String lName = name().toLowerCase(Locale.ROOT);
        this.cdnUrl = String.format(CDN_PATTERN, getBootstrapVersion(), lName, "");
        this.reference = new BootswatchCssReference(lName);

        String dot = ".";
        String rtl = "rtl";
        String rtlName = rtl + dot + lName;
        this.rtlCdnUrl = String.format(CDN_PATTERN, getBootstrapVersion(), lName, dot + rtl);
        this.rtlReference = new BootswatchCssReference(rtlName);
    }

    private boolean isRtl() {
        return WebSession.exists() && WebSession.get().isRtlLocale();
    }

    @Override
    public Iterable<String> getCdnUrls() {
        return List.of(); // no-op
    }

    @Override
    public List<HeaderItem> getDependencies() {
        ResourceReference ref;
        if (useCdnResources()) {
            ref = new UrlResourceReference(Url.parse(isRtl() ? rtlCdnUrl : cdnUrl));
        } else {
            ref = isRtl() ? rtlReference : reference;
        }
        return List.of(CssHeaderItem.forReference(ref).setId(BOOTSTRAP_THEME_MARKUP_ID));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        for (HeaderItem headerItem : getDependencies()) {
            response.render(headerItem);
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
    private String getBootstrapVersion() {
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
