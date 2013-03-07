package de.agilecoders.wicket.markup.html.bootstrap.navbar;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class AbstractNavbarComponent implements INavbarComponent {

    private final Navbar.ComponentPosition position;

    public AbstractNavbarComponent() {
        this(Navbar.ComponentPosition.LEFT);
    }

    public AbstractNavbarComponent(Navbar.ComponentPosition position) {
        this.position = position;
    }

    @Override
    public final Navbar.ComponentPosition getPosition() {
        return position;
    }
}
