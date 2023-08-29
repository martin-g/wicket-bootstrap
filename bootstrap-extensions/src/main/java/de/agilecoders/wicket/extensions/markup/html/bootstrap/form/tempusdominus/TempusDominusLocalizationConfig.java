package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json.RawValue;

/**
 * Config of datetime picker plugin.
 *
 * @see <a href="https://getdatepicker.com/6/options/">JS widget options</a>
 */
public class TempusDominusLocalizationConfig<T> extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    public enum HourCycleType {
        H11("h11"), // Midnight: 00 AM; Night: 11 PM
        H12("h12"), // Midnight: 12 AM; Night: 11 PM
        H23("h23"), // Midnight: 00;    Night: 23
        H24("h24"); // Midnight: 01;    Night: 24

        private final String type;

        HourCycleType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum DateFormatType {
        LTS,   // Long form time format.
        LT,    // Short time format.
        L,     // Standard date format.
        LL,    // Long form date format. US default is July 4, 2022.
        LLL,   // Long form date/time format. US default is July 4, 2022 9:30 AM.
        LLLL;  // Long form date/time format with weekday. US default is Monday, July 4, 2022 9:30 AM.
    }

    private static final IKey<String> Today = newKey("today", null);
    private static final IKey<String> Clear = newKey("clear", null);
    private static final IKey<String> Close = newKey("close", null);
    private static final IKey<String> SelectMonth = newKey("selectMonth", null);
    private static final IKey<String> PreviousMonth = newKey("previousMonth", null);
    private static final IKey<String> NextMonth = newKey("nextMonth", null);
    private static final IKey<String> SelectYear = newKey("selectYear", null);
    private static final IKey<String> PreviousYear = newKey("previousYear", null);
    private static final IKey<String> NextYear = newKey("nextYear", null);
    private static final IKey<String> SelectDecade = newKey("selectDecade", null);
    private static final IKey<String> PreviousDecade = newKey("previousDecade", null);
    private static final IKey<String> NextDecade = newKey("nextDecade", null);
    private static final IKey<String> PreviousCentury = newKey("previousCentury", null);
    private static final IKey<String> NextCentury = newKey("nextCentury", null);
    private static final IKey<String> PickHour = newKey("pickHour", null);
    private static final IKey<String> IncrementHour = newKey("incrementHour", null);
    private static final IKey<String> DecrementHour = newKey("decrementHour", null);
    private static final IKey<String> PickMinute = newKey("pickMinute", null);
    private static final IKey<String> IncrementMinute = newKey("incrementMinute", null);
    private static final IKey<String> DecrementMinute = newKey("decrementMinute", null);
    private static final IKey<String> PickSecond = newKey("pickSecond", null);
    private static final IKey<String> IncrementSecond = newKey("incrementSecond", null);
    private static final IKey<String> DecrementSecond = newKey("decrementSecond", null);
    private static final IKey<String> ToggleMeridiem = newKey("toggleMeridiem", null);
    private static final IKey<String> SelectTime = newKey("selectTime", null);
    private static final IKey<String> SelectDate = newKey("selectDate", null);
    private static final IKey<Map<String, String>> DayViewHeaderFormat = newKey("dayViewHeaderFormat", null);
    private static final IKey<String> Locale = newKey("locale", null);
    private static final IKey<Integer> StartOfTheWeek = newKey("startOfTheWeek", 0);
    private static final IKey<String> HourCycle = newKey("hourCycle", null);
    private static final IKey<Map<String, String>> DateFormats = newKey("dateFormats", null);
    private static final IKey<RawValue> Ordinal = newKey("ordinal", null);
    private static final IKey<String> Format = newKey("format", null);

    private final Class<T> clazz;
    private String javaFormat;

    /**
     * Construct config
     */
    public TempusDominusLocalizationConfig(Class<T> clazz) {
        this.clazz = clazz;
        put(DateFormats, new HashMap<>(6));
        withLocale(Session.get().getLocale());
    }

