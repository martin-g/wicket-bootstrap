package de.agilecoders.wicket;

import com.asual.lesscss.LessEngine;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.references.Less;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import eu.infomas.annotation.AnnotationDetector;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

public final class Bootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * The {@link MetaDataKey} used to retrieve the {@link IBootstrapSettings} from the Wicket {@link Appendable}.
     */
    private static final MetaDataKey<IBootstrapSettings> BOOTSTRAP_SETTINGS_METADATA_KEY = new MetaDataKey<IBootstrapSettings>() {
    };

    /**
     * Use http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * to load LessEngine if it is really needed
     */
    private static class LessEngineHolder {
        private final static LessEngine instance = new LessEngine();
    }

    private static LessEngine getLessEngine() {
        return LessEngineHolder.instance;
    }

    private Bootstrap() {
    }

    public static void install(Application app, IBootstrapSettings settings) {
        app.setMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY, settings);
    }

    public static IBootstrapSettings getSettings(Application app) {
        return app.getMetaData(BOOTSTRAP_SETTINGS_METADATA_KEY);
    }

    public static void renderHead(Component component, IHeaderResponse response) {
        new BootstrapResourcesBehavior().renderHead(getSettings(component.getApplication()), response);
    }

    public static void renderHead(IHeaderResponse response) {
        new BootstrapResourcesBehavior().renderHead(getSettings(Application.get()), response);
    }

    private static void internalCompileLess(final String... pkgs) {
        final AnnotationDetector.TypeReporter reporter = new AnnotationDetector.TypeReporter() {

            @SuppressWarnings("unchecked")
            @Override
            public Class<? extends Annotation>[] annotations() {
                return new Class[] { Less.class };
            }

            @Override
            public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
                LOG.info("found: {}", className);
            }
        };

        try {
            final AnnotationDetector cf = new AnnotationDetector(reporter);
            cf.detect(pkgs);
        } catch (IOException e) {
            LOG.error("can't scan packages: {}", pkgs);
        }
    }

    public static void compileLess(final String... pkgs) {
        List<String> packages = Lists.newArrayList(pkgs);
        packages.add("de.agilecoders.wicket.markup.html.references");

        internalCompileLess((String[]) packages.toArray());
    }
}
