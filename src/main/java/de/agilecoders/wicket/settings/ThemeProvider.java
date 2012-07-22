package de.agilecoders.wicket.settings;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface ThemeProvider {

    Theme byName(final String name);

    List<Theme> available();

    Theme defaultTheme();
}
