package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * <strong>Important</strong>: To use font-awesome 7.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 *
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;7.0.1&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 * reference for font awesome 7.x css via CDN
 */
public class FontAwesome7CdnCssReference extends UrlResourceReference{
    private static final long serialVersionUID = 1L;
    private static final String CDNURL = "https://use.fontawesome.com/releases/v7.0.1/css/all.css";

    /**
     * Singleton instance of this reference
     */
    private static final FontAwesome7CdnCssReference INSTANCE = new FontAwesome7CdnCssReference();

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesome7CdnCssReference instance() {
        return INSTANCE;
    }

    private FontAwesome7CdnCssReference() {
        super(Url.parse(CDNURL));
    }

}
