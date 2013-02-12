package de.agilecoders.wicket.markup.html.bootstrap.html;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * TODO: document
 *
 * @author miha
 */
public class HtmlTag extends TransparentWebMarkupContainer {
    private final boolean useModrnizr;
    private Locale locale; // TODO: make this field immutable
    private final ClientProperties clientProperties;

    /**
     * Construct.
     *
     * @param id
     */
    public HtmlTag(String id, final boolean useModrnizr) {
        super(id);

        this.useModrnizr = useModrnizr;
        this.clientProperties = new WebClientInfo(getRequestCycle()).getProperties();
        this.locale = Locale.ENGLISH;
    }

    /**
     * Construct.
     *
     * @param id the component id
     */
    public HtmlTag(final String id) {
        this(id, false);
    }

    public Locale locale() {
        return locale;
    }

    public HtmlTag locale(Locale locale) {
        this.locale = locale;
        return this;
    }

    /**
     * transforms a locale to an attribute value
     *
     * @param locale current locale
     * @return locale as attribute
     */
    private String toAttributeValue(Locale locale) {
        return locale.toString().replace("_", "-").toLowerCase();
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "html");

        if (locale != null) {
            tag.put("lang", toAttributeValue(locale));
        }

        final CssClassNames.Builder cssClassNames = CssClassNames.newBuilder();
        if (useModrnizr) {
            cssClassNames.add("no-js");
        }

        cssClassNames.add(createBrowserShortcut(clientProperties));
        cssClassNames.add("theme-" + Bootstrap.getSettings().getActiveThemeProvider().getActiveTheme().name());

        tag.put("class", cssClassNames.asString());
    }

    /**
     * creates a browser shortcuts to identify old IE versions.
     *
     * @param clientProperties current client properties
     * @return a set of browser shortcuts
     */
    private Set<String> createBrowserShortcut(final ClientProperties clientProperties) {
        Set<String> shortcut = new HashSet<String>();

        if (clientProperties.isBrowserInternetExplorer()) {
            if (clientProperties.getBrowserVersionMajor() < 9) {
                shortcut.add("lt-ie9");

                if (clientProperties.getBrowserVersionMajor() < 8) {
                    shortcut.add("lt-ie8");

                    if (clientProperties.getBrowserVersionMajor() < 7) {
                        shortcut.add("lt-ie7");
                    }
                }
            }
        }

        return shortcut;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (useModrnizr) {
            response.render(JavaScriptHeaderItem.forReference(ModernizrJavaScriptReference.INSTANCE));
        }
    }
}
