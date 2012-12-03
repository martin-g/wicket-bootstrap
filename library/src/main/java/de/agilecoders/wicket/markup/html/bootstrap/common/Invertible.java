package de.agilecoders.wicket.markup.html.bootstrap.common;

/**
 * Marker interface for all components which supports inverting their icon.
 *
 * @author miha
 * @version 1.0
 */
public interface Invertible<T> {

    T setInverted(final boolean inverted);

}
