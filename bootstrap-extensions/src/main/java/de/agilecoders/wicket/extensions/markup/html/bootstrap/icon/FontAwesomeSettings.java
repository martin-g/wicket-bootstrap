package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings.IconKey.*;

import java.util.HashMap;
import java.util.Map;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;

/**
 * This class allows the user to specify the FontAwesome version (5.x or 6.x - defaulting to 6.x).
 * This is relevant for wicket components from `bootstrap-extensions` using FontAwesome icons
 * to reflect the choice of the FontAwesome version made by the user.
 * This approach is required to keep FontAwesome related classes outside `bootstrap-core`.
 * <p>
 * In order to override the default of FontAwesome 6 to use FontAwesome5, do the following:
 *
 * <pre>
 * {@code
 * FontAwesomeSettings.get(Application.get()).setCssResourceReference(FontAwesome5CssReference.instance());
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

    /** This method should only be used inside the module `bootstrap-extensions` */
    public IconType getIconType(IconKey iconKey) {
        if (iconTypeMap == null) {
            synchronized (this) {
                if (iconTypeMap == null) {
                    iconTypeMap = new HashMap<>();
                    if (cssResourceReference instanceof FontAwesome6CssReference) {
                        iconTypeMap.put(ARROW_DOWN, FontAwesome6IconType.arrow_down_s);
                        iconTypeMap.put(ARROW_LEFT, FontAwesome6IconType.align_left_s);
                        iconTypeMap.put(ARROW_RIGHT, FontAwesome6IconType.align_right_s);
                        iconTypeMap.put(ARROW_UP, FontAwesome6IconType.arrow_up_s);
                        iconTypeMap.put(CALENDAR, FontAwesome6IconType.calendar_days_r);
                        iconTypeMap.put(CLEAR, FontAwesome6IconType.eraser_s);
                        iconTypeMap.put(CLOCK, FontAwesome6IconType.clock_r);
                        iconTypeMap.put(CLOSE, FontAwesome6IconType.xmark_s);
                        iconTypeMap.put(EMPTY, FontAwesome6IconType.star_r);
                        iconTypeMap.put(FILLED, FontAwesome6IconType.star_s);
                        iconTypeMap.put(SORT, FontAwesome6IconType.sort_s);
                        iconTypeMap.put(SORT_DOWN, FontAwesome6IconType.sort_down_s);
                        iconTypeMap.put(SORT_UP, FontAwesome6IconType.sort_up_s);
                        iconTypeMap.put(TODAY, FontAwesome6IconType.calendar_check_r);
                    } else if (cssResourceReference instanceof FontAwesome5CssReference) {
                        iconTypeMap.put(ARROW_DOWN, FontAwesome5IconType.arrow_down_s);
                        iconTypeMap.put(ARROW_LEFT, FontAwesome5IconType.align_left_s);
                        iconTypeMap.put(ARROW_RIGHT, FontAwesome5IconType.align_right_s);
                        iconTypeMap.put(ARROW_UP, FontAwesome5IconType.arrow_up_s);
                        iconTypeMap.put(CALENDAR, FontAwesome5IconType.calendar_alt_r);
                        iconTypeMap.put(CLEAR, FontAwesome5IconType.eraser_s);
                        iconTypeMap.put(CLOCK, FontAwesome5IconType.clock_r);
                        iconTypeMap.put(CLOSE, FontAwesome5IconType.times_s);
                        iconTypeMap.put(EMPTY, FontAwesome5IconType.star_r);
                        iconTypeMap.put(FILLED, FontAwesome5IconType.star_s);
                        iconTypeMap.put(SORT, FontAwesome5IconType.sort_s);
                        iconTypeMap.put(SORT_DOWN, FontAwesome5IconType.sort_down_s);
                        iconTypeMap.put(SORT_UP, FontAwesome5IconType.sort_up_s);
                        iconTypeMap.put(TODAY, FontAwesome5IconType.calendar_check_r);
                    }
                }
            }
        }
        return iconTypeMap.get(iconKey);
    }

    /** Pre-canned Icons that need to be either FontAwesome 5 or 6 specific, only use inside boostrap-extensions */
    public enum IconKey {
        ARROW_DOWN,
        ARROW_LEFT,
        ARROW_RIGHT,
        ARROW_UP,
        CALENDAR,
        CLEAR,
        CLOCK,
        CLOSE,
        EMPTY,
        FILLED,
        SORT,
        SORT_DOWN,
        SORT_UP,
        TODAY,
        ;
    }
}
