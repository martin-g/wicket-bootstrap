package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * reference for font awesome css via CDN
 */
public class FontAwesomeCDNCSSReference {
    private static final long serialVersionUID = 1L;
    private static String CDNURL = "//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css";

    /**
     * Singleton instance of this reference
     */
    private static final FontAwesomeCDNCSSReference INSTANCE = new FontAwesomeCDNCSSReference();

    public FontAwesomeCDNCSSReference() {
    }

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesomeCDNCSSReference instance() {
        return INSTANCE;
    }

    public ResourceReference getCDNURL() {
        return new UrlResourceReference(Url.parse(CDNURL));
    }
    
}
