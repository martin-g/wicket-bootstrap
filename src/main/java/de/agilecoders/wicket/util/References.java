package de.agilecoders.wicket.util;

import com.google.common.base.Strings;
import de.agilecoders.wicket.protocol.http.IBootstrapApplication;
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
            ((IBootstrapApplication) Application.get()).getBootstrapSettings().isMinified()) {
            return referenceUrl.replaceFirst("\\.(?=[^.]*$)", ".min.");
        }

        return Strings.nullToEmpty(referenceUrl);
    }

}
