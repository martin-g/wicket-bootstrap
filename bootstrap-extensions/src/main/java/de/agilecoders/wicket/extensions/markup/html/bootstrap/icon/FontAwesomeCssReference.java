package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * <strong>Important</strong>: To use font-awesome 4.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 *
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;4.7.0&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 * reference for font awesome css
 */
public class FontAwesomeCssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final FontAwesomeCssReference INSTANCE = new FontAwesomeCssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesomeCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private FontAwesomeCssReference() {
        super("font-awesome/current/css/font-awesome.css");
    }
}
