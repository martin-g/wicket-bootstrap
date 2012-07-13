package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapBaseBehavior extends Behavior {

    /**
     * returns the bootstrap settings
     *
     * @param component
     * @return
     */
    protected final IBootstrapSettings getBootstrapSettings(Component component) {
        IBootstrapSettings settings = Bootstrap.getSettings(component.getApplication());
        if (settings == null) {
            throw new WicketRuntimeException("No BootstrapSettings associated with this Application. Did you call Bootstrap.install()?");
        }

        return settings;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        IBootstrapSettings settings = getBootstrapSettings(component);

        headerResponse.render(CssHeaderItem.forReference(settings.getCssResourceReference()));
        if (settings.useResponsiveCss()) {
            headerResponse.render(CssHeaderItem.forReference(settings.getResponsiveCssResourceReference()));
        }
    }
}
