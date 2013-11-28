package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.Invertible;

/**
 * Base interface of all bootstrap styled buttons.
 *
 * @author miha
 */
public interface IBootstrapButton<T> {

    /**
     * sets the size of a button
     *
     * @see {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Size}
     * @param size The button size to use
     * @return instance for chaining
     */
    T setSize(Buttons.Size size);

    /**
     * sets the type of a button
     *
     * @see {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type}
     * @param type The button type to use
     * @return instance for chaining
     */
    T setType(Buttons.Type type);

}
