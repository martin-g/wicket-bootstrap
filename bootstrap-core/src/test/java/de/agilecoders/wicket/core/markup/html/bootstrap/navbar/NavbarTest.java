package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@code Navbar de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar}.
 *
 * @author miha
 */
public class NavbarTest extends WicketApplicationTest {

    @Test
    public void isInstantiableWithoutError() {
        Navbar navbar = new Navbar("id");

        tester().startComponentInPage(navbar);
        tester().assertNoErrorMessage();
    }

    @Test
    public void brandNameIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setBrandName(Model.of("Brand Name"));

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("brandLabel");

        assertThat(tagTester.getValue(), is(equalTo("Brand Name")));
    }

    @Test
    public void fixedTopClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.TOP);

        tester().startComponentInPage(navbar);

        assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.TOP)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        assertThat(classes.contains("navbar"), is(equalTo(true)));
        assertThat(classes.contains("navbar-fixed-top"), is(equalTo(true)));
    }

    @Test
    public void fixedBottomClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.BOTTOM);

        tester().startComponentInPage(navbar);

        assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.BOTTOM)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        assertThat(classes.contains("navbar"), is(equalTo(true)));
        assertThat(classes.contains("navbar-fixed-bottom"), is(equalTo(true)));
    }

    @Test
    public void fluidClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.fluid();

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("container");

        assertThat(navbar.isFluid(), is(equalTo(true)));
        assertThat(tagTester.getAttribute("class"), is(equalTo(("container-fluid"))));
    }

    @Test
    public void initialLeftNavigationIsEmpty() {
        Navbar navbar = new Navbar("id");

        tester().startComponentInPage(navbar);
        System.err.println(tester().getLastResponseAsString());

        TagTester tagTester = tester().getTagByWicketId("collapse");
        TagTester ulTag = tagTester.getChild("style", "display:none");

        assertThat(ulTag.getValue(), is(equalTo("")));
        assertThat(ulTag.getName(), is(equalTo("ul")));
    }

    @Test
    public void buttonIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addComponents(new INavbarComponent() {
            @Override
            public Component create(String markupId) {
                return new NavbarButton(Page.class, Model.of("Link Name"));
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
    public void buttonWithIconIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addComponents(new INavbarComponent() {
            @Override
            public Component create(String markupId) {
                return new NavbarButton(Page.class, Model.of("Link Name")).setIconType(GlyphIconType.aligncenter);
            }

            @Override
            public Navbar.ComponentPosition getPosition() {
                return Navbar.ComponentPosition.LEFT;
            }
        });

        tester().startComponentInPage(navbar);

        assertThat(tester().getTagByWicketId(Navbar.componentId()).hasChildTag("i"), is(equalTo(true)));
        assertThat(tester().getTagByWicketId("icon").getAttribute("class"), containsString("icon-align-center"));
    }

    @Test
    public void allComponents_areStateless() {
        final List<String> statefulComponents = new ArrayList<>();

        Navbar navbar = new Navbar("id");
        navbar.visitChildren(new IVisitor<Component, Void>() {
            @Override
            public void component(Component component, IVisit<Void> arg1) {
                if (!component.isStateless())
                    statefulComponents.add(component.getId());
            }
        });

        assertThat(statefulComponents.size(), is(equalTo(0)));
    }
}
