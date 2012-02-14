package de.agilecoders.wicket.ui.bootstrap.button;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public enum ButtonSize {
    Small("btn-small"),
    Medium(""),
    Large("btn-large");

    private final String cssClassName;

    private ButtonSize(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    String cssClassName() {
        return cssClassName;
    }

}
