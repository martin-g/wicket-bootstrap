package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import de.agilecoders.wicket.extensions.javascript.jasny.InputMaskBehavior;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.behavior.Behavior;

import java.text.SimpleDateFormat;
import java.util.Date;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

/**
 * Config of datetime picker plugin.
 *
 * @author Alexey Volkov
 * @since 02.02.2015
 * @see <a href="http://eonasdan.github.io/bootstrap-datetimepicker/Options/">JS widget options</a>
 */
public class DatetimePickerConfig extends AbstractConfig {

    public enum ViewModeType {
        /**
         * year view
         */
        YEARS("years"),
        /**
         * months view
         */
        MONTHS("months"),
        /**
         * days view
         */
        DAYS("days");

        private final String code;

        ViewModeType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    private static final long serialVersionUID = 1L;

    private static final String DIGITS_PATTERN = "(?i)(y|m|d|h|s)";

    private static final IKey<String> Format = newKey("format", null);
    private static final IKey<Boolean> UseCurrent = newKey("useCurrent", true);
    private static final IKey<Boolean> CalendarWeeks = newKey("calendarWeeks", false);
    private static final IKey<Integer> MinuteStepping = newKey("stepping", 1);
    private static final IKey<String> MinDate = newKey("minDate", null);
    private static final IKey<String> MaxDate = newKey("maxDate", null);
    private static final IKey<String> DefaultDate = newKey("defaultDate", null);
    private static final IKey<String> ViewMode = newKey("viewMode", null);
    private static final IKey<String> Locale = newKey("locale", null);

    private static final IKey<Boolean> ShowToday = newKey("showTodayButton", false);
    private static final IKey<Boolean> ShowClose = newKey("showClose", false);
    private static final IKey<Boolean> Collapse = newKey("collapse", true);
    private static final IKey<Boolean> SideBySide = newKey("sideBySide", false);
    private static final IKey<Boolean> Strict = newKey("useStrict", false);

    private static final IKey<String[]> DisabledDates = newKey("disabledDates", null);
    private static final IKey<String[]> EnabledDates = newKey("enabledDates", null);
    private static final IKey<DatetimePickerIconConfig> Icons = newKey("icons", null);

    private Boolean maskInput = false;

    /**
     * Construct config
     */
    public DatetimePickerConfig() {
        useLocale("en-gb");
        withFormat("MM/dd/yyyy");
    }

    /**
     * @param locale The moment.js locale
     * @return current instance
     */
    public DatetimePickerConfig useLocale(String locale) {
        put(Locale, locale);
        return this;
    }

    /**
     * @param maskInput mask input
     * @return current instance
     */
    public DatetimePickerConfig useMaskInput(boolean maskInput) {
        this.maskInput = maskInput;
        return this;
    }

    /**
     * @return mask input
     */
    public Boolean getMaskInput() {
        return maskInput;
    }

    /**
     * Set date format.
     *
     * @param format date format
     * @return config instance
     */
    public DatetimePickerConfig withFormat(String format) {
        put(Format, toJavaScriptDateFormat(format));
        return this;
    }

    /**
     * Set icon config.
     *
     * @param iconConfig icon config
     * @return config instance
     */
    public DatetimePickerConfig with(DatetimePickerIconConfig iconConfig) {
        put(Icons, iconConfig);
        return this;
    }

    /**
     * Set view mode
     *
     * @param view view mode
     * @return config instance
     */
    public DatetimePickerConfig useView(ViewModeType view) {
        put(ViewMode, view.getCode());
        return this;
    }

    /**
     * Set side by side parameter
     *
     * @param sideBySide side by side
     * @return config instance
     */
    public DatetimePickerConfig useSideBySide(boolean sideBySide) {
        put(SideBySide, sideBySide);
        return this;
    }

    /**
     * Sets strict date parsing
     *
     * @param strict A flag indicating whether or not to use strict date parsing
     * @return config instance
     */
    public DatetimePickerConfig useStrictParsing(boolean strict) {
        put(Strict, strict);
        return this;
    }
    /**
     * Set current date in input.
     *
     * @param useCurrent flag
     * @return config instance
     */
    public DatetimePickerConfig useCurrent(boolean useCurrent) {
        put(UseCurrent, useCurrent);
        return this;
    }

    /**
     * Set calendar weeks.
     *
     * @param calendarWeeks flag
     * @return config instance
     */
    public DatetimePickerConfig useCalendarWeeks(boolean calendarWeeks) {
        put(CalendarWeeks, calendarWeeks);
        return this;
    }

