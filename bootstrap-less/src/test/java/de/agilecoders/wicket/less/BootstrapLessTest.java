package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessSource;
import de.agilecoders.wicket.webjars.WicketWebjars;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * TODO miha: document class purpose
 *
 * @author miha
 */
public class BootstrapLessTest {

    private WicketTester tester;

    @Before
    public void before() {
        tester = new WicketTester(new TestApplication() {
            @Override
            public void init() {
                super.init();

                WicketWebjars.install(this);
                BootstrapLess.install(this);
            }
        });
    }

    @After
    public void after() {
        tester.destroy();
    }

    @Test
    // unignore after https://github.com/l0rdn1kk0n/wicket-webjars/issues/17
    public void importWebJars() throws Exception {
        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("import.less");

        LessSource.URLSource lessSource = less.getLessSource(res, null);
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo(".rule {\n  background: #428bca;\n}\n")));

    }

    @Test
    public void importClasspath() throws Exception {
        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("importClasspath.less");

        LessSource.URLSource lessSource = less.getLessSource(res, null);
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo(".classPathImported {\n  color: #333;\n}\n")));

    }

    @Test
    public void importPackage() throws Exception {
        LessCacheManager less = LessCacheManager.get();
        URL res = BootstrapLessTest.class.getResource("1.less");

        LessSource.URLSource lessSource = less.getLessSource(res, BootstrapLessTest.class.getName());
        String css = less.getCss(lessSource);
        assertThat(css, is(equalTo("body {\n  background-color: #808080;\n}\n")));

    }
}
