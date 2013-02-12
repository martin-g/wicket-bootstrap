package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.Component;

import java.util.List;

/**
 * Helper class to transform components to navbar components
 *
 * @author miha
 */
public final class NavbarComponents {

    /**
     * private constructor to prevent instantiation
     */
    private NavbarComponents() {
        throw new UnsupportedOperationException();
    }

    /**
     * transforms a given component into a navbar component.
     *
     * @param position the position inside navbar
     * @param components the components to transform
     * @return a mutable list of navbar components
     */
    public static List<INavbarComponent> transform(final Navbar.ComponentPosition position, final Component... components) {
        return Generics2.transform(Generics2.newArrayList(components), new Generics2.Function<Component, INavbarComponent>() {
            @Override
            public INavbarComponent apply(Component component) {
                return new ImmutableNavbarComponent(component, position);
            }
        });
    }
}
