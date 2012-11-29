package de.agilecoders.wicket.settings;

import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * Interface that represents a special theme.
 *
 * @author miha
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

}
