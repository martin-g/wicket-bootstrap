package de.agilecoders.wicket.util;

import com.google.common.base.Strings;
import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public final class References {

    private References() {}

    /**
     * TODOC
     *
     * @param referenceUrl
     * @return
     */
    public static String appendMinificationIdentifier(final String referenceUrl) {
        if (!Strings.isNullOrEmpty(referenceUrl) && referenceUrl.contains(".") &&
            (Bootstrap.getSettings(Application.get()).isMinified())) {
            return referenceUrl.replaceFirst("\\.(?=[^.]*$)", ".min.");
        }

        return Strings.nullToEmpty(referenceUrl);
    }

}
