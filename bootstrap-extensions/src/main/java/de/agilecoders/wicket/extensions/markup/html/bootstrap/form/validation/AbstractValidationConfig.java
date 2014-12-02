package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Base configuration for validation behaviors.
 * Config contains two parameters:
 * <p>
 * appendToParent flag - target for validation message.
 * If appendToParent is set to true message will be append to input parent element in DOM.
 * Otherwise message will be append to input element. By default appendToParent is false
 * </p>
  * <p>
 * errorClass - is css validation class which appends to parent element in DOM. By default uses bootstrap "has-error" class.
 * </p>
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
    public AbstractValidationConfig appendToParent(boolean appendToParent) {
        put(AppendToParent, appendToParent);
        return this;
    }

    /**
     * @return append to parent flag
     */
    public boolean isAppendToParent() {
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
