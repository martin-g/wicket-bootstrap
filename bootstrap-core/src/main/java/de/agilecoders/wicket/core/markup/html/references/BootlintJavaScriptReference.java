package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A resource reference that could be used to check whether the markup of the page
 * uses Bootstrap the best way
 *
 * @see <a href="https://github.com/twbs/bootlint">bootlint</a>
 */
public class BootlintJavaScriptReference extends JavaScriptResourceReference {

    public static final BootlintJavaScriptReference INSTANCE = new BootlintJavaScriptReference();

    private BootlintJavaScriptReference() {
        super(BootlintJavaScriptReference.class, "js/bootlint.js");
    }
}
