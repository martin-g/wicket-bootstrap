package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * reference for font awesome 5.x css via CDN
 */
public class FontAwesome5CDNCSSReference extends UrlResourceReference{
    private static final long serialVersionUID = 1L;
    private static final String CDNURL = "https://use.fontawesome.com/releases/v5.8.1/css/all.css";

    /**
     * Singleton instance of this reference
     */
    private static final FontAwesome5CDNCSSReference INSTANCE = new FontAwesome5CDNCSSReference();

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesome5CDNCSSReference instance() {
        return INSTANCE;
    }

    private FontAwesome5CDNCSSReference() {
        super(Url.parse(CDNURL));
    }

}
