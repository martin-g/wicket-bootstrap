package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.Theme;

/**
 * Bootstrap theme.
 *
 * @author miha
 */
public class BootstrapTheme extends Theme {

    /**
     * Construct.
     *
     * @param settings the bootstrap settings
     */
    public BootstrapTheme(final IBootstrapSettings settings) {
        super("bootstrap", settings.getCssResourceReference());
    }

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
