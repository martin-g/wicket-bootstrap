package de.agilecoders.wicket.markup.html.bootstrap.html;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

import java.util.Locale;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class HtmlTag extends TransparentWebMarkupContainer {
    private Locale locale;
    private final ClientProperties clientProperties;

    /**
     * Construct.
     *
     * @param id
     */
    public HtmlTag(String id) {
        super(id);

        this.clientProperties = new WebClientInfo(getRequestCycle()).getProperties();
        locale = Locale.ENGLISH;
    }

    public Locale locale() {
        return locale;
    }

    public HtmlTag locale(Locale locale) {
        this.locale = locale;
        return this;
    }

    private Model<String> toAttributeValue(Locale locale) {
        return Model.of(locale.toString().replace("_", "-").toLowerCase());
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "html");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (Bootstrap.getSettings(getApplication()).useModernizr()) {
            add(new CssClassNameAppender("no-js"));
        }

        if (locale != null) {
            add(new AttributeModifier("lang", toAttributeValue(locale())));
        }

        add(new CssClassNameAppender(createBrowserShortcut(clientProperties)));
    }

    private IModel<String> createBrowserShortcut(ClientProperties clientProperties) {
        String shortcut = "";

        if (clientProperties.isBrowserInternetExplorer()) {
            if (clientProperties.getBrowserVersionMajor() < 9) {
                shortcut += " lt-ie9";

                if (clientProperties.getBrowserVersionMajor() < 8) {
                    shortcut += " lt-ie8";

                    if (clientProperties.getBrowserVersionMajor() < 7) {
                        shortcut += " lt-ie7";
                    }
                }
            }
        }

        return Model.of(shortcut);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (Bootstrap.getSettings(getApplication()).useModernizr()) {
            response.render(JavaScriptHeaderItem.forReference(ModernizrJavaScriptReference.INSTANCE));
        }
    }
}
