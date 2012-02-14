package de.agilecoders.wicket.settings;

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
    public String getJavaScriptUri() {
        if (isMinified()) {
            return "/js/bootstrap.min.js";
        } else {
            return "/js/bootstrap.js";
        }
    }

    @Override
    public String getCssUri() {
        if (isMinified()) {
            return "/css/bootstrap/bootstrap.min.css";
        } else {
            return "/css/bootstrap/bootstrap.css";
        }
    }

    @Override
    public String getCssResponsiveUri() {
        if (isMinified()) {
            return "/css/bootstrap/bootstrap-responsive.min.css";
        } else {
            return "/css/bootstrap/bootstrap-responsive.css";
        }
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
