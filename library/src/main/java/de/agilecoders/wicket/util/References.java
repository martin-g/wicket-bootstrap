package de.agilecoders.wicket.util;

import com.google.common.base.Strings;
import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;

/**
 * Helper class for {@link org.apache.wicket.request.resource.ResourceReference} handling.
 *
 * @author miha
 */
public final class References {

    /**
     * Construct.
     */
    private References() {
        throw new UnsupportedOperationException();
    }

    /**
     * adds a ".min" extension in front of original extension if minimization is active.
     * If filename doesn't contain an extension no ".min" part will be added.
     *
     * e.g. "file.js" will be "file.min.js"
     *
     * @param referenceUrl The file name
     * @return file name containing ".min"
     */
    public static String appendMinificationIdentifier(final String referenceUrl) {
        if (!Strings.isNullOrEmpty(referenceUrl) && referenceUrl.contains(".") &&
            (Bootstrap.getSettings(Application.get()).isMinified())) {
            return referenceUrl.replaceFirst("\\.(?=[^.]*$)", ".min.");
        }

        return Strings.nullToEmpty(referenceUrl);
    }

}
