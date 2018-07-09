package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.agilecoders.wicket.core.util.Dates;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Abstract base class for implementations of {@code *DateTextFields}, i.e. for implementations
 * working with {@code java.util.Date} or {@code java.time.LocalDate}.This class provides the common
 * infrastructure that does not depend on the type of date being implemented.
 *
 * @param <C>
 *     The concrete configuration type
 * @param <I>
 *     the date type used as input for the {@literal with{Begin,End}Date} methods
 * @author miha
 * @author Urs Joss
 */
public abstract class AbstractDateTextFieldConfig<C extends AbstractDateTextFieldConfig, I> extends AbstractConfig {

    /**
     * The earliest date that may be selected; all earlier dates will be disabled.
     */
    protected static final IKey<String> StartDate = newKey("startDate", null);

    /**
     * The latest date that may be selected; all later dates will be disabled.
     */
    protected static final IKey<String> EndDate = newKey("endDate", null);

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
    private static final IKey<AbstractDateTextFieldConfig.TodayButton> ShowTodayButton = newKey("todayBtn",
        AbstractDateTextFieldConfig.TodayButton.FALSE);

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
    private static final IKey<Boolean> HighlightToday = newKey("todayHighlight", false);

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
     * Whether or not to show the clear button.
     */
    private static final IKey<Boolean> ClearButton = newKey("clearBtn", false);

    /**
     * Whether or not to show week numbers to the left of week rows.
     */
    private static final IKey<Boolean> CalendarWeeks = newKey("calendarWeeks", false);

    /**
     * Defines the min view of the date picker
     */
    private static final IKey<Integer> MinViewMode = newKey("minViewMode", null);
    /**
     * Defines if multi selection is enabled
     */
    private static final IKey<Boolean> Multidate   = newKey("multidate", false);

    /**
     * holds all week days in a specific sort order.
     */
    public enum Day {
        Sunday,
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday
    }

    /**
     * holds all view options.
     */
    public enum View {
        Month,
        Year,
        Decade,
        Day
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
     * @param value
     *     the earliest start date
     * @return this instance for chaining
     */
    public abstract C withStartDate(I value);

    /**
     * Sets the minimal view of the date picker
     *
     * @param minViewMode
     *     the minimal view (View.Decade is not supported and will be handle as Day)
     * @return this instance for chaining
     */
    public C withMinViewMode(AbstractDateTextFieldConfig.View minViewMode) {
        // +1 is necessary because date datepicker.js interprets Month as 1 and Year as 2;
        // but in our View enum Month = 0, Year = 1
        put(MinViewMode, minViewMode.ordinal() + 1);
        return (C) this;
    }

    /**
     * The latest date that may be selected; all later dates will be disabled.
     *
     * @param value
     *     the latest end date
     * @return this instance for chaining
     */
    public abstract C withEndDate(final I value);

    /**
     * The two-letter code of the language to use for month and day names.
     * These will also be used as the input's value (and subsequently sent to the
     * server in the case of form submissions). Currently ships with English ('en'),
     * German ('de'), Brazilian ('br'), and Spanish ('es') translations, but others
     * can be added (see I18N below). If an unknown language code is given, English
     * will be used.
     *
     * @param value
     *     two letter language code (optional 5 letter code like de_DE)
     * @return this instance for chaining
     */
    public C withLanguage(final String value) {
        put(Language, value);
        return (C) this;
    }

    /**
     * The view that the datepicker should show when it is opened. Accepts values of
     * 0 or 'month' for month view (the default), 1 or 'year' for the 12-month overview,
     * and 2 or 'decade' for the 10-year overview. Useful for date-of-birth datepicker.
     *
     * @param value
     *     the start view to use
     * @return this instance for chaining
     */
    public C withView(final AbstractDateTextFieldConfig.View value) {
        put(StartView, value.ordinal());
        return (C) this;
    }

