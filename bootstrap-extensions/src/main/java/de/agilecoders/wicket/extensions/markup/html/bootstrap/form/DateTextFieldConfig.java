package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

/**
 * Configuration holder for all {@link DateTextField} configurations.
 *
 * @author miha
 * @author Urs Joss
 */
public class DateTextFieldConfig extends AbstractDateTextFieldConfig<DateTextFieldConfig, LocalDateTime> {

    @Override
    public DateTextFieldConfig withStartDate(final LocalDateTime value) {
        put(StartDate, getDateAsString(value));
        return this;
    }

    @Override
    public DateTextFieldConfig withEndDate(final LocalDateTime value) {
        put(EndDate, getDateAsString(Args.notNull(value, "value")));
        return this;
    }

    private String getDateAsString(final LocalDateTime value) {
        final String format = getFormat();
        if (Strings.isEmpty(format)) {
            return value.toString();
        } else {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return dateTimeFormatter.format(value);
        }
    }

}

