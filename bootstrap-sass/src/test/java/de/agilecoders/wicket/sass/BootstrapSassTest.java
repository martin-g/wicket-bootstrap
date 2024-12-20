package de.agilecoders.wicket.sass;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.net.URL;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockServletContext;
import org.apache.wicket.request.resource.IResourceReferenceFactory;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.webjars.WicketWebjars;
import io.bit3.jsass.Options;
import io.bit3.jsass.type.SassString;
import io.bit3.jsass.type.SassValue;

@SuppressWarnings("SpellCheckingInspection")
class BootstrapSassTest {

    private WicketTester tester;

    @BeforeEach
    void before() {
        tester = new WicketTester(new TestApplication() {
            @Override
            public void init() {
                super.init();

                WicketWebjars.install(this);
                BootstrapSass.install(this);
            }
        });
    }

    @AfterEach
    void after() {
        tester.destroy();
    }

    @Test
    void importWebContext() throws Exception {
        WebApplication application = tester.getApplication();
        URI uri = getClass().getResource("/de/agilecoders/wicket/sass/test/webContextImported.scss").toURI();
        File file = new File(uri);
        File contextRoot = file.getParentFolder();
        // setup folder /.../bootstrap-sass/src/test/resources/de/agilecoders/wicket/sass/test as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importWebContext.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".rule {" + lineSeparator() + "  background: #999; }" + lineSeparator())));
    }

    /**
     * Tests with {@link ContextRelativeSassResourceReference}
     * <p>
     * https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/524
     */
    @Test
    @Disabled("Disabled due to the test was most likely broken by fix for WICKET-7024")
    void importServletContextRelative() throws Exception {
        WebApplication application = tester.getApplication();
        URI uri = getClass().getResource("/servlet/context/root/").toURI();
        File contextRoot = new File(uri);
        // setup folder /.../bootstrap-sass/src/test/resources/servlet/context/root as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

        tester.executeUrl("./wicket/resource/org.apache.wicket.Application/relative.scss?--" + ContextRelativeSassResourceReference.CONTEXT_RELATIVE_SASS_REFERENCE_VARIATION);
        tester.assertContains("sass-servlet-relative-cls");
        tester.assertContains("color: #333;");
    }

    @Test
    void importWebJars() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("import.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".rule {" + lineSeparator() + "  background: #0d6efd; }" + lineSeparator())));
    }

    @Test
    void importClasspath() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importClasspath.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(equalTo(".classPathImported {" + lineSeparator() + "  color: #333; }" + lineSeparator())));
    }

    @Test
    void importPackage() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("package-dependency-1.scss");

        SassSource sassSource = sass.getSassContext(res, BootstrapSassTest.class.getName());
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("package1"));
        assertThat(css, containsString("package2"));
        assertThat(css, containsString("package3"));
    }

    @Test
    void importPartials() {
        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("partial-root.scss");

        SassSource sassSource = sass.getSassContext(res, BootstrapSassTest.class.getName());
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("root-cls"));
        assertThat(css, containsString("partial"));
    }

    @Test
    void importLocalJarPath() {
        // test case for issue #794
        SassCacheManager sass = SassCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importRelativeLocalPath.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, containsString("* Bootstrap"));
    }

    @Test
    void sassResourceReferenceFactoryIsInstalled() {
        ResourceReferenceRegistry registry = tester.getApplication().getResourceReferenceRegistry();
        IResourceReferenceFactory referenceFactory = registry.getResourceReferenceFactory();
        assertThat(referenceFactory, is(instanceOf(SassResourceReferenceFactory.class)));
    }

    @Test
    void usesCustomSassCompilerConfigurationFactoryWhenProvided() {
        // tests the invocation of a custom sass function that will be registered within the configuration factory
        BootstrapSass.install(Application.get(), () -> {
            Options config = new Options();
            config.getFunctionProviders().add(new TestFunctions());
            return config;
        });

        SassCacheManager sass = SassCacheManager.get();
        URL res = BootstrapSassTest.class.getResource("resources/custom-functions.scss");

        SassSource sassSource = sass.getSassContext(res, null);
        String css = sass.getCss(sassSource);
        assertThat(css, is(".my-class {" + lineSeparator() + "  color: blue; }" + lineSeparator()));
    }

    public static class TestFunctions {

        public SassValue myFancyFunction() {
            return new SassString("blue", false);
        }

    }
}
