package de.agilecoders.wicket;

import de.agilecoders.wicket.less.IBootstrapLessCompilerSettings;
import de.agilecoders.wicket.less.LessResourceStreamLocator;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.core.util.resource.locator.caching.CachingResourceStreamLocator;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;

/**
 * Bootstrap less compiler settings accessor class
 */
public final class BootstrapLess {

    /**
     * The {@link org.apache.wicket.MetaDataKey} used to retrieve the {@link IBootstrapLessCompilerSettings} from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<IBootstrapLessCompilerSettings> BOOTSTRAP_LESS_SETTINGS_METADATA_KEY = new MetaDataKey<IBootstrapLessCompilerSettings>() {
    };

    /**
     * Construct.
     */
    private BootstrapLess() {
        throw new UnsupportedOperationException();
    }

    /**
     * Installs given settings for given application
     *
     * @param app      The current application
     * @param settings The settings to use
     */
    public static void install(final Application app, final IBootstrapLessCompilerSettings settings) {
        app.setMetaData(BOOTSTRAP_LESS_SETTINGS_METADATA_KEY, settings);

        if (settings.useLessCompiler()) {
            app.getResourceSettings().setResourceStreamLocator(new CachingResourceStreamLocator(new LessResourceStreamLocator()));
        }

        IPackageResourceGuard resourceGuard = app.getResourceSettings().getPackageResourceGuard();
        if (resourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) resourceGuard;
            securePackageResourceGuard.addPattern("+*.less");
        }
    }

    /**
     * returns the {@link IBootstrapLessCompilerSettings} which are assigned to given application
     *
     * @param app The current application
     * @return assigned {@link IBootstrapLessCompilerSettings}
     */
    public static IBootstrapLessCompilerSettings getSettings(final Application app) {
        return app.getMetaData(BOOTSTRAP_LESS_SETTINGS_METADATA_KEY);
    }

    /**
     * returns the {@link IBootstrapLessCompilerSettings} which are assigned to current application
     *
     * @return assigned {@link IBootstrapLessCompilerSettings}
     */
    public static IBootstrapLessCompilerSettings getSettings() {
        if (Application.exists()) {
            return getSettings(Application.get());
        }

        throw new IllegalStateException("there is no active application assigned to this thread.");
    }
}
