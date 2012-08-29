package de.agilecoders.wicket;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

import java.util.List;

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.Lists.newArrayList;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class WicketApplicationTest {

    private WebApplication application;
    private WicketTester tester;

    @Before
    public final void before() {
        application = new WebApplication() {

            @Override
            protected void init() {
                super.init();

                Bootstrap.install(this, WicketApplicationTest.this.createBootstrapSettings());
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return WicketApplicationTest.this.getHomePage();
            }

        };

        tester = new WicketTester(application);
        onBefore();
    }

    protected void onBefore() {
    }

    protected Class<? extends Page> getHomePage() {
        return Page.class;
    }

    protected IBootstrapSettings createBootstrapSettings() {
        return new BootstrapSettings();
    }

    protected final WicketTester tester() {
        return tester;
    }

    protected final WebApplication application() {
        return application;
    }

    protected final IBootstrapSettings getBootstrapSettings() {
        return Bootstrap.getSettings(application);
    }

    protected List<String> extractClassNames(TagTester tagTester) {
        return tagTester != null ? newArrayList(Splitter.on(' ').trimResults().split(nullToEmpty(tagTester.getAttribute("class"))))
                                 : Lists.<String>newArrayList();
    }

}
