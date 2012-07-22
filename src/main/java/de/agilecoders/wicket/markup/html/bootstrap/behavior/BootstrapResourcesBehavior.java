package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResourcesBehavior extends BootstrapJavascriptBehavior {

    @Override
    public void renderHead(IBootstrapSettings settings, IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        // just includes all
    }

}