    /**
     * The date format (java style), combination of d, dd, m, mm, M, MM, yy, yyyy.
     *
     * @param value
     *     The date format value (java style)
     * @return this instance for chaining
     */
    public C withFormat(final String value) {
        put(Format, Dates.toJavaScriptDateFormat(value));
        return (C) this;
    }

    /**
     * Day of the week start. 0 (Sunday) to 6 (Saturday)
     *
     * @param value
     *     the {@link AbstractDateTextFieldConfig.Day} the week starts
     * @return this instance for chaining
     */
    public C withWeekStart(final AbstractDateTextFieldConfig.Day value) {
        put(WeekStart, value.ordinal());
        return (C) this;
    }

    /**
     * Whether or not to allow date navigation by arrow keys.
     *
     * @param value
     *     true, if keyboard navigation is allowed
     * @return this instance for chaining
     */
    public C allowKeyboardNavigation(final boolean value) {
        put(KeyboardNavigation, value);
        return (C) this;
    }

    /**
     * If true, highlights the current date.
     *
     * @param value
     *     If true, highlights the current date.
     * @return this instance for chaining
     */
    public C highlightToday(final boolean value) {
        put(HighlightToday, value);
        return (C) this;
    }

    /**
     * If true, displays a "Today" button at the bottom of the datepicker to select
     * the current date. If true, the "Today" button will only move the current date
     * into view;
     *
     * @param value
     *     whether to show today button or not
     * @return this instance for chaining
     */
    public C showTodayButton(final AbstractDateTextFieldConfig.TodayButton value) {
        put(ShowTodayButton, value);
        return (C) this;
    }

    /**
     * Whether or not to force parsing of the input value when the picker is closed.
     * That is, when an invalid date is left in the input field by the user, the picker
     * will forcibly parse that value, and set the input's value to the new, valid date,
     * conforming to the given format.
     *
     * @param value
     *     Whether or not to force parsing of the input value when the picker is closed
     * @return this instance for chaining
     */
    public C forceParse(final boolean value) {
        put(ForceParse, value);
        return (C) this;
    }

    /**
     * @param value
     *     Whether or not to display a 'clear' button, which allows for clearing the input field.
     * @return this instance for chaining
     */
    public C clearButton(final boolean value) {
        put(ClearButton, value);
        return (C) this;
    }

    /**
     * @param value
     *     Whether or not to show week numbers to the left of week rows.
     * @return this instance for chaining
     */
    public C calendarWeeks(final boolean value) {
        put(CalendarWeeks, value);
        return (C) this;
    }

    /**
     * Whether or not to close the datepicker immediately when a date is selected.
     *
     * @param value
     *     true, if datepicker should close immediately when date is selected.
     * @return this instance for chaining
     */
    public C autoClose(final boolean value) {
        put(AutoClose, value);
        return (C) this;
    }

    /**
     * Whether multi date selection is enabled.
     *
     * @param value
     *     true, if datepicker should accept multiple selections
     * @return this instance for chaining
     */
    public C withMulti(final boolean value) {
        put(Multidate, value);
        return (C) this;
    }

    /**
     * See <a href="http://bootstrap-datepicker.readthedocs.org/en/latest/options.html#todaybtn">docs</a>.
     * Today button could be a boolean or string <em>"linked"</em>:
     * <cite>If true or “linked”, displays a “Today” button at the bottom of the datepicker to select the
     * current date. If true, the “Today” button will only move the current date into view; if “linked”,
     * the current date will also be selected.</cite>
     */
    @JsonSerialize(using = TodayButtonSerializer.class)
    public static enum TodayButton {
        TRUE,
        FALSE,
        LINKED
    }

    /**
     * Serializer for TodayButton
     */
    private static class TodayButtonSerializer extends JsonSerializer<TodayButton> {

        @Override
        public void serialize(AbstractDateTextFieldConfig.TodayButton value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
            switch (value) {
            case TRUE:
                jgen.writeBoolean(true);
                break;
            case FALSE:
                jgen.writeBoolean(false);
                break;
            case LINKED:
                jgen.writeString("linked");
                break;
            }
        }
    }
}
