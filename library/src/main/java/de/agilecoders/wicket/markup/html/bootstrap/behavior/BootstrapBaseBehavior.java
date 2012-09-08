package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import de.agilecoders.wicket.settings.ITheme;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * The {@code BootstrapBaseBehavior} renders the current active
 * {@link ITheme} which includes the {@link de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference}
 * and the {@link de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference}
 * if enabled to the response.
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapBaseBehavior extends Behavior {

    /**
     * returns the {@link IBootstrapSettings} implementation like {@link de.agilecoders.wicket.settings.BootstrapSettings}
     * which was bound to the current application.
     *
     * @param component the bound component
     * @return the bound {@link IBootstrapSettings}
     */
    protected final IBootstrapSettings getBootstrapSettings(Component component) {
        IBootstrapSettings settings = Bootstrap.getSettings(component.getApplication());
        if (settings == null) {
            throw new WicketRuntimeException("No BootstrapSettings associated with this Application. Did you call Bootstrap.install()?");
        }

        return settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        IBootstrapSettings settings = getBootstrapSettings(component);

        renderHead(settings, headerResponse);
    }

    /**
     * Render the current active {@link ITheme} which includes all {@link org.apache.wicket.request.resource.ResourceReference} objects
     * and the {@link de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapResponsiveCssReference}
     * if enabled to the response. Also it allows all subclasses to get the {@link IBootstrapSettings} by overriding this method.
     *
     * @param settings       the bound {@link IBootstrapSettings}
     * @param headerResponse the current {@link IHeaderResponse}
     */
    public void renderHead(IBootstrapSettings settings, IHeaderResponse headerResponse) {
        ITheme theme = settings.getActiveThemeProvider().getActiveTheme();
        theme.renderHead(headerResponse);

        if (settings.useResponsiveCss()) {
            headerResponse.render(CssHeaderItem.forReference(settings.getResponsiveCssResourceReference()));
        }
    }
}
