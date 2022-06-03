package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings.IconKey.*;

import java.util.HashMap;
import java.util.Map;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;

/**
 * Font-Awesome related settings.
 * Required in order to keep FontAwesome related classes outside bootstrap-core in bootstrap-extensions.
 * <p>
 * Defaults to using FontAwesome6. In order to configure it to use FontAwesome5, do the following:
 *
 * <pre>
 * {@code
 * FontAwesomeSettings.get(Application.get()).setCssResourceReference(FontAwesome6CssReference.instance());
 * }
 * </pre>
 */
public class FontAwesomeSettings {

    private Map<IconKey, IconType> iconTypeMap = null;

    private WebjarsCssResourceReference cssResourceReference = FontAwesome6CssReference.instance();

    private static final MetaDataKey<FontAwesomeSettings> KEY = new MetaDataKey<>() {
    };

    public static FontAwesomeSettings get(Application application) {
        FontAwesomeSettings settings = application.getMetaData(KEY);
        if (settings == null) {
            synchronized (application) {
                settings = application.getMetaData(KEY);
                if (settings == null) {
                    settings = new FontAwesomeSettings();
                    set(application, settings);
                }
            }
        }
        return settings;
    }

    public static void set(Application application, FontAwesomeSettings settings) {
        application.setMetaData(KEY, settings);
    }

    /**
     * @return the css reference to the font awesome version of choice.
     */
    public WebjarsCssResourceReference getCssResourceReference() {
        return cssResourceReference;
    }

    /**
     * @param cssResourceReference
     *     The css reference to the font awesome version of choice.
     */
    public void setCssResourceReference(WebjarsCssResourceReference cssResourceReference) {
        this.cssResourceReference = cssResourceReference;
    }

    public IconType getIconType(IconKey iconKey) {
        if (iconTypeMap == null) {
            synchronized (this) {
                if (iconTypeMap == null) {
                    iconTypeMap = new HashMap<>();
                    if (cssResourceReference instanceof FontAwesome6CssReference) {
                        iconTypeMap.put(CALENDAR, FontAwesome6IconType.calendar_r);
                        iconTypeMap.put(UP, FontAwesome6IconType.arrow_up_s);
                        iconTypeMap.put(DOWN, FontAwesome6IconType.arrow_down_s);
                        iconTypeMap.put(DATE, FontAwesome6IconType.calendar_days_r);
                        iconTypeMap.put(TIME, FontAwesome6IconType.clock_r);
                        iconTypeMap.put(PREVIOUS, FontAwesome6IconType.arrow_left_s);
                        iconTypeMap.put(NEXT, FontAwesome6IconType.arrow_right_s);
                        iconTypeMap.put(TODAY, FontAwesome6IconType.calendar_check_r);
                        iconTypeMap.put(CLEAR, FontAwesome6IconType.eraser_s);
                        iconTypeMap.put(CLOSE, FontAwesome6IconType.xmark_s);
                        iconTypeMap.put(FILLED, FontAwesome6IconType.star_s);
                        iconTypeMap.put(EMPTY, FontAwesome6IconType.star_r);
                    } else if (cssResourceReference instanceof FontAwesome5CssReference) {
                        iconTypeMap.put(CALENDAR, FontAwesome5IconType.calendar_alt_r);
                        iconTypeMap.put(UP, FontAwesome5IconType.arrow_up_s);
                        iconTypeMap.put(DOWN, FontAwesome5IconType.arrow_down_s);
                        iconTypeMap.put(DATE, FontAwesome5IconType.calendar_alt_r);
                        iconTypeMap.put(TIME, FontAwesome5IconType.clock_r);
                        iconTypeMap.put(PREVIOUS, FontAwesome5IconType.arrow_left_s);
                        iconTypeMap.put(NEXT, FontAwesome5IconType.arrow_right_s);
                        iconTypeMap.put(TODAY, FontAwesome5IconType.calendar_check_r);
                        iconTypeMap.put(CLEAR, FontAwesome5IconType.eraser_s);
                        iconTypeMap.put(CLOSE, FontAwesome5IconType.times_s);
                        iconTypeMap.put(FILLED, FontAwesome5IconType.star_s);
                        iconTypeMap.put(EMPTY, FontAwesome5IconType.star_r);
                    }
                }
            }
        }
        return iconTypeMap.get(iconKey);
    }

    /** Pre-canned Icons that need to be either FontAwesome 5 or 6 specific */
    public enum IconKey {
        CALENDAR,
        UP,
        DOWN,
        DATE,
        TIME,
        PREVIOUS,
        NEXT,
        TODAY,
        CLEAR,
        CLOSE,
        FILLED,
        EMPTY,
        ;
    }
}
