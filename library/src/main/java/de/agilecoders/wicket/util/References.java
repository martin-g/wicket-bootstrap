package de.agilecoders.wicket.util;

import com.google.common.base.Strings;
import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;

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
     * <p/>
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

    /**
     * renders a given header item with filter if present.
     *
     * @param settings   The bootstrap settings
     * @param response   The current header response
     * @param headerItem The header item to render
     */
    public static void renderWithFilter(final IBootstrapSettings settings, final IHeaderResponse response, final JavaScriptReferenceHeaderItem headerItem) {
        final String filterName = settings.getJsResourceFilterName();

        if (Strings.isNullOrEmpty(filterName)) {
            response.render(headerItem);
        } else {
            response.render(new FilteredHeaderItem(headerItem, filterName));
        }
    }

}
