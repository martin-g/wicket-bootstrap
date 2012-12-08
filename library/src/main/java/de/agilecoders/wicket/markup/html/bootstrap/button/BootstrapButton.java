package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface BootstrapButton<T> extends Invertible<T> {

    T setSize(ButtonSize buttonSize);

    T setType(ButtonType buttonType);

}
