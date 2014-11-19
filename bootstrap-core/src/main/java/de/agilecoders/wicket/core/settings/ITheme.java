package de.agilecoders.wicket.core.settings;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

import java.util.List;

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
     * The markup id to use for the CSS resource reference.
     * If the theme is changed in an Ajax response wicket-ajax.js will properly
     * remove the old &lt;link&gt; and add the new one.
     */
    String BOOTSTRAP_THEME_MARKUP_ID = "wb-theme";

    /**
     * @return The unique name of this theme.
     */
    String name();

    /**
     * @return A list of all resources needed by the theme
     */
    List<HeaderItem> getDependencies();

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
