package de.agilecoders.wicket.themes.markup.html.google;

import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import java.util.Collections;
import java.util.List;

/**
 * #### Description
 *
 * java representation of TODC Bootstrap. This theme modifies bootstrap and therefor both
 * files gets loaded (`bootstrap.css` and `google-bootstrap.css`).
 *
 * #### Usage
 *
 * ```java
 * settings.setThemeProvider(new SingleThemeProvider(new GoogleTheme()));
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class GoogleTheme extends Theme {

    /**
     * Construct.
     */
    public GoogleTheme(final String name) {
        super(name);
    }

    /**
     * Construct using default theme name: `google`
     */
    public GoogleTheme() {
        this("google");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        HeaderItem headerItem = CssHeaderItem.forReference(GoogleCssReference.instance()).setId(BOOTSTRAP_THEME_MARKUP_ID);
        return Collections.singletonList(headerItem);
    }
}
