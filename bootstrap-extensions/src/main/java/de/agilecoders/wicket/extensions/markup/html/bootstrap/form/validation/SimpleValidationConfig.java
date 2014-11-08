package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.IKey;

/**
 * simple validation config
 *
 * @author Alexey Volkov
 * @since 08.11.14
 */
public class SimpleValidationConfig extends AbstractValidationConfig {

    private static final long serialVersionUID = -3220639449778548946L;
    private static final IKey<String> BorderColor = newKey("borderColor", null);

    /**
     * sets flag of display parent to false
     */
    protected SimpleValidationConfig() {
        appendToParent(false);
    }

    /**
     * @param borderColor border color
     * @return this instance
     */
    public SimpleValidationConfig withBorderColor(String borderColor) {
        put(BorderColor, borderColor);
        return this;
    }

    /**
     * @return border color
     */
    public String getBorderColor() {
        return get(BorderColor);
    }

}
