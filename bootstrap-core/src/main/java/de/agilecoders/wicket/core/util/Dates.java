package de.agilecoders.wicket.core.util;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

/**
 * Helper class for dates.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class Dates {

    /**
     * Construct.
     */
    private Dates() {
        throw new UnsupportedOperationException();
    }

    /**
     * # Description
     *
     * translates a javascript date format into a java date format.
     *
     * # Usage
     *
     * ```java
     * Dates.toJavaDateFormat("ddmmYYYY"); // = "ddMMYYYY"
     * ```
     *
     * @param javaScriptDateFormat The javascript date format as string
     * @return java date format
     */
    public static String toJavaDateFormat(final String javaScriptDateFormat) {
        return nullToEmpty(javaScriptDateFormat).replaceAll("mm", "MM");
    }

    /**
     * # Description
     *
     * translates a java date format into a javascript date format.
     *
     * # Usage
     *
     * ```java
     * Dates.toJavaDateFormat("ddMMYYYY"); // = "ddmmYYYY"
     * ```
     *
     * @param javaDateFormat The java date format as string
     * @return javascript date format
     */
    public static String toJavaScriptDateFormat(final String javaDateFormat) {
        return nullToEmpty(javaDateFormat).replaceAll("MM", "mm");
    }
}
