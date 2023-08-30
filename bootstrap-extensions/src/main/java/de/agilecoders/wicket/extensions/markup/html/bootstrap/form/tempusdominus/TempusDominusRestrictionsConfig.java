package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json.RawValue;

/**
 * Config of datetime picker plugin.
 *
 * @see <a href="https://getdatepicker.com/6/options/">JS widget options</a>
 */
public class TempusDominusRestrictionsConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    private static final IKey<RawValue> MinDate = newKey("minDate", null);
    private static final IKey<RawValue> MaxDate = newKey("maxDate", null);
    private static final IKey<List<RawValue>> DisabledDates = newKey("disabledDates", null);
    private static final IKey<List<RawValue>> EnabledDates = newKey("enabledDates", null);
    private static final IKey<List<Integer>> DaysOfWeekDisabled = newKey("daysOfWeekDisabled", null);
    private static final IKey<List<Map<String, RawValue>>> DisabledTimeIntervals = newKey("disabledTimeIntervals", null);
    private static final IKey<List<Integer>> EnabledHours = newKey("enabledHours", null);
    private static final IKey<List<Integer>> DisabledHours = newKey("disabledHours", null);

    public TempusDominusRestrictionsConfig() {
        put(DisabledDates, new ArrayList<>());
        put(EnabledDates, new ArrayList<>());
        put(DaysOfWeekDisabled, new ArrayList<>());
        put(DisabledTimeIntervals, new ArrayList<>());
        put(EnabledHours, new ArrayList<>());
        put(DisabledHours, new ArrayList<>());
    }

    /**
     * @param date Prevents the user from selecting a date/time before this value.
     * @return current instance
     */
    public <T> TempusDominusRestrictionsConfig withMinDate(T date) {
        put(MinDate, TempusDominusConfig.createJsDate(date));
        return this;
    }

    /**
     * @param date Prevents the user from selecting a date/time after this value.
     * @return current instance
     */
    public <T> TempusDominusRestrictionsConfig withMaxDate(T date) {
        put(MaxDate, TempusDominusConfig.createJsDate(date));
        return this;
    }

    /**
     * Adds the dates to disabled list
     *
     * @param date Disallows the user to select any of the provided days. Setting this takes precedence over options.minDate, options.maxDate configuration.
     * @return current instance
     */
    public <T> TempusDominusRestrictionsConfig withDisabledDate(T date) {
        Args.isTrue(get(EnabledDates).isEmpty(), "Use one or the other, don't provide both enabledDates and disabledDates.");
        get(DisabledDates).add(TempusDominusConfig.createJsDate(date));
        return this;
    }

    /**
     * Adds the dates to enabled list
     *
     * @param date Allows the user to select only from the provided days. Setting this takes precedence over options.minDate, options.maxDate configuration.
     * @return current instance
     */
    public <T> TempusDominusRestrictionsConfig withEnabledDate(T date) {
        Args.isTrue(get(DisabledDates).isEmpty(), "Use one or the other, don't provide both enabledDates and disabledDates.");
        get(EnabledDates).add(TempusDominusConfig.createJsDate(date));
        return this;
    }

    /**
     * Adds day of week to disabled list
     *
     * @param day Disallow the user to select weekdays that exist in this array. This has lower priority over the
     *      options.minDate, options.maxDate, options.disabledDates and options.enabledDates configuration settings.
     * @return current instance
     */
    public TempusDominusRestrictionsConfig withDayOfWeekDisabled(int day) {
        Args.withinRange(0,6, day, "dayOfWeek");
        get(DaysOfWeekDisabled).add(day);
        return this;
    }

    /**
     * Disables time selection between the given DateTimes.
     *
     * @param from start of interval
     * @param to end of interval
     * @return current instance
     */
    public <T> TempusDominusRestrictionsConfig withDisabledTimeInterval(T from, T to) {
        get(DisabledTimeIntervals).add(Map.of(
                "from", TempusDominusConfig.createJsDate(from),
                "to", TempusDominusConfig.createJsDate(to)));
        return this;
    }

    /**
     * Adds hour to enabled list
     *
     * @param hour Allows the user to select only from the provided hours.
     * @return current instance
     */
    public TempusDominusRestrictionsConfig withEnabledHour(int hour) {
        Args.isTrue(get(DisabledHours).isEmpty(), "Use one or the other, don't provide both enabledHours and disabledHours.");
        Args.withinRange(0,23, hour, "hour");
        get(EnabledHours).add(hour);
        return this;
    }

    /**
     * Adds hour to disabled list
     *
     * @param hour Disallows the user to select any of the provided hours.
     * @return current instance
     */
    public TempusDominusRestrictionsConfig withDisabledHour(int hour) {
        Args.isTrue(get(EnabledHours).isEmpty(), "Use one or the other, don't provide both enabledHours and disabledHours.");
        Args.withinRange(0,23, hour, "hour");
        get(DisabledHours).add(hour);
        return this;
    }
}
