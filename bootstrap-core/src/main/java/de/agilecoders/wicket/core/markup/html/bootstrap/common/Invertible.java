package de.agilecoders.wicket.core.markup.html.bootstrap.common;

/**
 * Marker interface for all components which supports inverting their icon.
 *
 * @author miha
 */
public interface Invertible<T> {

    /**
     * inverts the ui state of a component.
     *
     * @param inverted true, if inverted version should be used
     * @return this instance for chaining
     */
    T setInverted(final boolean inverted);

}
