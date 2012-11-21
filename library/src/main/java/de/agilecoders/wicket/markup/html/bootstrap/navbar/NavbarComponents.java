package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.wicket.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Helper class to transform components to navbar components
 *
 * @author miha
 * @version 1.0
 */
public final class NavbarComponents {

    private NavbarComponents() {
        throw new UnsupportedOperationException();
    }

    public static List<INavbarComponent> transform(final Navbar.ComponentPosition position, final Component... components) {
        return Lists.transform(newArrayList(components), new Function<Component, INavbarComponent>() {
            @Override
            public INavbarComponent apply(Component component) {
                return new ImmutableNavbarComponent(component, position);
            }
        });
    }
}
