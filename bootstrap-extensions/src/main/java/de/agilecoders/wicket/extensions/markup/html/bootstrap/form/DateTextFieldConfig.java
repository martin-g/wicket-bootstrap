package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.core.util.Dates;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Configuration holder for all {@link DateTextField} configurations.
 *
 * @author miha
 */
public final class DateTextFieldConfig extends AbstractConfig {

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

    /**
     * The earliest date that may be selected; all earlier dates will be disabled.
     */
    private static final IKey<String> StartDate = newKey("startDate", null);

    /**
     * The latest date that may be selected; all later dates will be disabled.
     */
    private static final IKey<String> EndDate = newKey("endDate", null);

    /**
     * The view that the datepicker should show when it is opened. Accepts values of
     * 0 or 'month' for month view (the default); 1 or 'year' for the 12-month overview,
     * and 2 or 'decade' for the 10-year overview. Useful for date-of-birth datepickers.
     */
    private static final IKey<Integer> StartView = newKey("startView", 0);

    /**
     * If true, displays a "Today" button at the bottom of the datepicker to select
     * the current date. If true, the "Today" button will only move the current date
     * into view;
     */
    private static final IKey<Boolean> ShowTodayButton = newKey("todayBtn", false);

    /**
     * Whether or not to allow date navigation by arrow keys.
     */
    private static final IKey<Boolean> KeyboardNavigation = newKey("keyboardNavigation", true);

    /**
     * The two-letter code of the language to use for month and day names.
     * These will also be used as the input's value (and subsequently sent to the
     * server in the case of form submissions). Currently ships with English ('en');
     * German ('de'), Brazilian ('br'), and Spanish ('es') translations, but others
     * can be added (see I18N below). If an unknown language code is given, English
     * will be used.
     */
    private static final IKey<String> Language = newKey("language", "en");

    /**
     * The date format, combination of d, dd, m, mm, M, MM, yy, yyyy.
     */
    private static final IKey<String> Format = newKey("format", "mm/dd/yyyy");

    /**
     * Day of the week start. 0 (Sunday) to 6 (Saturday)
     */
    private static final IKey<Integer> WeekStart = newKey("weekStart", 0);

    /**
     * If true, highlights the current date.
     */
    private static final IKey<Boolean> HighlightToday = newKey("todayHighlight", true);

    /**
     * Whether or not to close the datepicker immediately when a date is selected.
     */
    private static final IKey<Boolean> AutoClose = newKey("autoclose", false);

    /**
     * Whether or not to force parsing of the input value when the picker is closed.
     * That is, when an invalid date is left in the input field by the user, the picker
     * will forcibly parse that value, and set the input's value to the new, valid date,
     * conforming to the given format.
     */
    private static final IKey<Boolean> ForceParse = newKey("forceParse", true);

    /**
     * holds all week days in a specific sort order.
     */
    public enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    /**
     * holds all view options.
     */
    public enum View {
        Month, Year, Decade
    }

    /**
     * Construct.
     */
    public DateTextFieldConfig() {
        super();
    }

    /**
     * @return the date format as string
     */
    public String getFormat() {
        return Dates.toJavaDateFormat(getString(Format));
    }

    /**
     * @return the language to use
     */
    public String getLanguage() {
        return getString(Language);
    }

    /**
     * @return true if default language should be used.
     */
    public boolean isDefaultLanguageSet() {
        return Language.isDefaultValue(getLanguage());
    }

    /**
     * The earliest date that may be selected; all earlier dates will be disabled.
     *
     * @param value the earliest start date
     * @return this instance for chaining
     */
    public DateTextFieldConfig withStartDate(final DateTime value) {
        put(StartDate, value.toString(FORMATTER));
        return this;
    }

    /**
     * The latest date that may be selected; all later dates will be disabled.
     *
     * @param value the latest end date
     * @return this instance for chaining
     */
    public DateTextFieldConfig withEndDate(final DateTime value) {
        put(EndDate, value.toString(FORMATTER));
        return this;
    }

    /**
     * The two-letter code of the language to use for month and day names.
     * These will also be used as the input's value (and subsequently sent to the
     * server in the case of form submissions). Currently ships with English ('en'),
     * German ('de'), Brazilian ('br'), and Spanish ('es') translations, but others
     * can be added (see I18N below). If an unknown language code is given, English
     * will be used.
     *
     * @param value two letter language code (optional 5 letter code like de_DE)
     * @return this instance for chaining
     */
    public DateTextFieldConfig withLanguage(final String value) {
        put(Language, value);
        return this;
    }

    /**
     * The view that the datepicker should show when it is opened. Accepts values of
     * 0 or 'month' for month view (the default), 1 or 'year' for the 12-month overview,
     * and 2 or 'decade' for the 10-year overview. Useful for date-of-birth datepicker.
     *
     * @param value the start view to use
     * @return this instance for chaining
     */
    public DateTextFieldConfig withView(final View value) {
        put(StartView, value.ordinal());
        return this;
    }

    /**
     * The date format (java style), combination of d, dd, m, mm, M, MM, yy, yyyy.
     *
     * @param value The date format value (java style)
     * @return this instance for chaining
     */
    public DateTextFieldConfig withFormat(final String value) {
        put(Format, Dates.toJavaScriptDateFormat(value));
        return this;
    }

    /**
     * Day of the week start. 0 (Sunday) to 6 (Saturday)
     *
     * @param value the {@link Day} the week starts
     * @return this instance for chaining
     */
    public DateTextFieldConfig withWeekStart(final Day value) {
        put(WeekStart, value.ordinal());
        return this;
    }

    /**
     * Whether or not to allow date navigation by arrow keys.
     *
     * @param value true, if keyboard navigation is allowed
     * @return this instance for chaining
     */
    public DateTextFieldConfig allowKeyboardNavigation(final boolean value) {
        put(KeyboardNavigation, value);
        return this;
    }

    /**
     * If true, highlights the current date.
     *
     * @param value If true, highlights the current date.
     * @return this instance for chaining
     */
    public DateTextFieldConfig highlightToday(final boolean value) {
        put(HighlightToday, value);
        return this;
    }

    /**
     * If true, displays a "Today" button at the bottom of the datepicker to select
     * the current date. If true, the "Today" button will only move the current date
     * into view;
     *
     * @param value whether to show today button or not
     * @return this instance for chaining
     */
    public DateTextFieldConfig showTodayButton(final boolean value) {
        put(ShowTodayButton, value);
        return this;
    }

    /**
     * Whether or not to force parsing of the input value when the picker is closed.
     * That is, when an invalid date is left in the input field by the user, the picker
     * will forcibly parse that value, and set the input's value to the new, valid date,
     * conforming to the given format.
     *
     * @param value Whether or not to force parsing of the input value when the picker is closed
     * @return this instance for chaining
     */
    public DateTextFieldConfig forceParse(final boolean value) {
        put(ForceParse, value);
        return this;
    }

    /**
     * Whether or not to close the datepicker immediately when a date is selected.
     *
     * @param value true, if datepicker should close immediately when date is selected.
     * @return this instance for chaining
     */
    public DateTextFieldConfig autoClose(final boolean value) {
        put(AutoClose, value);
        return this;
    }
}
