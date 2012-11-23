package de.agilecoders.wicket.markup.html.bootstrap.form;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.apache.wicket.util.lang.Objects;

/**
 * Configuration holder for all {@link DateTextField} configurations.
 *
 * @author miha
 * @version 1.0
 */
public final class DateTextFieldConfig extends AbstractConfig {

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

    /**
     * holds all possible configurations
     */
    public enum Key implements IKey {

        /**
         * The earliest date that may be selected; all earlier dates will be disabled.
         */
        StartDate("startDate", String.class, null),

        /**
         * The latest date that may be selected; all later dates will be disabled.
         */
        EndDate("endDate", String.class, null),

        /**
         * The view that the datepicker should show when it is opened. Accepts values of
         * 0 or 'month' for month view (the default), 1 or 'year' for the 12-month overview,
         * and 2 or 'decade' for the 10-year overview. Useful for date-of-birth datepickers.
         */
        StartView("startView", Integer.class, 0),

        /**
         * If true, displays a "Today" button at the bottom of the datepicker to select
         * the current date. If true, the "Today" button will only move the current date
         * into view;
         */
        ShowTodayButton("todayBtn", Boolean.class, false),

        /**
         * Whether or not to allow date navigation by arrow keys.
         */
        KeyboardNavigation("keyboardNavigation", Boolean.class, true),

        /**
         * The two-letter code of the language to use for month and day names.
         * These will also be used as the input's value (and subsequently sent to the
         * server in the case of form submissions). Currently ships with English ('en'),
         * German ('de'), Brazilian ('br'), and Spanish ('es') translations, but others
         * can be added (see I18N below). If an unknown language code is given, English
         * will be used.
         */
        Language("language", String.class, "en"),

        /**
         * The date format, combination of d, dd, m, mm, M, MM, yy, yyyy.
         */
        Format("format", String.class, "mm/dd/yyyy"),

        /**
         * Day of the week start. 0 (Sunday) to 6 (Saturday)
         */
        WeekStart("weekStart", Integer.class, 0),

        /**
         * If true, highlights the current date.
         */
        HighlightToday("todayHighlight", Boolean.class, true),

        /**
         * Whether or not to close the datepicker immediately when a date is selected.
         */
        AutoClose("autoclose", Boolean.class, false),

        /**
         * Whether or not to force parsing of the input value when the picker is closed.
         * That is, when an invalid date is left in the input field by the user, the picker
         * will forcibly parse that value, and set the input's value to the new, valid date,
         * conforming to the given format.
         */
        ForceParse("forceParse", Boolean.class, true);

        private final String key;
        private final Class type;
        private final Object defaultValue;

        private Key(final String key, final Class type, final Object defaultValue) {
            this.key = key;
            this.type = type;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void assertCorrectType(final Object value) {
            Preconditions.checkArgument(type.isInstance(value));
        }

        @Override
        public boolean isDefaultValue(final Object value) {
            return Objects.equal(value, defaultValue);
        }

        @Override
        public Object getDefaultValue() {
            return defaultValue;
        }
    }

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

    public DateTextFieldConfig() {
        super();
    }

    public String getFormat() {
        return getString(Key.Format);
    }

    public String getLanguage() {
        return getString(Key.Language);
    }

    public DateTextFieldConfig withStartDate(final DateTime value) {
        put(Key.StartDate, value.toString(FORMATTER));
        return this;
    }

    public DateTextFieldConfig withEndDate(final DateTime value) {
        put(Key.EndDate, value.toString(FORMATTER));
        return this;
    }

    public DateTextFieldConfig withLanguage(final String value) {
        put(Key.Language, value.toLowerCase());
        return this;
    }

    public DateTextFieldConfig withView(final View value) {
        put(Key.StartView, value.ordinal());
        return this;
    }

    public DateTextFieldConfig withFormat(final String value) {
        put(Key.Format, value.toLowerCase());
        return this;
    }

    public DateTextFieldConfig withWeekStart(final Day value) {
        put(Key.WeekStart, value.ordinal());
        return this;
    }

    public DateTextFieldConfig allowKeyboardNavigation(final boolean value) {
        put(Key.KeyboardNavigation, value);
        return this;
    }

    public DateTextFieldConfig highlightToday(final boolean value) {
        put(Key.HighlightToday, value);
        return this;
    }

    public DateTextFieldConfig showTodayButton(final boolean value) {
        put(Key.ShowTodayButton, value);
        return this;
    }

    public DateTextFieldConfig forceParse(final boolean value) {
        put(Key.ForceParse, value);
        return this;
    }

    public DateTextFieldConfig autoClose(final boolean value) {
        put(Key.AutoClose, value);
        return this;
    }
}
