package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.Theme;

import java.util.Arrays;

/**
 * Bootstrap theme.
 *
 * @author miha
 */
public class BootstrapTheme extends Theme {

    private static final String CDN_PATTERN = "//netdna.bootstrapcdn.com/twitter-bootstrap/%s/css/bootstrap-combined.min.css";

    /**
     * Construct.
     */
    public BootstrapTheme() {
        super("bootstrap", BootstrapCssReference.INSTANCE);
    }

    @Override
    public Iterable<String> getCdnUrls() {
        String cdnUrl = String.format(CDN_PATTERN, IBootstrapSettings.VERSION);
        return Arrays.asList(cdnUrl);
    }
}
