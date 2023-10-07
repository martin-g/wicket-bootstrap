package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Config of datetime picker plugin.
 *
 * @see <a href="https://getdatepicker.com/6/options/">JS widget options</a>
 */
public class TempusDominusDisplayConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    public enum ButtonType {
        TODAY("today"),
        CLEAR("clear"),
        CLOSE("close");

        private final String type;

        ButtonType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum ComponentType {
        CALENDAR("calendar"),
        DATE("date"),
        MONTH("month"),
        YEAR("year"),
        DECADES("decades"),
        CLOCK("clock"),
        HOURS("hours"),
        MINUTES("minutes"),
        SECONDS("seconds");

        private final String component;

        ComponentType(String component) {
            this.component = component;
        }

        public String getComponent() {
            return component;
        }
    }

    public enum ViewModeType {
        /**
         * clock view
         */
        CLOCK("clock"),
        /**
         * calendar view
         */
        CALENDAR("calendar"),
        /**
         * months view
         */
        MONTHS("months"),
        /**
         * year view
         */
        YEARS("years"),
        /**
         * decades view
         */
        DECADES("decades");

        private final String code;

        ViewModeType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum ToolbarPlacementType {
        TOP("top"),
        BOTTOM("bottom");

        private final String placement;

        ToolbarPlacementType(String placement) {
            this.placement = placement;
        }

        public String getPlacement() {
            return placement;
        }
    }

    public enum ThemeType {
        LIGHT("light"),
        DARK("dark"),
        AUTO("auto");

        private final String theme;

        ThemeType(String theme) {
            this.theme = theme;
        }

        public String getTheme() {
            return theme;
        }
    }

    private static final IKey<TempusDominusIconConfig> Icons = newKey("icons", null);
    private static final IKey<Boolean> SideBySide = newKey("sideBySide", false);
    private static final IKey<Boolean> CalendarWeeks = newKey("calendarWeeks", false);
    private static final IKey<String> ViewMode = newKey("viewMode", null);
    private static final IKey<String> ToolbarPlacement = newKey("toolbarPlacement", null);
    private static final IKey<Boolean> KeepOpen = newKey("keepOpen", false);
    private static final IKey<Map<String, Boolean>> Buttons = newKey("buttons", null);
    private static final IKey<Map<String, Boolean>> Components = newKey("components", null);
    private static final IKey<Boolean> Inline = newKey("inline", false);
    private static final IKey<String> Theme = newKey("theme", null);

    /**
     * Construct config
     */
    public TempusDominusDisplayConfig() {
        put(Icons, new TempusDominusIconConfig());
        put(Buttons, new HashMap<>(3));
        put(Components, new HashMap<>(9));
    }

    /**
     * Will add restrictions based on class Date/LocalDate/LocalTime/LocalDateTime etc.
     *
     * @param clazz Class of DateTime object to set restrictions
     * @return current instance
     */
    public <T extends Temporal> TempusDominusDisplayConfig withClass(Class<T> clazz) {
        if (LocalTime.class == clazz) {
            withComponent(ComponentType.CALENDAR, false);
            withViewMode(ViewModeType.CLOCK);
        } else if (LocalDate.class == clazz) {
            withComponent(ComponentType.CLOCK, false);
        }
        return this;
    }

    /**
     * @param cfgUpdater Consumer accepting TempusDominusIconConfig so it can be updated:
     *      Any icon library that expects icons to be used like
     *      &lt;i class='fas fa-calendar'&gt;&lt;/i&gt; will work, provided you include the
     *      correct styles and scripts needed.
     * @return current instance
     */
    public TempusDominusDisplayConfig withIcons(Consumer<TempusDominusIconConfig> cfgUpdater) {
        cfgUpdater.accept(get(Icons));
        return this;
    }

    /**
     * @param sideBySide Displays the date and time pickers side by side.
     * @return current instance
     */
    public TempusDominusDisplayConfig withSideBySide(boolean sideBySide) {
        put(SideBySide, sideBySide);
        return this;
    }

    /**
     * @param calendarWeeks Displays an additional column with the calendar week for that week.
     * @return current instance
     */
    public TempusDominusDisplayConfig withCalendarWeeks(boolean calendarWeeks) {
        put(CalendarWeeks, calendarWeeks);
        return this;
    }

    /**
     * @param view The default view when the picker is displayed. Set to "years" for a date of
     *      birth picker.
     * @return current instance
     */
    public TempusDominusDisplayConfig withViewMode(ViewModeType view) {
        put(ViewMode, view.getCode());
        return this;
    }

    /**
     * @param place Changes the placement of the toolbar where the today, clear, component switch
     *      icon are located.
     * @return current instance
     */
    public TempusDominusDisplayConfig withToolbarPlacement(ToolbarPlacementType place) {
        put(ToolbarPlacement, place.getPlacement());
        return this;
    }

    /**
     * @param keep Keep the picker window open even after a date selection.
     * @return current instance
     */
    public TempusDominusDisplayConfig withKeepOpen(boolean keep) {
        put(KeepOpen, keep);
        return this;
    }

    /**
     * Will add given type to the Map
     *
     * @param type - Type of the button to set visibility
     * @param value - Visible or not
     * @return current instance
     */
    public TempusDominusDisplayConfig withButton(ButtonType type, boolean value) {
        get(Buttons).put(type.getType(), value);
        return this;
    }

    /**
     * Will add given component to the Map
     *
     * These options turns on or off the particular views. If option is false for date
     *      the user would only be able to select month and year for instance.
     *
     * @param cmp - view name
     * @param on - visible or not
     * @return current instance
     */
    public TempusDominusDisplayConfig withComponent(ComponentType cmp, boolean on) {
        get(Components).put(cmp.getComponent(), on);
        return this;
    }

    /**
     * @param inline Displays the picker in a inline div instead of a popup.
     * @return current instance
     */
    public TempusDominusDisplayConfig withInline(boolean inline) {
        put(Inline, inline);
        return this;
    }

    /**
     * @param theme Specifies which theme to use, light mode or dark mode. When set to auto, it will auto
     *      detect based on settings of the user's system.
     * @return current instance
     */
    public TempusDominusDisplayConfig withTheme(ThemeType theme) {
        put(Theme, theme.getTheme());
        return this;
    }
}
