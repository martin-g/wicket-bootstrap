package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * <strong>Important</strong>: To use font-awesome 6.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 *
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;6.6.0&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 * reference for font awesome 6.x css via CDN
 */
public class FontAwesome6CdnCssReference extends UrlResourceReference{
    private static final long serialVersionUID = 1L;
    private static final String CDNURL = "https://use.fontawesome.com/releases/v6.6.0/css/all.css";

    /**
     * Singleton instance of this reference
     */
    private static final FontAwesome6CdnCssReference INSTANCE = new FontAwesome6CdnCssReference();

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesome6CdnCssReference instance() {
        return INSTANCE;
    }

    private FontAwesome6CdnCssReference() {
        super(Url.parse(CDNURL));
    }

}
