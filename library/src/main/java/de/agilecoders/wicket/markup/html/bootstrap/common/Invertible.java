package de.agilecoders.wicket.markup.html.bootstrap.common;

/**
 * Marker interface for all components which supports inverting their icon.
 *
 * @author miha
 * @version 1.0
 */
public interface Invertible<T> {

    /**
     * inverts the icon color
     *
     * @param inverted true, if inverted version should be used
     * @return this instance for chaining
     */
    T setInverted(final boolean inverted);

}
