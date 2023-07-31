package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * #### Description
 *
 * Default {@link ITheme} implementation. This class can be used as base class for custom themes.
 *
 * #### Usage
 *
 * ```java
 * public class MyCustomTheme extends Theme {
 *     public MyCustomTheme() {
 *         super("my-custom-theme", new MyCustomThemeCssReference());
 *     }
 * }
 * ```
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class Theme implements ITheme {

    private final String name;
    private final List<HeaderItem> headerItems = new ArrayList<>();

    /**
     * Construct.
     *
     * @param name               Unique theme name
     * @param resourceReferences All references that are necessary for this theme
     */
    public Theme(final String name, final ResourceReference... resourceReferences) {
        this.name = name;

        for (ResourceReference resourceReference : resourceReferences) {
            if (resourceReference instanceof CssResourceReference) {
                headerItems.add(CssHeaderItem.forReference(resourceReference));
            } else if (resourceReference instanceof JavaScriptResourceReference) {
                headerItems.add(JavaScriptHeaderItem.forReference(resourceReference));
            }
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return headerItems;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        if (useCdnResources()) {
            Iterable<String> cdnUrls = getCdnUrls();
            if (cdnUrls != null && cdnUrls.iterator().hasNext()) {
                for (String cdnUrl : cdnUrls) {
                    response.render(CssHeaderItem.forReference(new UrlResourceReference(Url.parse(cdnUrl))));
                }
            }
            else {
                renderPackageReferences(response);
            }
        }
        else {
            renderPackageReferences(response);
        }
    }

    private boolean useCdnResources() {
        boolean result = false;

        if (Application.exists()) {
            IBootstrapSettings settings = Bootstrap.getSettings();
            result = settings.useCdnResources();
        }

        return result;
    }

    @Override
    public Iterable<String> getCdnUrls() {
        return List.of();
    }

    private void renderPackageReferences(IHeaderResponse response) {
        for (HeaderItem headerItem : getDependencies()) {
            response.render(headerItem);
        }
    }

    /**
     * @return The configured version of Bootstrap
     */
    protected String getVersion() {
        String version = IBootstrapSettings.VERSION;
        if (Application.exists()) {
            version = Bootstrap.getSettings().getVersion();
        }

        return version;
    }
}
