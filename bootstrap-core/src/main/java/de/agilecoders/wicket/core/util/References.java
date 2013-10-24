package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.List;

import static de.agilecoders.wicket.core.util.Strings2.nullToEmpty;

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
     * @param response   The current header response
     * @param references The resource references to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final JavaScriptResourceReference... references) {
        List<JavaScriptReferenceHeaderItem> headerItems = new ArrayList<JavaScriptReferenceHeaderItem>();
        for (JavaScriptResourceReference reference : references) {
            headerItems.add(JavaScriptHeaderItem.forReference(reference));
        }
        renderWithFilter(response, headerItems.toArray(new JavaScriptReferenceHeaderItem[headerItems.size()]));
    }

    /**
     * renders a given header item with filter if present.
     *
     * @param response    The current header response
     * @param headerItems The header items to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final JavaScriptReferenceHeaderItem... headerItems) {
        renderWithFilter(Bootstrap.getSettings(), response, headerItems);
    }

    /**
     * renders a given header item with filter if present.
     *
     * @param settings    The bootstrap settings
     * @param response    The current header response
     * @param headerItems The header items to render
     */
    public static void renderWithFilter(final IBootstrapSettings settings, final IHeaderResponse response, final JavaScriptReferenceHeaderItem... headerItems) {
        final String filterName = settings.getJsResourceFilterName();

        if (Strings.isEmpty(filterName)) {
            for (JavaScriptReferenceHeaderItem headerItem : headerItems) {
                response.render(headerItem);
            }
        } else {
            for (JavaScriptReferenceHeaderItem headerItem : headerItems) {
                response.render(new FilteredHeaderItem(headerItem, filterName));
            }
        }
    }

}
