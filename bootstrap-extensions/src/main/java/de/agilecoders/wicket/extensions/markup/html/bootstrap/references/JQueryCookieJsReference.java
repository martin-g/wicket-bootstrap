package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * * A JavaScript resource reference that contributes the jquery.cookie.js.
 */
public class JQueryCookieJsReference extends JQueryPluginResourceReference {

    public static final JQueryCookieJsReference INSTANCE = new JQueryCookieJsReference();

    public JQueryCookieJsReference() {
        super(JQueryCookieJsReference.class, "js/jquery.cookie.js");
    }
}
