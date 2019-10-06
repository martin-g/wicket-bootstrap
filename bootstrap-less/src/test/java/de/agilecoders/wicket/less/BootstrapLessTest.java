package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessFunction;
import com.github.sommeri.less4j.LessProblems;
import com.github.sommeri.less4j.LessSource;
import com.github.sommeri.less4j.LessCompiler.Configuration;
import com.github.sommeri.less4j.core.ast.Expression;
import com.github.sommeri.less4j.core.ast.FunctionExpression;
import com.github.sommeri.less4j.core.ast.IdentifierExpression;

import de.agilecoders.wicket.webjars.WicketWebjars;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockServletContext;
import org.apache.wicket.request.resource.IResourceReferenceFactory;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * TODO miha: document class purpose
 *
 * @author miha
 */
@SuppressWarnings("SpellCheckingInspection")
class BootstrapLessTest {

    private WicketTester tester;

    @BeforeEach
    void before() {
        tester = new WicketTester(new TestApplication() {
            @Override
            public void init() {
                super.init();

                WicketWebjars.install(this);
                BootstrapLess.install(this);
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
        URI uri = getClass().getResource("/de/agilecoders/wicket/less/test/webContextImported.less").toURI();
        File file = new File(uri);
        File contextRoot = file.getParentFolder();
        // setup folder /.../bootstrap-less/src/test/resources/de/agilecoders/wicket/less/test as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importWebContext.less");

        LessSource.URLSource lessSource = less.getLessSource(res, null);
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo(".rule {\n  background: #999;\n}\n")));
    }

    /**
     * Tests with {@link ContextRelativeLessResourceReference}
     *
     * https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/524
     */
    @Test
    void importServletContextRelative() throws Exception {
        WebApplication application = tester.getApplication();
        URI uri = getClass().getResource("/servlet/context/root/").toURI();
        File contextRoot = new File(uri);
        // setup folder /.../bootstrap-less/src/test/resources/servlet/context/root as a root for the ServletContext
        application.setServletContext(new MockServletContext(application, contextRoot.getAbsolutePath()));

//        tester.startPage(HomePage.class);
        tester.executeUrl("./wicket/resource/org.apache.wicket.Application/relative.less?--" + ContextRelativeLessResourceReference.CONTEXT_RELATIVE_LESS_REFERENCE_VARIATION);
        tester.assertContains("less-servlet-relative-cls");
        tester.assertContains("color: #333;");
    }

    @Test
    void importWebJars() {
        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("import.less");

        LessSource.URLSource lessSource = less.getLessSource(res, null);
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo(".rule {\n  background: #337ab7;\n}\n")));
    }

    @Test
    void importClasspath() {
        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importClasspath.less");

        LessSource.URLSource lessSource = less.getLessSource(res, null);
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo(".classPathImported {\n  color: #333;\n}\n")));
    }

    @Test
    void importPackage() {
        LessCacheManager less = LessCacheManager.get();
        URL res = BootstrapLessTest.class.getResource("package-dependency-1.less");

        LessSource.URLSource lessSource = less.getLessSource(res, BootstrapLessTest.class.getName());
        String css = less.getCss(lessSource);
        assertThat(css, containsString("package1"));
        assertThat(css, containsString("package2"));
        assertThat(css, containsString("package3"));
    }

    @Test
    void lessResourceReferenceFactoryIsInstalled() {
        ResourceReferenceRegistry registry = tester.getApplication().getResourceReferenceRegistry();
        IResourceReferenceFactory referenceFactory = registry.getResourceReferenceFactory();
        assertThat(referenceFactory, is(instanceOf(LessResourceReferenceFactory.class)));
    }

    @Test
    void usesCustomLessCompilerConfigurationFactoryWhenProvided() {
        // tests the invocation of a custom less function that will be registered within the configuration factory
        BootstrapLess.install(Application.get(), () -> {
            Configuration config = new Configuration();
            config.addCustomFunction(new LessFunction() {
                @Override
                public Expression evaluate(FunctionExpression input, List<Expression> parameters, Expression evaluatedParameter,
                        LessProblems problems) {
                    return new IdentifierExpression(input.getUnderlyingStructure(), "blue");
                }

                @Override
                public boolean canEvaluate(FunctionExpression input, List<Expression> parameters) {
                    return "myFancyFunction".equals(input.getName());
                }
            });
            return config;
        });

        LessCacheManager less = LessCacheManager.get();
        URL res = BootstrapLessTest.class.getResource("resources/custom-functions.less");

        LessSource.URLSource lessSource = new LessSource.URLSource(res);
        String css = less.getCss(lessSource);
        assertThat(css, is(".my-class {\n  color: blue;\n}\n"));
    }
}
