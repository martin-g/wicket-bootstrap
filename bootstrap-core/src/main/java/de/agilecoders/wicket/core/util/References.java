package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.Strings;

import static de.agilecoders.wicket.jquery.Strings2.nullToEmpty;

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
        if (!Strings.isEmpty(referenceUrl) && referenceUrl.contains(".") &&
            (Application.get().getResourceSettings().getUseMinifiedResources())) {
            return referenceUrl.replaceFirst("\\.(?=[^.]*$)", ".min.");
        }

        return nullToEmpty(referenceUrl);
    }

    /**
     * renders a given header item with filter if present.
     *
     * @param response  The current header response
     * @param reference The resource reference to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final ResourceReference reference) {
        renderWithFilter(response, JavaScriptHeaderItem.forReference(reference));
    }

    /**
     * renders a given header item with filter if present.
     *
     * @param response   The current header response
     * @param headerItem The header item to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final JavaScriptReferenceHeaderItem headerItem) {
        renderWithFilter(Bootstrap.getSettings(), response, headerItem);
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

        if (Strings.isEmpty(filterName)) {
            response.render(headerItem);
        } else {
            response.render(new FilteredHeaderItem(headerItem, filterName));
        }
    }

}
