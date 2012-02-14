package de.agilecoders.wicket.settings;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface IBootstrapSettings {

    String getJavaScriptUri();

    String getCssUri();

    String getCssResponsiveUri();

    boolean isMinified();

    void minify(final boolean minify);

}
