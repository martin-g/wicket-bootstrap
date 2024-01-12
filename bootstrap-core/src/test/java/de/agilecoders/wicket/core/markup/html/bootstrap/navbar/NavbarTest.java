package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.visit.IVisitor;
import org.junit.jupiter.api.Test;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Tests the {@code Navbar de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar}.
 *
 * @author miha
 */
@SuppressWarnings("SpellCheckingInspection")
class NavbarTest extends WicketApplicationTest {

    @Test
    void isInstantiableWithoutError() {
        Navbar navbar = new Navbar("id");

        tester().startComponentInPage(navbar);
        tester().assertNoErrorMessage();
    }

    @Test
    void brandNameIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setBrandName(Model.of("Brand Name"));

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("brandLabel");

        assertThat(tagTester.getValue(), is(equalTo("Brand Name")));
    }

    @Test
    void fixedTopClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.TOP);

        tester().startComponentInPage(navbar);

        assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.TOP)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        assertThat(classes.contains("navbar"), is(equalTo(true)));
        assertThat(classes.contains("fixed-top"), is(equalTo(true)));
    }

    @Test
    void fixedBottomClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.BOTTOM);

        tester().startComponentInPage(navbar);

        assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.BOTTOM)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        assertThat(classes.contains("navbar"), is(equalTo(true)));
        assertThat(classes.contains("fixed-bottom"), is(equalTo(true)));
    }

    @Test
    void stickyTopClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.STICKY_TOP);

        tester().startComponentInPage(navbar);

        assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.STICKY_TOP)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        assertTrue(classes.contains("navbar"));
        assertTrue(classes.contains("sticky-top"));
    }

    @Test
    void initialLeftNavigationIsEmpty() {
        Navbar navbar = new Navbar("id");

        tester().startComponentInPage(navbar);

        TagTester tagTester = tester().getTagByWicketId("collapse");
        TagTester ulTag = tagTester.getChild("hidden", "");

        assertThat(ulTag.getValue(), is(equalTo("")));
        assertThat(ulTag.getName(), is(equalTo("ul")));
    }

    @Test
    void buttonIsAddedToRightNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addComponents(new INavbarComponent() {
            @Override
            public Component create(String markupId) {
                return new NavbarButton<>(Page.class, Model.of("Right Link Name"));
            }

            @Override
            public Navbar.ComponentPosition getPosition() {
                return Navbar.ComponentPosition.RIGHT;
            }
        });

        tester().startComponentInPage(navbar);

        TagTester tagTester = tester().getTagByWicketId("navRightList");

        assertThat(tagTester.hasChildTag("a"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId(Navbar.componentId()).hasAttribute("href"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId(Navbar.componentId()).getValue(), containsString("Right Link Name"));
    }

    @Test
    void buttonIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addComponents(new INavbarComponent() {
            @Override
            public Component create(String markupId) {
                return new NavbarButton<Page>(Page.class, Model.of("Link Name"));
            }

            @Override
            public Navbar.ComponentPosition getPosition() {
                return Navbar.ComponentPosition.LEFT;
            }
        });

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("navLeftList");

        assertThat(tagTester.hasChildTag("a"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId(Navbar.componentId()).hasAttribute("href"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId(Navbar.componentId()).getValue(), containsString("Link Name"));
    }

    @Test
    void buttonWithIconIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addComponents(new INavbarComponent() {
            @Override
            public Component create(String markupId) {
                return new NavbarButton<Page>(Page.class, Model.of("Link Name"))
                    .setIconType(new IconType("test-icon") {
                        @Override
                        public String cssClassName() {
                            return "test-icon";
                        }
                    });
            }

            @Override
            public Navbar.ComponentPosition getPosition() {
                return Navbar.ComponentPosition.LEFT;
            }
        });

        tester().startComponentInPage(navbar);

        assertThat(tester().getTagByWicketId(Navbar.componentId()).hasChildTag("i"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId("icon").getAttribute("class"), containsString("test-icon"));
    }

    @Test
    void allComponents_areStateless() {
        final List<String> statefulComponents = new ArrayList<>();

        Navbar navbar = new Navbar("id");
        navbar.visitChildren((IVisitor<Component, Void>) (component, arg1) -> {
            if (!component.isStateless()) {
                statefulComponents.add(component.getId());
            }
        });

        assertThat(statefulComponents.size(), is(equalTo(0)));
    }

    @Test
    void navbarBackgroundIsRendered() {
        Navbar navbar = new Navbar("id")
            .setBackgroundColor(BackgroundColor.Success);

        tester().startComponentInPage(navbar);

        TagTester tag = tester().getTagByWicketId("id");
        List<String> classes = extractClassNames(tag);

        assertTrue(classes.contains("bg-success"));
    }

    @Test
    void navbarCollapseBreakpointIsRendered() {
        Navbar navbar = new Navbar("id")
            .setCollapseBreakdown(Navbar.CollapseBreakpoint.Small);

        tester().startComponentInPage(navbar);

        TagTester tag = tester().getTagByWicketId("id");
        List<String> classes = extractClassNames(tag);

        assertTrue(classes.contains("navbar"));
        assertTrue(classes.contains("navbar-expand-sm"));
    }
}
