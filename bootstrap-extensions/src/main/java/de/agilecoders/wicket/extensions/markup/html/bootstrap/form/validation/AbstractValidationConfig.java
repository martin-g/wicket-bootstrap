package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Base configuration for validation behaviors.
 * Config contains one flag - target for validation message.
 * If appendToParent set to true message will be append to input parent element in DOM.
 * Otherwise message will be append to input element. Bt default appendToParent is false
 * errorClass - is validation class which appends to parent element in DOM. By default uses bootstrap "has-error" class.
 *
 * @author Alexey Volkov
 * @since 08.11.14
 */
public abstract class AbstractValidationConfig extends AbstractConfig {

    private static final long serialVersionUID = -4162710449437437516L;

    private static final IKey<Boolean> AppendToParent = newKey("appendToParent", false);
    private static final IKey<String> ErrorClass = newKey("errorClass", "has-error");

    /**
     * sets flag of append to parent to false
     */
    protected AbstractValidationConfig() {
    }

    /**
     * @param appendToParent appendToParent
     * @return current instance
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

    /**
     * @param errorClass error class
     * @return current instance
     */
    public AbstractValidationConfig withErrorClass(String errorClass) {
        put(ErrorClass, errorClass);
        return this;
    }

    /**
     * @return error class
     */
    public String getErrorClass() {
        return get(ErrorClass);
    }

}
