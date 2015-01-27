package de.agilecoders.wicket.less;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.request.resource.IResourceReferenceFactory;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;

import com.github.sommeri.less4j.LessCompiler;

/**
 * Bootstrap less compiler settings accessor class
 */
public final class BootstrapLess {

    /**
     * Construct.
     */
    private BootstrapLess() {
        throw new UnsupportedOperationException();
    }

    /**
     * Installs given settings for given application
     *
     * @param app            The current application
     * @param configFactory  The {@link LessCompilerConfigurationFactory} to create new {@link LessCompiler.Configuration}.
     */
    public static void install(final Application app, final LessCompilerConfigurationFactory configFactory) {

        LessCacheManager cacheManager = new LessCacheManager(configFactory);
        cacheManager.install(app);

        IPackageResourceGuard resourceGuard = app.getResourceSettings().getPackageResourceGuard();
        if (resourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) resourceGuard;
            securePackageResourceGuard.addPattern("+*.less");
        }

        ResourceReferenceRegistry resourceReferenceRegistry = app.getResourceReferenceRegistry();
        IResourceReferenceFactory delegate = resourceReferenceRegistry.getResourceReferenceFactory();
        resourceReferenceRegistry.setResourceReferenceFactory(new LessResourceReferenceFactory(delegate));
    }
    
    /**
     * Installs given settings for given application
     *
     * @param app      The current application
     */
    public static void install(final Application app) {
        install(app, null);
    }

}
