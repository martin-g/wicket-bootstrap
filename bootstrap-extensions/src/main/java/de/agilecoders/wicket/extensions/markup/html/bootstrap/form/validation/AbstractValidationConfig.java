package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * @author Alexey Volkov
 * @since 08.11.14
 */
public abstract class AbstractValidationConfig extends AbstractConfig {

    private static final long serialVersionUID = -4162710449437437516L;

    private static final IKey<Boolean> AppendToParent = newKey("appendToParent", false);

    /**
     * sets flag of append to parent to false
     */
    protected AbstractValidationConfig() {
        appendToParent(false);
    }

    /**
     * @param appendToParent appendToParent
     * @return this instance
     */
    public AbstractValidationConfig appendToParent(Boolean appendToParent) {
        put(AppendToParent, appendToParent);
        return this;
    }

    /**
     * @return flag
     */
    public Boolean getAppendToParent() {
        return get(AppendToParent);
    }

}
