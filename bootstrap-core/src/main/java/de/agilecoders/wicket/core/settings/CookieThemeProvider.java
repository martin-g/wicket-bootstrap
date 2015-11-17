package de.agilecoders.wicket.core.settings;

import org.apache.wicket.util.cookies.CookieDefaults;
import org.apache.wicket.util.cookies.CookieUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

/**
 * #### Description
 *
 * An {@link ActiveThemeProvider} implementation that stores the active theme
 * in the session and in a browser cookie for durability.
 */
public class CookieThemeProvider extends SessionThemeProvider {

    private static final String DEFAULT_COOKIE_NAME = "wb-theme";

    private final String cookieName;
    private final CookieDefaults cookieDefaults;

    /**
     * Constructor.
     * <p>
     * Uses default cookie name and settings
     */
    public CookieThemeProvider() {
        this(DEFAULT_COOKIE_NAME, new CookieDefaults());
    }

    /**
     * Constructor.
     * <p>
     * Sets custom cookie name and settings
     *
     * @param cookieName The name of the cookie that will keep the theme name as a value
     * @param cookieDefaults The settings for the cookie (age, domain, path, etc.)
     */
    public CookieThemeProvider(String cookieName, CookieDefaults cookieDefaults) {
        this.cookieName = Args.notEmpty(cookieName, "cookieName");
        this.cookieDefaults = Args.notNull(cookieDefaults, "cookieDefaults");}

    @Override
    protected String loadThemeName() {
        String themeName = super.loadThemeName();

        if (Strings.isEmpty(themeName)) {
            CookieUtils cookieUtils = new CookieUtils(cookieDefaults);
            themeName = cookieUtils.load(cookieName);
        }
        return themeName;
    }

    @Override
    protected void storeThemeName(String themeName) {
        super.storeThemeName(themeName);

        CookieUtils cookieUtils = new CookieUtils(cookieDefaults);
        cookieUtils.save(cookieName, themeName);
    }
}
