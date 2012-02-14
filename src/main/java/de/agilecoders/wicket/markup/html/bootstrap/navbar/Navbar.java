package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: document
 * <p/>
 * <pre><div wicket:id="navbar" class="navbar"></div></pre>
 *
 * @author miha
 * @version 1.0
 */
public class Navbar extends Panel {

    /**
     * indicates the position of a button inside the navigation bar.
     */
    public enum Position {
        LEFT, RIGHT
    }

    private final WebMarkupContainer container;
    private final BookmarkablePageLink<Page> brandNameLink;
    private final Component navLeftList;

    private boolean fluid = false;
    private boolean fixedTop = false;

    private final List<NavbarButton> buttonLeftList = new ArrayList<>();

    /**
     * TODO document
     *
     * @param componentId The non-null id of this component
     */
    public Navbar(final String componentId) {
        this(componentId, Model.of());
    }

    /**
     * TODO document
     *
     * @param componentId The non-null id of this component
     * @param model       The component's model
     */
    public Navbar(final String componentId, final IModel<?> model) {
        super(componentId, model);

        container = newContainer("container");
        brandNameLink = newBrandNameLink("brandName");
        navLeftList = newNavigation("navLeftList", buttonLeftList);

        add(container, brandNameLink, navLeftList);
    }

    /**
     * TODO document
     *
     * @param componentId The non-null id of a new navigation component
     * @param listModel   The component's model
     * @return a new navigation list view instance
     */
    private Component newNavigation(final String componentId, final List<NavbarButton> listModel) {
        return new ListView<NavbarButton>(componentId, listModel) {
            @Override
            protected void populateItem(ListItem<NavbarButton> components) {
                NavbarButton button = components.getModelObject();
                components.add(button);

                if (getPage().getClass().equals(button.getPageClass())) {
                    components.add(new CssClassNameAppender("active"));
                }
            }
        };
    }

    /**
     * creates a new brand name button.
     *
     * @param componentId The non-null id of a new navigation component
     * @return a new brand name page link instance
     */
    private BookmarkablePageLink<Page> newBrandNameLink(String componentId) {
        return new BookmarkablePageLink<Page>(componentId, getHomePage());
    }

    /**
     * @return the page class which is used for the brand name link. The
     *         application's homepage is used by default.
     */
    protected Class<? extends Page> getHomePage() {
        return getApplication().getHomePage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        container.add(new CssClassNameAppender(isFluid() ? "container-fluid" : "container"));

        if (isFixedTop()) {
            add(new CssClassNameAppender("navbar-fixed-top"));
        }

        brandNameLink.setVisible(brandNameLink.getBody() != null);
        navLeftList.setVisible(buttonLeftList.size() > 0);
    }

    /**
     * @return true, if the navigation is fixed on the top of the screen.
     */
    public boolean isFixedTop() {
        return fixedTop;
    }

    /**
     * @return true, if the navigation is rendered for a fluid page layout.
     */
    public boolean isFluid() {
        return fluid;
    }

    public final Navbar addButton(Position position, NavbarButton... buttons) {
        if (Position.LEFT.equals(position)) {
            buttonLeftList.addAll(Arrays.asList(buttons));
        } // TODO: add a navigation on the right side

        return this;
    }

    /**
     * creates a new transparent inner container which is used to append some
     * css classes.
     *
     * @param componentId The non-null id of a new navigation component
     * @return a new inner container of the navigation bar.
     */
    private TransparentWebMarkupContainer newContainer(String componentId) {
        return new TransparentWebMarkupContainer(componentId);
    }

    /**
     * sets the label of the brand name button
     *
     * @param brandName the brand name label
     * @return the component's current instance
     */
    public Navbar brandName(IModel<String> brandName) {
        this.brandNameLink.setBody(brandName);

        return this;
    }

    /**
     * marks the navigation to be rendered inside a fluid page layout.
     *
     * @return the component's current instance.
     */
    public Navbar fluid() {
        this.fluid = true;

        return this;
    }

    /**
     * fixates the navigation on the top of the screen.
     *
     * @return the component's current instance.
     */
    public Navbar fixedTop() {
        this.fixedTop = true;

        return this;
    }
}
