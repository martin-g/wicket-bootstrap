package de.agilecoders.wicket.less;

import de.agilecoders.wicket.webjars.WicketWebjars;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertTrue;

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

    @Test
    public void testName() throws Exception {
        LessCacheManager less = LessCacheManager.get();
        URL res = Thread.currentThread().getContextClassLoader().getResource("import.less");

        assertTrue((".rule {\n"
                   + "  background: #428bca;\n"
                   + "}\n").equals(less.getCss(less.getLessSource(res))));

    }
}
