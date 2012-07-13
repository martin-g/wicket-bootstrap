package de.agilecoders.wicket.markup.html.references;

import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ExternalUrlResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class JqueryReference extends ExternalUrlResourceReference {

    public final static JqueryReference INSTANCE = new JqueryReference();

    /**
     * Constructor.
     */
    private JqueryReference() {
        super(Url.parse((Bootstrap.getSettings(Application.get()).getJqueryUrl())));
    }

}
