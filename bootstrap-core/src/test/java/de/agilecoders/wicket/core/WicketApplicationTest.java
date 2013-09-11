package de.agilecoders.wicket.core;

import com.google.common.base.Charsets;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.test.Attributes;
import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static de.agilecoders.wicket.jquery.util.Generics2.newArrayList;
import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Base integration test class
 *
 * @author miha
 */
public class WicketApplicationTest {

    private WebApplication application;
    private WicketTester tester;

    @Before
    public final void before() {
        application = newWebApplication();

        tester = new WicketTester(application);
        onBefore();
    }

    @After
    public final void tearDown() throws Exception {
        tester.destroy();
    }

    protected WebApplication newWebApplication() {
        return new WebApplication() {

            @Override
            protected void init() {
                super.init();

                Bootstrap.install(this, WicketApplicationTest.this.createBootstrapSettings());

                getMarkupSettings().setStripWicketTags(false);
                getMarkupSettings().setDefaultMarkupEncoding(Charsets.UTF_8.name());

                WicketApplicationTest.this.init(this);
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return WicketApplicationTest.this.getHomePage();
            }

        };
    }

    protected void init(WebApplication app) {}

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

    /**
     * @return default id that is used for main component
     */
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

        final CssClassNames.Builder cssClasses = CssClassNames.parse(tag.getAttribute("class"));

        for (String cssClassName : cssClassNames) {
            assertThat("contains css class name: " + cssClassName + "; current: " + cssClasses.asString(), cssClasses.contains(cssClassName), is(equalTo(true)));
        }
    }

    protected final void assertNotContainsCssClass(TagTester tag, String... cssClassNames) {
        Args.notNull(tag, "tag");
        Args.notNull(cssClassNames, "cssClassNames");

        final CssClassNames.Builder cssClasses = CssClassNames.parse(tag.getAttribute("class"));

        for (String cssClassName : cssClassNames) {
            assertThat("not contains css class name: " + cssClassName + "; current: " + cssClasses.asString(), cssClasses.contains(cssClassName), is(equalTo(false)));
        }
    }

    protected TagTester startComponentInPage(final Component component) {
        tester().startComponentInPage(component);

        return tester().getTagByWicketId(id());
    }

    protected TagTester startComponentInPage(final Component component, final String markup) {
        tester().startComponentInPage(component, Markup.of(markup));

        return tester().getTagByWicketId(id());
    }

    /**
     * creates a new component with given behavior and runs it on a test page.
     *
     * @param behavior the behavior to execute
     * @return tag that contains behavior
     */
    protected TagTester startBehaviorInPage(final Behavior behavior) {
        Component component = new WebMarkupContainer(id());
        component.add(behavior);

        tester().startComponentInPage(component);

        return tester().getTagByWicketId(id());
    }

    /**
     * creates a new component with given behavior and runs it on a test page.
     *
     * @param behavior the behavior to execute
     * @param markup   the markup to use for main component
     * @return tag that contains behavior
     */
    protected TagTester startBehaviorInPage(final Behavior behavior, final String markup) {
        Component component = new WebMarkupContainer(id());
        component.add(behavior);

        tester().startComponentInPage(component, Markup.of(markup));

        return tester().getTagByWicketId(id());
    }

    /**
     * asserts a special css class name on main component
     *
     * @param cssClassNames the css class names that must be present.
     */
    protected final void assertClassNamesPresent(final String... cssClassNames) {
        Attributes.assertClassNamesPresent(tester().getTagByWicketId(id()), cssClassNames);
    }

}
