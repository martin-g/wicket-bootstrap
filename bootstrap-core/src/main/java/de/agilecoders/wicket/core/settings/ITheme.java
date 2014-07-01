package de.agilecoders.wicket.core.settings;

import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * #### Description
 *
 * Interface that represents a theme. A theme is responsible for rendering of all web resources that
 * are needed.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public interface ITheme {

    /**
     * @return The unique name of this theme.
     */
    String name();

    /**
     * Print to the web response what ever the {@link ITheme} wants to contribute to the head section.
     *
     * @param response The {@link IHeaderResponse} instance
     */
    void renderHead(IHeaderResponse response);

    /**
     * @return The urls to CDN CSS resources to use for this theme
     */
    Iterable<String> getCdnUrls();

}
