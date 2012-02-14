package de.agilecoders.wicket.settings;

import org.apache.wicket.request.resource.ResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface IBootstrapSettings {

    ResourceReference getCssResourceReference();

    ResourceReference getResponsiveCssResourceReference();

    ResourceReference getJsResourceReference();

    String getJqueryUrl();

    boolean isMinified();

    void minify(final boolean minify);

}
