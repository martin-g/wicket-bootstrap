package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * Tests the {@code Navbar de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar}.
 *
 * @author miha
 * @version 1.0
 */
public class NavbarTest extends WicketApplicationTest {


    @Test
    public void isInstantiableWithoutError() {
        Navbar navbar = new Navbar("id");

        tester().startComponent(navbar);
        tester().assertNoErrorMessage();
    }

    @Test
    public void brandNameIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.brandName(Model.of("Brand Name"));

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("brandName");

        Assert.assertThat(tagTester.getValue(), is(equalTo("Brand Name")));
    }

    @Test
    public void fixedTopClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.TOP);

        tester().startComponentInPage(navbar);

        Assert.assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.TOP)));
        Assert.assertThat(tester().getTagByWicketId("id").getAttribute("class"), is(equalTo(("navbar navbar-fixed-top"))));
    }

    @Test
    public void fixedBottomClassIsRendered() {
        Navbar navbar = new Navbar("id");
        navbar.setPosition(Navbar.Position.BOTTOM);

        tester().startComponentInPage(navbar);

        Assert.assertThat(navbar.getPosition(), is(equalTo(Navbar.Position.BOTTOM)));
        Assert.assertThat(tester().getTagByWicketId("id").getAttribute("class"), is(equalTo(("navbar navbar-fixed-bottom"))));
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
        TagTester tagTester = tester().getTagById("nav-collapse");
        TagTester ulTag = tagTester.getChild("style", "display:none");

        Assert.assertThat(ulTag.getValue(), is(equalTo("")));
        Assert.assertThat(ulTag.getName(), is(equalTo("ul")));
    }

    @Test
    public void buttonIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addButton(Navbar.ButtonPosition.LEFT, new NavbarButton<Page>(Page.class, Model.of("Link Name")));

        tester().startComponentInPage(navbar);
        TagTester tagTester = tester().getTagByWicketId("navLeftList");

        Assert.assertThat(tagTester.hasChildTag("a"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId("button").hasAttribute("href"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId("button").getValue(), containsString("Link Name"));
    }

    @Test
    public void buttonWithIconIsAddedToLeftNavigation() {
        Navbar navbar = new Navbar("id");
        navbar.addButton(Navbar.ButtonPosition.LEFT, new NavbarButton<Page>(Page.class, Model.of("Link Name")).setIcon(new Icon("icon", IconType.AlignCenter)));

        tester().startComponentInPage(navbar);

        Assert.assertThat(tester().getTagByWicketId("button").hasChildTag("i"), is(equalTo(true)));
        Assert.assertThat(tester().getTagByWicketId("icon").getAttribute("class"), containsString("icon-align-center"));
        Assert.assertThat(tester().getTagByWicketId("icon").getAttribute("class"), containsString("icon-white"));
    }
}
