package de.agilecoders.wicket.core.util;

/**
 * helper class to handle string interaction
 *
 * @author miha
 */
public final class Strings2 {

    /**
     * ensures a non null value.
     *
     * @param value string value to transform to an empty string if it's null
     * @return non null value
     */
    public static String nullToEmpty(final String value) {
        return value != null ? value : "";
    }

    /**
     * private constructor to prevent instantiation of util class.
     */
    private Strings2() {
        throw new UnsupportedOperationException();
    }
}
