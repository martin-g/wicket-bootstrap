package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Configuration holder for all {@link DateTextField} configurations.
 *
 * @author miha
 * @author Urs Joss
 */
public class DateTextFieldConfig extends AbstractDateTextFieldConfig<DateTextFieldConfig, DateTime> {

    @Override
    public DateTextFieldConfig withStartDate(final DateTime value) {
        put(StartDate, getDateAsString(value));
        return this;
    }

    @Override
    public DateTextFieldConfig withEndDate(final DateTime value) {
        put(EndDate, getDateAsString(Args.notNull(value, "value")));
        return this;
    }

    private String getDateAsString(final DateTime value) {
        final String format = getFormat();
        if (Strings.isEmpty(format)) {
            return value.toString();
        } else {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
            return dateTimeFormatter.print(value);
        }
    }

}

