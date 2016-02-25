package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the {@code Navbar de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar}.
 *
 * @author miha
 */
@Category(IntegrationTest.class)
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

        Assert.assertThat(tagTester.getValue(), is(equalTo("Brand Name")));
    }

    @Test
    public void fixedTopClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.TOP);

        tester().startComponentInPage(navbar);

        Assert.assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.TOP)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        Assert.assertThat(classes.contains("navbar"), is(equalTo(true)));
        Assert.assertThat(classes.contains("navbar-fixed-top"), is(equalTo(true)));
    }

    @Test
    public void fixedBottomClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.BOTTOM);

        tester().startComponentInPage(navbar);

        Assert.assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.BOTTOM)));

        List<String> classes = extractClassNames(tester().getTagByWicketId("id"));

        Assert.assertThat(classes.contains("navbar"), is(equalTo(true)));
        Assert.assertThat(classes.contains("navbar-fixed-bottom"), is(equalTo(true)));
    }

    @Test
    public void fluidClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.fluid();

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("container");

        Assert.assertThat(navbar.isFluid(), is(equalTo(true)));
        Assert.assertThat(tagTester.getAttribute("class"), is(equalTo(("container-fluid"))));
    }

    @Test
    public void initialLeftNavigationIsEmpty() {
        Navbar navbar = new Navbar("id");

        tester().startComponentInPage(navbar);
        System.err.println(tester().getLastResponseAsString());

        TagTester tagTester = tester().getTagByWicketId("collapse");
        TagTester ulTag = tagTester.getChild("style", "display:none");

        Assert.assertThat(ulTag.getValue(), is(equalTo("")));
        Assert.assertThat(ulTag.getName(), is(equalTo("ul")));
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

        Assert.assertThat(tagTester.hasChildTag("a"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId(Navbar.componentId()).hasAttribute("href"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId(Navbar.componentId()).getValue(), containsString("Link Name"));
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

        Assert.assertThat(tester().getTagByWicketId(Navbar.componentId()).hasChildTag("i"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId("icon").getAttribute("class"), containsString("icon-align-center"));
    }
}
