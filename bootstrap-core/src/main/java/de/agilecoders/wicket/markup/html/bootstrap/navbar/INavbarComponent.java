package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import org.apache.wicket.Component;
import org.apache.wicket.util.io.IClusterable;

/**
 * Interface used to represent a single component inside {@link Navbar}.
 *
 * @author miha
 */
public interface INavbarComponent extends IClusterable {

    /**
     * creates a new {@link Navbar} component
     *
     * @param markupId the markup id of new component
     * @return a new component instance
     */
    Component create(final String markupId);

    /**
     * @return the position of this component
     */
    Navbar.ComponentPosition getPosition();

}
