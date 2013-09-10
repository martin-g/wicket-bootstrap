package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import com.google.common.base.Function;
import de.agilecoders.wicket.jquery.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.util.lang.Args;

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
        Args.notNull(components, "components");
        Args.notNull(position, "position");
        
        return Generics2.transform(components, new Function<Component, INavbarComponent>() {
            @Override
            public INavbarComponent apply(Component component) {
                return new ImmutableNavbarComponent(component, position);
            }
        });
    }
}
