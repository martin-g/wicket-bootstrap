package de.agilecoders.wicket.settings;

import de.agilecoders.wicket.markup.html.references.BootstrapCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import de.agilecoders.wicket.markup.html.references.BootstrapResponsiveCssReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapSettings implements IBootstrapSettings {

    private boolean minify = false;

    /**
     * Constructor.
     */
    public BootstrapSettings() {
        // noop till now
    }

    @Override
    public ResourceReference getCssResourceReference() {
        return BootstrapCssReference.INSTANCE;
    }

    @Override
    public ResourceReference getResponsiveCssResourceReference() {
        return BootstrapResponsiveCssReference.INSTANCE;
    }

    @Override
    public ResourceReference getJsResourceReference() {
        return BootstrapJavaScriptReference.INSTANCE;
    }

    @Override
    public String getJqueryUrl() {
        return "//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js";
    }

    @Override
    public boolean isMinified() {
        return minify;
    }

    @Override
    public void minify(boolean minify) {
        this.minify = minify;
    }
}
