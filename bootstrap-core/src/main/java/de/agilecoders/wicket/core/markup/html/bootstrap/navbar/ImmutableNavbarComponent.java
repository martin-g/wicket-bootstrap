package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import org.apache.wicket.Component;
import org.apache.wicket.util.lang.Args;

/**
 * An immutable navbar component
 *
 * @author miha
 */
public class ImmutableNavbarComponent extends AbstractNavbarComponent {

    private final Component component;

    /**
     * Construct.
     *
     * @param component the component to use inside navbar
     */
    public ImmutableNavbarComponent(Component component) {
        this(component, Navbar.ComponentPosition.LEFT);
    }

    /**
     * Construct.
     *
     * @param component the component to use inside navbar
     * @param position  the position inside navbar
     */
    public ImmutableNavbarComponent(Component component, Navbar.ComponentPosition position) {
        super(position);

        Args.isTrue(Navbar.componentId().equals(component.getId()), "componentId must equal " + Navbar.componentId());

        this.component = component;
    }

    @Override
    public final Component create(final String markupId) {
        return component;
    }
}
