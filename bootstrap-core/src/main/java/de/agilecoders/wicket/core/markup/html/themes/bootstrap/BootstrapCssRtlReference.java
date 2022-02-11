package de.agilecoders.wicket.core.markup.html.themes.bootstrap;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * #### Description
 *
 * The RTL (right-to-left) bootstrap css. To update the bootstrap version add the following dependency to
 * your maven `dependencyManagement` section:
 *
 * ```xml
 * <dependency>
 *     <groupId>org.webjars</groupId>
 *     <artifactId>bootstrap</artifactId>
 *     <version>your-bootstrap-version</version>
 * </dependency>
 * ```
 *
 * The css resource will be loaded by wicket-webjars.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class BootstrapCssRtlReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {
        private static final BootstrapCssRtlReference INSTANCE = new BootstrapCssRtlReference();
    }

    /**
     * Normally you should not use this method, but use
     * {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getCssResourceReference()} ()} to prevent version conflicts.
     *
     * @return the single instance of the resource reference
     */
    public static BootstrapCssRtlReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private BootstrapCssRtlReference() {
        super("/bootstrap/current/css/bootstrap.rtl.css");
    }

}