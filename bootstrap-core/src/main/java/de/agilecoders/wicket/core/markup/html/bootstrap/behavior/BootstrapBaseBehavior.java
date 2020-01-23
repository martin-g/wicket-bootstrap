package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;

/**
 * #### Description
 *
 * The {@code BootstrapBaseBehavior} renders the current active
 * {@link ITheme} which includes the {@link de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference}
 * if enabled to the response.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class BootstrapBaseBehavior extends Behavior {
    private static final long serialVersionUID = 1L;
    private static final BootstrapBaseBehavior INSTANCE = new BootstrapBaseBehavior();

    /**
     * @return bootstrap base behavior instance
     */
    public static BootstrapBaseBehavior instance() {
        return INSTANCE;
    }

    /**
     * removes the {@link BootstrapBaseBehavior} from given {@link Component}
     *
     * @param component The component to remove the behavior from.
     */
    public static void removeFrom(final Component component) {
        Args.notNull(component, "component");

        component.remove(INSTANCE);
    }

    /**
     * adds the {@link BootstrapBaseBehavior} from given {@link Component}
     *
     * @param component The component to add the behavior to.
     */
    public static void addTo(final Component component) {
        Args.notNull(component, "component");

        component.add(INSTANCE);
    }

    /**
     * returns the {@link IBootstrapSettings} implementation like {@link de.agilecoders.wicket.core.settings.BootstrapSettings}
     * which was bound to the current application.
     *
     * @param component the bound component
     * @return the bound {@link IBootstrapSettings}
     */
    protected final IBootstrapSettings getBootstrapSettings(final Component component) {
        Args.notNull(component, "component");

        final IBootstrapSettings settings = Bootstrap.getSettings(component.getApplication());
        if (settings == null) {
            throw new WicketRuntimeException("No BootstrapSettings associated with this Application. Did you call Bootstrap.install()?");
        }

        return settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(final Component component, final IHeaderResponse headerResponse) {
        IBootstrapSettings settings = getBootstrapSettings(component);

        renderHead(settings, headerResponse);
    }

    /**
     * Render the current active {@link ITheme} which includes all {@link org.apache.wicket.request.resource.ResourceReference} objects
     * if enabled to the response. Also it allows all subclasses to get the {@link IBootstrapSettings} by overriding this method.
     *
     * @param settings       the bound {@link IBootstrapSettings}
     * @param headerResponse the current {@link IHeaderResponse}
     */
    public void renderHead(final IBootstrapSettings settings, final IHeaderResponse headerResponse) {
        final ITheme theme = settings.getActiveThemeProvider().getActiveTheme();

        theme.renderHead(headerResponse);
    }
}
