package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;

/**
 * Base interface of all bootstrap styled buttons.
 *
 * @author miha
 */
public interface BootstrapButton<T> extends Invertible<T> {

    /**
     * sets the size of a button
     *
     * @see {@link ButtonSize}
     * @param buttonSize The button size to use
     * @return instance for chaining
     */
    T setSize(ButtonSize buttonSize);

    /**
     * sets the type of a button
     *
     * @see {@link ButtonType}
     * @param buttonType The button type to use
     * @return instance for chaining
     */
    T setType(ButtonType buttonType);

}
