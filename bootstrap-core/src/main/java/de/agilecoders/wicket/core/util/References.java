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
import java.util.regex.Pattern;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

/**
 * #### Description
 *
 * Helper class for {@link org.apache.wicket.request.resource.ResourceReference} handling.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class References {

    private static final Pattern MIN_PATTERN = Pattern.compile("\\.(?=[^.]*$)");

    /**
     * Construct.
     */
    private References() {
        throw new UnsupportedOperationException();
    }

    /**
     * #### Description
     *
     * adds a ".min" extension in front of original extension if minimization is active.
     * If filename doesn't contain an extension no ".min" part will be added.
     * <p/>
     *
     * #### Usage
     *
     * ```java
     * References.appendMinificationIdentifier(""); // = ""
     * References.appendMinificationIdentifier(null); // = ""
     * References.appendMinificationIdentifier("test"); // = "test"
     * References.appendMinificationIdentifier("test.js"); // = "test.min.js"
     * References.appendMinificationIdentifier("test.custom.js"); // = "test.custom.min.js"
     * ```
     *
     * @param referenceUrl The reference url to append ".min" to
     * @return file name containing ".min"
     */
    public static String appendMinificationIdentifier(final String referenceUrl) {
        if (!Strings.isEmpty(referenceUrl) && referenceUrl.contains(".") &&
            (Application.get().getResourceSettings().getUseMinifiedResources())) {
            return MIN_PATTERN.matcher(referenceUrl).replaceFirst(".min.");
        }

        return nullToEmpty(referenceUrl);
    }

    /**
     * #### Description
     *
     * renders a given header item with as a `FilteredHeaderItem` with filter name from bootstrap settings if present.
     *
     * #### Usage
     *
     * you can call this method in `renderHeader(IHeaderResponse response)`:
     *
     * ```java
     * References.renderWithFilter(response, myCustomJsReference, mySecondJsReference);
     * ```
     *
     * @param response   The current header response
     * @param references The resource references to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final JavaScriptResourceReference... references) {
        List<JavaScriptReferenceHeaderItem> headerItems = new ArrayList<>();
        for (JavaScriptResourceReference reference : references) {
            headerItems.add(JavaScriptHeaderItem.forReference(reference));
        }
        renderWithFilter(response, headerItems.toArray(new JavaScriptReferenceHeaderItem[headerItems.size()]));
    }

    /**
     * #### Description
     *
     * renders a given header item with as a `FilteredHeaderItem` with filter name from bootstrap settings if present.
     *
     * #### Usage
     *
     * you can call this method in `renderHeader(IHeaderResponse response)`:
     *
     * ```java
     * References.renderWithFilter(response, myCustomJsReferenceHeaderItem, mySecondJsReferenceHeaderItem);
     * ```
     *
     * @param response   The current header response
     * @param headerItems The header items to render
     */
    public static void renderWithFilter(final IHeaderResponse response, final JavaScriptReferenceHeaderItem... headerItems) {
        renderWithFilter(Bootstrap.getSettings(), response, headerItems);
    }

    /**
     * #### Description
     *
     * renders a given header item with as a `FilteredHeaderItem` with filter name from bootstrap settings if present.
     *
     * #### Usage
     *
     * you can call this method in `renderHeader(IHeaderResponse response)`:
     *
     * ```java
     * References.renderWithFilter(myBootstrapSettings, response, myCustomJsReferenceHeaderItem, mySecondJsReferenceHeaderItem);
     * ```
     *
     * @param settings    The bootstrap settings
     * @param response   The current header response
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
