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
	 * ```java Dates.toJavaDateFormat("ddmmYYYY"); // = "ddMMYYYY" ```
	 * 
	 * @param javaScriptDateFormat
	 *            The javascript date format as string
	 * @return java date format
	 */
	public static String toJavaDateFormat(final String javaScriptDateFormat) {

		char[] chars = nullToEmpty(javaScriptDateFormat).toCharArray();
		final StringBuilder result = new StringBuilder();

		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {

			case 'm':
				result.append("MM"); // m -> MM
				break;

			case 'M':
				if (i + 1 < chars.length && chars[i + 1] == 'M') {
					i++;
					result.append("MMMM"); // MM -> MMMM
				} else {
					result.append("MMM"); // M -> MMM
				}
				break;

			case 'd':
				result.append("dd"); // d -> dd
				break;

			case 'D':
				if (i + 1 < chars.length && chars[i + 1] == 'D') {
					i++;
					result.append("EEEE"); // DD - > EEEE
				} else {
					result.append("EEE"); // D -> EEE
				}
				break;

			default:
				result.append(chars[i]); // as is
			}
		}

		return result.toString();
	}

	/**
	 * #### Description
	 * <p/>
	 * translates a java date format into a javascript date format.
	 * <p/>
	 * #### Usage
	 * <p/>
	 * ```java Dates.toJavaDateFormat("ddMMYYYY"); // = "dmmYYYY" ```
	 * 
	 * @param javaDateFormat
	 *            The java date format as string
	 * @return javascript date format
	 */
	public static String toJavaScriptDateFormat(final String javaDateFormat) {

		char[] chars = nullToEmpty(javaDateFormat).toCharArray();
		final StringBuilder result = new StringBuilder();

		for (int i = 0, l = chars.length; i < l; i++) {
			switch (chars[i]) {

			case 'M':
				if (i + 1 < chars.length && chars[i + 1] == 'M') {
					i++;
					if (i + 1 < chars.length && chars[i + 1] == 'M') {
						i++;
						if (i + 1 < chars.length && chars[i + 1] == 'M') {
							i++;
							result.append("MM"); // MMMM -> MM
						} else {
							result.append("M"); // MMM -> M
						}
					} else {
						result.append("m"); // MM -> m
					}
				} else {
					result.append("m"); // M -> m
				}
				break;

			case 'd':
				if (i + 1 < chars.length && chars[i + 1] == 'd') {
					i++;
					result.append("d"); // dd -> d
				} else {
					result.append("d"); // d -> d
				}
				break;

			case 'E':
				if (i + 1 < chars.length && chars[i + 1] == 'E') {
					i++;
					if (i + 1 < chars.length && chars[i + 1] == 'E') {
						i++;
						if (i + 1 < chars.length && chars[i + 1] == 'E') {
							i++;
							result.append("DD"); // EEEE -> DD
						} else {
							result.append("D"); // EEE -> D
						}
					} else {
						result.append("D"); // EE -> D
					}
				} else {
					result.append("D"); // E -> D
				}
				break;

			default:
				result.append(chars[i]); // as is
			}
		}

		return result.toString();
	}
}