    /**
     * Set minute stepping.
     *
     * @param minuteStepping interval minutes
     * @return config instance
     */
    public DatetimePickerConfig withMinuteStepping(int minuteStepping) {
        put(MinuteStepping, minuteStepping);
        return this;
    }

    /**
     * Set minimum date.
     *
     * @param minDate minimum date
     * @return config instance
     */
    public DatetimePickerConfig withMinDate(Date minDate) {
        put(MinDate, defaultDateValueFormatter().format(minDate));
        return this;
    }

    /**
     * Set maximum date.
     *
     * @param maxDate maximum date
     * @return config instance
     */
    public DatetimePickerConfig withMaxDate(Date maxDate) {
        put(MaxDate, defaultDateValueFormatter().format(maxDate));
        return this;
    }

    /**
     * Set the default date.
     *
     * @param defaultDate  the default date
     * @return config instance
     */
    public DatetimePickerConfig withDefaultDate(Date defaultDate) {
        put(DefaultDate, defaultDateValueFormatter().format(defaultDate));
        return this;
    }

    /**
     * Get date formatter based config format.
     *
     * @return date formatter
     */
    private SimpleDateFormat defaultDateValueFormatter() {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }

    /**
     * Show today.
     *
     * @param showToday flag
     * @return config instance
     */
    public DatetimePickerConfig setShowToday(boolean showToday) {
        put(ShowToday, showToday);
        return this;
    }

    /**
     * Show close button.
     *
     * @param showClose flag
     * @return config instance
     */
    public DatetimePickerConfig setShowClose(boolean showClose) {
        put(ShowClose, showClose);
        return this;
    }

    /**
     * Set collapse.
     *
     * @param collapse flag
     * @return config instance
     */
    public DatetimePickerConfig setCollapse(Boolean collapse) {
        put(Collapse, collapse);
        return this;
    }

    /**
     * Set disabled
     *
     * @param disabledDates array disabled dates
     * @return config instance
     */
    public DatetimePickerConfig withDisabledDates(Date[] disabledDates) {
        put(DisabledDates, convertDatesToStrings(disabledDates));
        return this;
    }

    /**
     * Set enabled
     *
     * @param enabledDates array enabled dates
     * @return config instance
     */
    public DatetimePickerConfig withEnabledDates(Date[] enabledDates) {
        put(EnabledDates, convertDatesToStrings(enabledDates));
        return this;
    }

    /**
     * Get date format.
     *
     * @return date format
     */
    public String getFormat() {
        return toJavaDateFormat(getString(Format));
    }

    /**
     * Get minute stepping
     *
     * @return minute stepping
     */
    public String getMinuteStepping() {
        return getString(MinuteStepping);
    }

    /**
     * Get minute stepping
     *
     * @return minute stepping
     */
    public ViewModeType getViewMode() {
        return ViewModeType.valueOf(getString(ViewMode));
    }

    /**
     * Convert array dates to array strings.
     *
     * @param dates array
     * @return array strings
     */
    private String[] convertDatesToStrings(Date[] dates) {
        String[] stringDates = new String[dates.length];
        SimpleDateFormat formatter = customDateFormatter();
        int i = 0;
        for (Date date : dates) {
            stringDates[i++] = formatter.format(date);
        }
        return stringDates;
    }

    /**
     * Get date formatter based config format.
     *
     * @return date formatter
     */
    private SimpleDateFormat customDateFormatter() {
        return new SimpleDateFormat(getFormat());
    }

    /**
     * Convert java script date format to java.
     *
     * @param javaScriptDateFormat date format
     * @return java date format
     */
    public static String toJavaDateFormat(final String javaScriptDateFormat) {
        return nullToEmpty(javaScriptDateFormat).replace('D', 'd').replace('Y', 'y');
    }

    /**
     * Convert java date format to java script.
     *
     * @param javaDateFormat date format
     * @return java script date format
     */
    public static String toJavaScriptDateFormat(final String javaDateFormat) {
        return nullToEmpty(javaDateFormat).replace('d', 'D').replace('y', 'Y');
    }

    /**
     * generates new mask behavior for text input
     *
     * @return new input mask behavior
     */
    public Behavior newMaskBehavior() {
        return new InputMaskBehavior() {

            private static final long serialVersionUID = 5414514378068933745L;

            @Override
            protected String getMask() {
                return getFormat().replaceAll(DIGITS_PATTERN, "9");
            }
        };
    }
}
