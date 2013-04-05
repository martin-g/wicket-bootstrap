package de.agilecoders.wicket.core;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.test.Attributes;
import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.core.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static de.agilecoders.wicket.core.util.Generics2.newArrayList;
import static de.agilecoders.wicket.core.util.Strings2.nullToEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Base integration test class
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

                getMarkupSettings().setStripWicketTags(false);
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
        return tagTester != null ? newArrayList(Generics2.split(nullToEmpty(tagTester.getAttribute("class")), " "))
                                 : new ArrayList<String>();
    }

    protected final String id() {
        return "id";
    }

    protected final void assertCssClass(final Behavior behavior, final String... cssClassNames) {
        TagTester tag = startBehaviorInPage(behavior);

        assertCssClass(tag, cssClassNames);
    }

    protected final void assertCssClass(final Component component, final String... cssClassNames) {
        TagTester tag = startComponentInPage(component);

        assertCssClass(tag, cssClassNames);
    }

    protected final void assertCssClass(TagTester tag, String... cssClassNames) {
        Args.notNull(tag, "tag");
        Args.notNull(cssClassNames, "cssClassNames");

        for (String cssClassName : cssClassNames) {
            assertThat(CssClassNames.parse(tag.getAttribute("class")).contains(cssClassName), is(equalTo(true)));
        }
    }

    protected TagTester startComponentInPage(final Component component) {
        tester().startComponentInPage(component);

        return tester().getTagByWicketId(id());
    }

    protected TagTester startBehaviorInPage(final Behavior behavior) {
        Component component = new WebMarkupContainer(id());
        component.add(behavior);

        tester().startComponentInPage(component);

        return tester().getTagByWicketId(id());
    }

    protected final void assertClassNamesPresent(final String... cssClassNames) {
        Attributes.assertClassNamesPresent(tester().getTagByWicketId(id()), cssClassNames);
    }

}
