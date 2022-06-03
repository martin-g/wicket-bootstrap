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
                        iconTypeMap.put(ALIGN_CENTER, FontAwesome6IconType.align_center_s);
                        iconTypeMap.put(ALIGN_LEFT, FontAwesome6IconType.align_left_s);
                        iconTypeMap.put(ALIGN_JUSTIFY, FontAwesome6IconType.align_justify_s);
                        iconTypeMap.put(ALIGN_RIGHT, FontAwesome6IconType.align_right_s);
                        iconTypeMap.put(ARROW_DOWN, FontAwesome6IconType.arrow_down_s);
                        iconTypeMap.put(ARROW_LEFT, FontAwesome6IconType.align_left_s);
                        iconTypeMap.put(ARROW_RIGHT, FontAwesome6IconType.align_right_s);
                        iconTypeMap.put(ARROW_UP, FontAwesome6IconType.arrow_up_s);
                        iconTypeMap.put(BOOK, FontAwesome6IconType.book_s);
                        iconTypeMap.put(BOOKMARK, FontAwesome6IconType.bookmark_r);
                        iconTypeMap.put(CALENDAR, FontAwesome6IconType.calendar_days_r);
                        iconTypeMap.put(CALENDAR_CHECK, FontAwesome6IconType.calendar_check_s);
                        iconTypeMap.put(CHECK, FontAwesome6IconType.check_s);
                        iconTypeMap.put(CIRCLE_CHECK, FontAwesome6IconType.circle_check_r);
                        iconTypeMap.put(CLEAR, FontAwesome6IconType.eraser_s);
                        iconTypeMap.put(CLOCK_R, FontAwesome6IconType.clock_r);
                        iconTypeMap.put(CLOCK_S, FontAwesome6IconType.clock_s);
                        iconTypeMap.put(CLOSE, FontAwesome6IconType.xmark_s);
                        iconTypeMap.put(DATE, FontAwesome6IconType.calendar_days_r);
                        iconTypeMap.put(EDIT, FontAwesome6IconType.pen_to_square_r);
                        iconTypeMap.put(EMPTY, FontAwesome6IconType.star_r);
                        iconTypeMap.put(FILLED, FontAwesome6IconType.star_s);
                        iconTypeMap.put(FONT, FontAwesome6IconType.font_s);
                        iconTypeMap.put(HOME, FontAwesome6IconType.house_s);
                        iconTypeMap.put(NEXT, FontAwesome6IconType.arrow_right_s);
                        iconTypeMap.put(PREVIOUS, FontAwesome6IconType.arrow_left_s);
                        iconTypeMap.put(ROTATE, FontAwesome6IconType.rotate_s);
                        iconTypeMap.put(SEARCH, FontAwesome6IconType.magnifying_glass_s);
                        iconTypeMap.put(SORT, FontAwesome6IconType.sort_s);
                        iconTypeMap.put(SORT_DOWN, FontAwesome6IconType.sort_down_s);
                        iconTypeMap.put(SORT_UP, FontAwesome6IconType.sort_up_s);
                        iconTypeMap.put(TABLE_CELL, FontAwesome6IconType.table_cells_s);
                        iconTypeMap.put(THUMBS_DOWN, FontAwesome6IconType.thumbs_down_s);
                        iconTypeMap.put(THUMBS_UP, FontAwesome6IconType.thumbs_up_s);
                        iconTypeMap.put(TIME, FontAwesome6IconType.clock_r);
                        iconTypeMap.put(TODAY, FontAwesome6IconType.calendar_check_r);
                        iconTypeMap.put(UPLOAD, FontAwesome6IconType.upload_s);
                    } else if (cssResourceReference instanceof FontAwesome5CssReference) {
                        iconTypeMap.put(ALIGN_CENTER, FontAwesome5IconType.align_center_s);
                        iconTypeMap.put(ALIGN_LEFT, FontAwesome5IconType.align_left_s);
                        iconTypeMap.put(ALIGN_JUSTIFY, FontAwesome5IconType.align_justify_s);
                        iconTypeMap.put(ALIGN_RIGHT, FontAwesome5IconType.align_right_s);
                        iconTypeMap.put(ARROW_DOWN, FontAwesome5IconType.arrow_down_s);
                        iconTypeMap.put(ARROW_LEFT, FontAwesome5IconType.align_left_s);
                        iconTypeMap.put(ARROW_RIGHT, FontAwesome5IconType.align_right_s);
                        iconTypeMap.put(ARROW_UP, FontAwesome5IconType.arrow_up_s);
                        iconTypeMap.put(BOOK, FontAwesome5IconType.book_s);
                        iconTypeMap.put(BOOKMARK, FontAwesome5IconType.bookmark_r);
                        iconTypeMap.put(CALENDAR, FontAwesome5IconType.calendar_alt_r);
                        iconTypeMap.put(CALENDAR_CHECK, FontAwesome5IconType.calendar_check_s);
                        iconTypeMap.put(CHECK, FontAwesome5IconType.check_s);
                        iconTypeMap.put(CIRCLE_CHECK, FontAwesome5IconType.check_circle_r);
                        iconTypeMap.put(CLEAR, FontAwesome5IconType.eraser_s);
                        iconTypeMap.put(CLOCK_R, FontAwesome5IconType.clock_r);
                        iconTypeMap.put(CLOCK_S, FontAwesome5IconType.clock_s);
                        iconTypeMap.put(CLOSE, FontAwesome5IconType.times_s);
                        iconTypeMap.put(DATE, FontAwesome5IconType.calendar_alt_r);
                        iconTypeMap.put(EDIT, FontAwesome5IconType.edit_r);
                        iconTypeMap.put(EMPTY, FontAwesome5IconType.star_r);
                        iconTypeMap.put(FILLED, FontAwesome5IconType.star_s);
                        iconTypeMap.put(FONT, FontAwesome5IconType.font_s);
                        iconTypeMap.put(HOME, FontAwesome5IconType.home_s);
                        iconTypeMap.put(NEXT, FontAwesome5IconType.arrow_right_s);
                        iconTypeMap.put(PREVIOUS, FontAwesome5IconType.arrow_left_s);
                        iconTypeMap.put(ROTATE, FontAwesome5IconType.sync_alt_s);
                        iconTypeMap.put(SEARCH, FontAwesome5IconType.search_s);
                        iconTypeMap.put(SORT, FontAwesome5IconType.sort_s);
                        iconTypeMap.put(SORT_DOWN, FontAwesome5IconType.sort_down_s);
                        iconTypeMap.put(SORT_UP, FontAwesome5IconType.sort_up_s);
                        iconTypeMap.put(TABLE_CELL, FontAwesome5IconType.th_large_s);
                        iconTypeMap.put(THUMBS_DOWN, FontAwesome5IconType.thumbs_down_s);
                        iconTypeMap.put(THUMBS_UP, FontAwesome5IconType.thumbs_up_s);
                        iconTypeMap.put(TIME, FontAwesome5IconType.clock_r);
                        iconTypeMap.put(TODAY, FontAwesome5IconType.calendar_check_r);
                        iconTypeMap.put(UPLOAD, FontAwesome5IconType.upload_s);
                    }
                }
            }
        }
        return iconTypeMap.get(iconKey);
    }

    /** Pre-canned Icons that need to be either FontAwesome 5 or 6 specific */
    public enum IconKey {
        ALIGN_CENTER,
        ALIGN_JUSTIFY,
        ALIGN_LEFT,
        ALIGN_RIGHT,
        ARROW_DOWN,
        ARROW_LEFT,
        ARROW_RIGHT,
        ARROW_UP,
        BOOK,
        BOOKMARK,
        CALENDAR,
        CALENDAR_CHECK,
        CHECK,
        CIRCLE_CHECK,
        CLEAR,
        CLOCK_R,
        CLOCK_S,
        CLOSE,
        DATE,
        EDIT,
        EMPTY,
        FILLED,
        FONT,
        HOME,
        NEXT,
        PREVIOUS,
        ROTATE,
        SEARCH,
        SORT,
        SORT_DOWN,
        SORT_UP,
        TABLE_CELL,
        THUMBS_DOWN,
        THUMBS_UP,
        UPLOAD,
        TIME,
        TODAY,
        ;
    }
}
