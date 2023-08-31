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
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json.RawValue;

/**
 * Config of datetime picker plugin.
 *
 * @see <a href="https://getdatepicker.com/6/options/">JS widget options</a>
 */
public class TempusDominusConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    private static final IKey<Boolean> AllowInputToggle = newKey("allowInputToggle", false);
    private static final IKey<RawValue> Container = newKey("container", null);
    private static final IKey<Boolean> DateRange = newKey("dateRange", false);
    private static final IKey<RawValue> DefaultDate = newKey("defaultDate", null);
    private static final IKey<TempusDominusDisplayConfig> Display = newKey("display", null);
    private static final IKey<Boolean> KeepInvalid = newKey("keepInvalid", false);
    private static final IKey<Map<String, Object>> Meta = newKey("meta", null);
    private static final IKey<Boolean> MultipleDates = newKey("multipleDates", false);
    private static final IKey<String> MultipleDatesSeparator = newKey("multipleDatesSeparator", null);
    private static final IKey<Boolean> PromptTimeOnDateChange = newKey("promptTimeOnDateChange", false);
    private static final IKey<Integer> PromptTimeOnDateChangeTransitionDelay = newKey("promptTimeOnDateChangeTransitionDelay", null);
    private static final IKey<TempusDominusRestrictionsConfig> Restrictions = newKey("restrictions", null);
    private static final IKey<Integer> Stepping = newKey("stepping", null);
    private static final IKey<Boolean> UseCurrent = newKey("useCurrent", true);
    private static final IKey<RawValue> ViewDate = newKey("viewDate", null);

    private TempusDominusLocalizationConfig localization = new TempusDominusLocalizationConfig();

    /**
     * Construct config
     */
    public TempusDominusConfig() {
        put(Display, new TempusDominusDisplayConfig());
        put(Restrictions, new TempusDominusRestrictionsConfig());
    }

    /**
     * Will add restrictions based on class LocalDate/LocalTime/LocalDateTime etc.
     *
     * @param clazz Class of DateTime object to set restrictions
     * @return current instance
     */
    public <T> TempusDominusConfig withClass(Class<T> clazz) {
        withDisplay(cfg -> cfg.withClass(clazz));
        withLocalization(cfg -> cfg.withClass(clazz));
        return this;
    }

    /**
     * @param allow If true, the picker will show on textbox focus.
     * @return current instance
     */
    public TempusDominusConfig withAllowInputToggle(boolean allow) {
        put(AllowInputToggle, allow);
        return this;
    }

    /**
     * @param container Change the target container to use for the widget instead of body
     *    (In case of application using shadow DOM for example).
     * @return current instance
     */
    public TempusDominusConfig withContainer(String container) {
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
    public TempusDominusConfig withDateRange(boolean dateRange) {
        put(DateRange, dateRange);
        return this;
    }

    /**
     * @param defDate Sets the picker default date/time. Overrides useCurrent
     * @return current instance
     */
    public <T> TempusDominusConfig withDefaultDate(T defDate) {
        put(DefaultDate, createJsDate(defDate));
        return this;
    }

    /**
     * @param cfgUpdater Consumer accepting TempusDominusDisplayConfig so it can be updated:
     *      The display options allow you to control much of the picker's look and feel.
     *      You can disable components, buttons and change the default icons.
     * @return current instance
     */
    public TempusDominusConfig withDisplay(Consumer<TempusDominusDisplayConfig> cfgUpdater) {
        cfgUpdater.accept(get(Display));
        return this;
    }

    /**
     * Shortcut for Display -> Icons
     *
     * @param cfgUpdater Consumer accepting TempusDominusIconConfig so it can be updated:
     *      The display options allow you to control much of the picker's look and feel.
     *      You can disable components, buttons and change the default icons.
     * @return current instance
     */
    public TempusDominusConfig withIcons(Consumer<TempusDominusIconConfig> cfgUpdater) {
        get(Display).withIcons(cfgUpdater);
        return this;
    }

    /**
     * @param keep Allows for the user to select a date that is invalid according to the rules.
     * @return current instance
    */
    public TempusDominusConfig withKeepInvalid(boolean keep) {
        put(KeepInvalid, keep);
        return this;
    }

    /**
     * @param cfgUpdater Consumer accepting TempusDominusDisplayConfig so it can be updated:
     *      Most of the localization options are for title tooltips over icons.
     * @return current instance
     */
    public TempusDominusConfig withLocalization(Consumer<TempusDominusLocalizationConfig> cfgUpdater) {
        cfgUpdater.accept(localization);
        return this;
    }

    /**
     * @param map This property is to provide developers a place to store extra information
     *      about the picker.
     * @return current instance
     */
    public TempusDominusConfig withMeta(Map<String, Object> map) {
        put(Meta, map);
        return this;
    }

    /**
     * @param multiple Allows multiple dates to be selected.
     * @return current instance
     */
    public TempusDominusConfig withMultipleDates(boolean multiple) {
        put(MultipleDates, multiple);
        return this;
    }

    /**
     * @param separator When multipleDates is enabled, this value wil be used to separate
     *      the selected dates.
     * @return current instance
     */
    public TempusDominusConfig withMultipleDatesSeparator(String separator) {
        put(MultipleDatesSeparator, separator);
        return this;
    }

    /**
     * @param prompt If enabled and any of the time components are enabled, when a user
     *      selects a date the picker will automatically display the clock view after
     *      `promptTimeOnDateChangeTransitionDelay`.
     * @return current instance
     */
    public TempusDominusConfig withPromptTimeOnDateChange(boolean prompt) {
        put(PromptTimeOnDateChange, prompt);
        return this;
    }

    /**
     * @param delay Used with promptTimeOnDateChange. The number of milliseconds before the
     *      picker will display the clock view.
     * @return current instance
     */
    public TempusDominusConfig withPromptTimeOnDateChangeTransitionDelay(int delay) {
        put(PromptTimeOnDateChangeTransitionDelay, delay);
        return this;
    }

    /**
     * @param cfgUpdater Consumer accepting TempusDominusDisplayConfig so it can be updated:
     *      Restrictions allow you to prevent users from selected dates or times based
     *      on a set of rules.
     * @return current instance
     */
    public TempusDominusConfig withRestrictions(Consumer<TempusDominusRestrictionsConfig> cfgUpdater) {
        cfgUpdater.accept(get(Restrictions));
        return this;
    }

    /**
     * @param step Controls how much the minutes are changed by. This also changes the minute
     *      selection grid to step by this amount.
     * @return current instance
     */
    public TempusDominusConfig withStepping(int step) {
        put(Stepping, step);
        return this;
    }

    /**
     * @param use Determines if the current date/time should be used as the default value when
     *      the picker is opened.
     * @return current instance
     */
    public TempusDominusConfig withUseCurrent(boolean use) {
        put(UseCurrent, use);
        return this;
    }

    /**
     * @param date Set the view date of the picker. Setting this will not change the selected date(s).
     * @return current instance
     */
    public <T> TempusDominusConfig withViewDate(T date) {
        put(ViewDate, createJsDate(date));
        return this;
    }

    /**
     * @return Date format pattern set for TempusDominusLocalizationConfig.getFormat()
     */
    public String getFormat() {
        return localization.getFormat();
    }

    /**
     * @return Date format pattern set for TempusDominusLocalizationConfig.getFormat()
     */
    public Locale getLocale() {
        return localization.getLocale();
    }

    /**
     * @return TempusDominusLocalizationConfig
     */
    TempusDominusLocalizationConfig getLocalization() {
        return localization;
    }

    public static <U> String formatDateISO(U date) {
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
        return strDate;
    }

    static <U> RawValue createJsDate(U date) {
        String strDate = formatDateISO(date);
        return strDate == null ? null : new RawValue("new Date('" + strDate + "')");
    }
}
