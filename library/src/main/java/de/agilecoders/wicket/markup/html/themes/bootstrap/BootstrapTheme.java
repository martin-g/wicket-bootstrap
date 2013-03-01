package de.agilecoders.wicket.markup.html.themes.bootstrap;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import de.agilecoders.wicket.settings.Theme;

import java.util.Arrays;

/**
 * Bootstrap theme.
 *
 * @author miha
 * @version 1.0
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
