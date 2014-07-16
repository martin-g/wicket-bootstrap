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
     * #### Description
     * <p/>
     * translates a javascript date format into a java date format.
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Dates.toJavaDateFormat("ddmmYYYY"); // = "ddMMYYYY"
     * ```
     *
     * @param javaScriptDateFormat The javascript date format as string
     * @return java date format
     */
    public static String toJavaDateFormat(final String javaScriptDateFormat) {
        char[] chars = nullToEmpty(javaScriptDateFormat).toCharArray();

        String pattern = "";
        final StringBuilder finalPattern = new StringBuilder("");

        for (int i = 0, l = chars.length; i < l; i++) {
            boolean hasMore = i < l - 1;
            char token = chars[i];

            switch(token) {
                case 'd':
                    if (hasMore && chars[i + 1] == 'd') {
                        break;
                    }
                    finalPattern.append("dd");
                    pattern = "";
                    break;

                case 'm':
                    if (hasMore && chars[i + 1] == 'm') {
                        pattern += "m";
                        break;
                    }

                    if (pattern.length() < 2) {
                        finalPattern.append("m");
                    } else {
                        finalPattern.append("mm");
                    }

                    pattern = "";
                    break;

                case 'M':
                    if (hasMore && chars[i + 1] == 'M') {
                        pattern += "M";
                        break;
                    }

                    if (pattern.length() < 2) {
                        finalPattern.append("MMM");
                    } else {
                        finalPattern.append("MMMM");
                    }

                    pattern = "";
                    break;

                case 'D':
                    if (hasMore && chars[i + 1] == 'D') {
                        pattern += "D";
                        break;
                    }

                    if (pattern.length() < 2) {
                        finalPattern.append("E");
                    } else {
                        finalPattern.append("EEEE");
                    }

                    pattern = "";
                    break;

                default:
                    finalPattern.append(token);
            }
        }

        return finalPattern.toString();
    }

    /**
     * #### Description
     * <p/>
     * translates a java date format into a javascript date format.
     * <p/>
     * #### Usage
     * <p/>
     * ```java
     * Dates.toJavaDateFormat("ddMMYYYY"); // = "dmmYYYY"
     * ```
     *
     * @param javaDateFormat The java date format as string
     * @return javascript date format
     */
    public static String toJavaScriptDateFormat(final String javaDateFormat) {
        char[] chars = nullToEmpty(javaDateFormat).toCharArray();

        String pattern = "";
        final StringBuilder finalPattern = new StringBuilder("");

        for (int i = 0, l = chars.length; i < l; i++) {
            boolean hasMore = i < l - 1;
            char token = chars[i];

            switch(token) {
                case 'd':
                    if (hasMore && chars[i + 1] == 'd') {
                        break;
                    }
                    finalPattern.append("d");
                    pattern = "";
                    break;

                case 'M':
                    if (hasMore && chars[i + 1] == 'M') {
                        pattern += "M";
                        break;
                    }

                    if (pattern.length() <= 2) {
                        finalPattern.append(pattern.toLowerCase());
                    } else if (pattern.length() == 3) {
                        finalPattern.append("M");
                    } else if (pattern.length() > 3) {
                        finalPattern.append("MM");
                    }

                    pattern = "";
                    break;

                case 'E':
                    if (hasMore && chars[i + 1] == 'E') {
                        pattern += "E";
                        break;
                    }

                    if (pattern.length() <= 3) {
                        finalPattern.append("D");
                    } else {
                        finalPattern.append("DD");
                    }

                    pattern = "";
                    break;

                default:
                    finalPattern.append(token);
            }
        }

        return finalPattern.toString();
    }
}
