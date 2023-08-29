package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json.RawValue;

/**
 * Config of datetime picker plugin.
 *
 * @see <a href="https://getdatepicker.com/6/options/">JS widget options</a>
 */
public class TempusDominusConfig<T> extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    private static final IKey<Boolean> AllowInputToggle = newKey("allowInputToggle", false);
    private static final IKey<RawValue> Container = newKey("container", null);
    private static final IKey<Boolean> DateRange = newKey("dateRange", false);
    private static final IKey<RawValue> DefaultDate = newKey("defaultDate", null);
    private static final IKey<TempusDominusDisplayConfig<?>> Display = newKey("display", null);
    private static final IKey<Boolean> KeepInvalid = newKey("keepInvalid", false);
    private static final IKey<TempusDominusLocalizationConfig<?>> Localization = newKey("localization", null);
    private static final IKey<Map<String, Object>> Meta = newKey("meta", null);
    private static final IKey<Boolean> MultipleDates = newKey("multipleDates", false);
    private static final IKey<String> MultipleDatesSeparator = newKey("multipleDatesSeparator", null);
    private static final IKey<Boolean> PromptTimeOnDateChange = newKey("promptTimeOnDateChange", false);
    private static final IKey<Integer> PromptTimeOnDateChangeTransitionDelay = newKey("promptTimeOnDateChangeTransitionDelay", null);
    private static final IKey<TempusDominusRestrictionsConfig<?>> Restrictions = newKey("restrictions", null);
    private static final IKey<Integer> Stepping = newKey("stepping", null);
    private static final IKey<Boolean> UseCurrent = newKey("useCurrent", true);
    private static final IKey<RawValue> ViewDate = newKey("viewDate", null);

    /**
     * Construct config
     */
    public TempusDominusConfig(Class<T> clazz) {
        put(Display, new TempusDominusDisplayConfig<>(clazz));
        put(Localization, new TempusDominusLocalizationConfig<>(clazz));
        put(Restrictions, new TempusDominusRestrictionsConfig<T>());
    }

    /**
     * @param allow If true, the picker will show on textbox focus.
     * @return current instance
     */
    public TempusDominusConfig<T> withAllowInputToggle(boolean allow) {
        put(AllowInputToggle, allow);
        return this;
    }

    /**
     * @param container Change the target container to use for the widget instead of body
     *    (In case of application using shadow DOM for example).
     * @return current instance
     */
    public TempusDominusConfig<T> withContainer(String container) {
        put(Container, new RawValue(container));
        return this;
    }

    /**
     * @param dateRange Date Range work similar to multi date. You should also set multiDateSeparator
     *      with what you want the two values to be separated with. This option allows the user to
     *      select two dates and highlights all the dates in range between. Validation still takes
     *      place. The range will be consider invalid if any of the dates in the range are disabled.
     * @return current instance
     */
    public TempusDominusConfig<T> withDateRange(boolean dateRange) {
        put(DateRange, dateRange);
        return this;
    }

    /**
     * @param defDate Sets the picker default date/time. Overrides useCurrent
     * @return current instance
     */
    public TempusDominusConfig<T> withDefaultDate(T defDate) {
        put(DefaultDate, createJsDate(defDate));
        return this;
    }

    /**
     * @param cfg The display options allow you to control much of the picker's look and feel.
     *      You can disable components, buttons and change the default icons.
     * @return current instance
     */
    public TempusDominusConfig<T> withDisplayConfig(TempusDominusDisplayConfig<T> cfg) {
        put(Display, cfg);
        return this;
    }

    /**
     * @param keep Allows for the user to select a date that is invalid according to the rules.
     * @return current instance
    */
    public TempusDominusConfig<T> withKeepInvalid(boolean keep) {
        put(KeepInvalid, keep);
        return this;
    }

    /**
     * @param cfg Most of the localization options are for title tooltips over icons.
     * @return current instance
     */
    public TempusDominusConfig<T> withLocalization(TempusDominusLocalizationConfig<T> cfg) {
        put(Localization, cfg);
        return this;
    }

    /**
     * @param map This property is to provide developers a place to store extra information
     *      about the picker.
     * @return current instance
     */
    public TempusDominusConfig<T> withMeta(Map<String, Object> map) {
        put(Meta, map);
        return this;
    }

    /**
     * @param multiple Allows multiple dates to be selected.
     * @return current instance
     */
    public TempusDominusConfig<T> withMultipleDates(boolean multiple) {
        put(MultipleDates, multiple);
        return this;
    }

    /**
     * @param separator When multipleDates is enabled, this value wil be used to separate
     *      the selected dates.
     * @return current instance
     */
    public TempusDominusConfig<T> withMultipleDatesSeparator(String separator) {
        put(MultipleDatesSeparator, separator);
        return this;
    }

    /**
     * @param prompt If enabled and any of the time components are enabled, when a user
     *      selects a date the picker will automatically display the clock view after
     *      `promptTimeOnDateChangeTransitionDelay`.
     * @return current instance
     */
    public TempusDominusConfig<T> withPromptTimeOnDateChange(boolean prompt) {
        put(PromptTimeOnDateChange, prompt);
        return this;
    }

    /**
     * @param delay Used with promptTimeOnDateChange. The number of milliseconds before the
     *      picker will display the clock view.
     * @return current instance
     */
    public TempusDominusConfig<T> withPromptTimeOnDateChangeTransitionDelay(int delay) {
        put(PromptTimeOnDateChangeTransitionDelay, delay);
        return this;
    }

    /**
     * @param cfg Restrictions allow you to prevent users from selected dates or times based
     *      on a set of rules.
     * @return current instance
     */
    public TempusDominusConfig<T> withRestrictions(TempusDominusRestrictionsConfig<T> cfg) {
        put(Restrictions, cfg);
        return this;
    }

    /**
     * @param step Controls how much the minutes are changed by. This also changes the minute
     *      selection grid to step by this amount.
     * @return current instance
     */
    public TempusDominusConfig<T> withStepping(int step) {
        put(Stepping, step);
        return this;
    }

    /**
     * @param use Determines if the current date/time should be used as the default value when
     *      the picker is opened.
     * @return current instance
     */
    public TempusDominusConfig<T> withUseCurrent(boolean use) {
        put(UseCurrent, use);
        return this;
    }

    /**
     * @param date Set the view date of the picker. Setting this will not change the selected date(s).
     * @return current instance
     */
    public TempusDominusConfig<T> withViewDate(T date) {
        put(ViewDate, createJsDate(date));
        return this;
    }

    /**
     * @return Date format pattern set for TempusDominusLocalizationConfig.getFormat()
     */
    public String getFormat() {
        return get(Localization).getFormat();
    }

    static <U> RawValue createJsDate(U date) {
        String strDate = null;
        if (date instanceof Date) {
            strDate = ISO_INSTANT.format(((Date)date).toInstant());
        } else if (date instanceof LocalTime) {
            strDate = ISO_LOCAL_TIME.format((LocalTime)date);
        } else if (date instanceof LocalDate) {
            strDate = ISO_LOCAL_DATE.format((LocalDate)date);
        } else if (date instanceof LocalDateTime) {
            strDate = ISO_LOCAL_DATE_TIME.format((LocalDateTime)date);
        } else if (date instanceof ZonedDateTime) {
            strDate = ISO_ZONED_DATE_TIME.format((ZonedDateTime)date);
        }
        return strDate == null ? null : new RawValue("new Date('" + strDate + "')");
    }
}