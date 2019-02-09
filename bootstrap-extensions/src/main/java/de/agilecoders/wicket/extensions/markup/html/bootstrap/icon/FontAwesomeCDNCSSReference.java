package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * reference for font awesome css via CDN
 */
public class FontAwesomeCDNCSSReference extends UrlResourceReference{
    private static final long serialVersionUID = 1L;
    private static final String CDNURL = "https://use.fontawesome.com/releases/v5.7.1/css/all.css";

    /**
     * Singleton instance of this reference
     */
    private static final FontAwesomeCDNCSSReference INSTANCE = new FontAwesomeCDNCSSReference();

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesomeCDNCSSReference instance() {
        return INSTANCE;
    }

    private FontAwesomeCDNCSSReference() {
        super(Url.parse(CDNURL));
    }

}
