package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import com.google.common.base.Preconditions;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ImmutableNavbarComponent extends AbstractNavbarComponent {

    private final Component component;

    public ImmutableNavbarComponent(Component component) {
        this(component, Navbar.ComponentPosition.LEFT);
    }

    public ImmutableNavbarComponent(Component component, Navbar.ComponentPosition position) {
        super(position);

        Preconditions.checkArgument(Navbar.COMPONENT_ID.equals(component.getId()));

        this.component = component;
    }

    @Override
    public final Component create(String markupId) {
        return component;
    }
}