    /**
     * @param label Label for "Go to today"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withToday(String label) {
        put(Today, label);
        return this;
    }

    /**
     * @param label Label for "Clear selection"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withClear(String label) {
        put(Clear, label);
        return this;
    }

    /**
     * @param label Label for "Close the picker"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withClose(String label) {
        put(Close, label);
        return this;
    }

    /**
     * @param label Label for "Select Month"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withSelectMonth(String label) {
        put(SelectMonth, label);
        return this;
    }

    /**
     * @param label Label for "Previous Month"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPreviousMonth(String label) {
        put(PreviousMonth, label);
        return this;
    }

    /**
     * @param label Label for "Next Month"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withNextMonth(String label) {
        put(NextMonth, label);
        return this;
    }

    /**
     * @param label Label for "Select Year"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withSelectYear(String label) {
        put(SelectYear, label);
        return this;
    }

    /**
     * @param label Label for "Previous Year"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPreviousYear(String label) {
        put(PreviousYear, label);
        return this;
    }

    /**
     * @param label Label for "Next Year"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withNextYear(String label) {
        put(NextYear, label);
        return this;
    }

    /**
     * @param label Label for "Select Decade"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withSelectDecade(String label) {
        put(SelectDecade, label);
        return this;
    }

    /**
     * @param label Label for "Previous Decade"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPreviousDecade(String label) {
        put(PreviousDecade, label);
        return this;
    }

    /**
     * @param label Label for "Next Decade"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withNextDecade(String label) {
        put(NextDecade, label);
        return this;
    }

    /**
     * @param label Label for "Previous Century"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPreviousCentury(String label) {
        put(PreviousCentury, label);
        return this;
    }

    /**
     * @param label Label for "Next Century"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withNextCentury(String label) {
        put(NextCentury, label);
        return this;
    }

    /**
     * @param label Label for "Pick Hour"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPickHour(String label) {
        put(PickHour, label);
        return this;
    }

    /**
     * @param label Label for "Increment Hour"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withIncrementHour(String label) {
        put(IncrementHour, label);
        return this;
    }

    /**
     * @param label Label for "Decrement Hour"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withDecrementHour(String label) {
        put(DecrementHour, label);
        return this;
    }

    /**
     * @param label Label for "Pick Minute"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPickMinute(String label) {
        put(PickMinute, label);
        return this;
    }

    /**
     * @param label Label for "Increment Minute"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withIncrementMinute(String label) {
        put(IncrementMinute, label);
        return this;
    }

    /**
     * @param label Label for "Decrement Minute"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withDecrementMinute(String label) {
        put(DecrementMinute, label);
        return this;
    }

    /**
     * @param label Label for "Pick Second"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withPickSecond(String label) {
        put(PickSecond, label);
        return this;
    }

    /**
     * @param label Label for "Increment Second"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withIncrementSecond(String label) {
        put(IncrementSecond, label);
        return this;
    }

    /**
     * @param label Label for "Decrement Second"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withDecrementSecond(String label) {
        put(DecrementSecond, label);
        return this;
    }

    /**
     * @param label Label for "Toggle Period"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withToggleMeridiem(String label) {
        put(ToggleMeridiem, label);
        return this;
    }

    /**
     * @param label Label for "Select Time"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withSelectTime(String label) {
        put(SelectTime, label);
        return this;
    }

    /**
     * @param label Label for "Select Date"
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withSelectDate(String label) {
        put(SelectDate, label);
        return this;
    }

    /**
     * @param format This should be an appropriate value from the Intl.DateFormat options.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withDayViewHeaderFormat(Map<String, String> format) {
        put(DayViewHeaderFormat, format);
        return this;
    }

    private static String getPattern(DateFormat format) {
        return ((SimpleDateFormat)format).toLocalizedPattern();
    }

    /**
     * This method will set locale and date/time formats based on Locale passed
     *
     * @param locale This should be a BCP 47 language tag or a value supported by Intl.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withLocale(java.util.Locale locale) {
        put(Locale, locale.toLanguageTag());
        withDateFormat(DateFormatType.LTS, getPattern(SimpleDateFormat.getTimeInstance(DateFormat.LONG, locale)));
        final String timeFmt = getPattern(SimpleDateFormat.getTimeInstance(DateFormat.MEDIUM, locale));
        withDateFormat(DateFormatType.LT, timeFmt);
        final String dateFmt = getPattern(SimpleDateFormat.getDateInstance(DateFormat.SHORT, locale));
        withDateFormat(DateFormatType.L, dateFmt);
        withDateFormat(DateFormatType.LL, getPattern(SimpleDateFormat.getDateInstance(DateFormat.LONG, locale)));
        withDateFormat(DateFormatType.LLL, getPattern(SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale)));
        withDateFormat(DateFormatType.LLL, getPattern(SimpleDateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG, locale)));
        if (LocalTime.class == clazz) {
            withFormat(timeFmt);
        } else if (LocalDate.class == clazz) {
            withFormat(dateFmt);
        } else {
            withFormat(getPattern(SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale)));
        }
        return this;
    }

    /**
     * This method will set locale as String
     *
     * @param locale This should be a BCP 47 language tag or a value supported by Intl.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withLocale(String locale) {
        put(Locale, locale);
        return this;
    }

    /**
     * @param start Changes the start of the week to the provided index. Intl/Date does not provide
     *      apis to get the locale's start of the week. 0 = Sunday, 6 = Saturday. If you want the
     *      calendar view to start on Monday, set this option to 1.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withStartOfTheWeek(int start) {
        Args.withinRange(0,6, start, "start");
        put(StartOfTheWeek, start);
        return this;
    }

    /**
     * @param cycle Changes how the hours are displayed. If left undefined, the picker will attempt to guess.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withHourCycle(HourCycleType cycle) {
        put(HourCycle, cycle.getType());
        return this;
    }

    /**
     * Will add given format to the Map
     *
     * These options describe shorthand format strings.
     *
     * @param type - which format to set
     * @param format - format as String
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withDateFormat(DateFormatType type, String format) {
        get(DateFormats).put(type.name(), format);
        return this;
    }

    /**
     * @param ordinalFunc Function to convert cardinal numbers to ordinal numbers, e.g. 3 -> third.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withOrdinal(String ordinalFunc) {
        put(Ordinal, new RawValue(ordinalFunc));
        return this;
    }

    /**
     * NOTE: this format is used for parsing Java date, so this should be valid Java format
     *
     * @param format Default tokenized format to use.
     * @return current instance
     */
    public TempusDominusLocalizationConfig<T> withFormat(String format) {
        this.javaFormat = format;
        put(Format, toJavaScriptDateFormat(format));
        return this;
    }

    private static String toJavaScriptDateFormat(final String javaFormat) {
        return nullToEmpty(javaFormat).replace('a', 't');
    }

    public String getFormat() {
        return javaFormat;
    }
}
