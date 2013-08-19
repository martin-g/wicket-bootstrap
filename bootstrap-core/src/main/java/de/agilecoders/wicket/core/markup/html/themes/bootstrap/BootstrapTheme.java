package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.Theme;

/**
 * Bootstrap theme.
 *
 * @author miha
 */
public class BootstrapTheme extends Theme {

//    private static final String CDN_PATTERN = "//netdna.bootstrapcdn.com/twitter-bootstrap/%s/css/bootstrap-combined.min.css";

    /**
     * Construct.
     */
    public BootstrapTheme() {
        super("bootstrap", BootstrapCssReference.instance());
    }
//
//    @Override
//    public Iterable<String> getCdnUrls() {
//        String cdnUrl = String.format(CDN_PATTERN, getVersion());
//        return Arrays.asList(cdnUrl);
//    }
}
