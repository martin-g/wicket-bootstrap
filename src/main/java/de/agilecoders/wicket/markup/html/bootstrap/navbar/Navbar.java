package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.markup.html.bootstrap.button.Activateable;
import de.agilecoders.wicket.markup.html.bootstrap.button.Bookmarkable;
import org.apache.wicket.AttributeModifier;
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
     * indicates the position of the navigation bar itself
     */
    public static enum Position {
        /** fixate at the top of the screen */
        TOP,

        /** fixate at the bottom of the screen */
        BOTTOM,

        /** do not fixate the position */
        NONE
    }
    /**
     * indicates the position of a button inside the navigation bar.
     */
    public static enum ButtonPosition {
        LEFT, RIGHT
    }

    private final WebMarkupContainer container;

    private final BookmarkablePageLink<Page> brandNameLink;
    private final Component navRightList;
    private final Component navLeftList;

    private Position position = Position.NONE;
    private boolean fluid = false;

    private final List<Component> buttonLeftList = Lists.newArrayList();
    private final List<Component> buttonRightList = Lists.newArrayList();

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
        navRightList = newNavigation("navRightList", buttonRightList);

        add(container, brandNameLink, navLeftList, navRightList);
    }

    /**
     * TODO document
     *
     * @param componentId The non-null id of a new navigation component
     * @param listModel   The component's model
     * @return a new navigation list view instance
     */
    private Component newNavigation(final String componentId, final List<Component> listModel) {
        return new ListView<Component>(componentId, listModel) {
            @Override
            protected void populateItem(ListItem<Component> components) {
                Component button = components.getModelObject();
                components.add(button);

                Page page = getPage();
                if (button instanceof Activateable) {
                    Activateable activateable = (Activateable) button;
                    if (activateable.isActive(button)) {
                        components.add(new CssClassNameAppender("active"));
                    }
                }
                // TODO consider removing Bookmarkable. Often the PageParameters are needed as well to make this next check.
                // Activateable can handle all of this
                else if (button instanceof Bookmarkable && page.getClass().equals(((Bookmarkable)button).getPageClass())) {
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

        add(new CssClassNameAppender("navbar"));
        container.add(new CssClassNameAppender(isFluid() ? "container-fluid" : "container"));

        if (Position.TOP == getPosition()) {
            add(new CssClassNameAppender("navbar-fixed-top"));
        } else if (Position.BOTTOM == getPosition()) {
            add(new CssClassNameAppender("navbar-fixed-bottom"));
        } else {
            add(new CssClassNameModifier("navbar-fixed-top", AttributeModifier.VALUELESS_ATTRIBUTE_REMOVE));
            add(new CssClassNameModifier("navbar-fixed-bottom", AttributeModifier.VALUELESS_ATTRIBUTE_REMOVE));
        }

        brandNameLink.setVisible(brandNameLink.getBody() != null);
        navLeftList.setVisible(buttonLeftList.size() > 0);
        navRightList.setVisible(buttonRightList.size() > 0);
    }

    /**
     * @return true, if the navigation is fixed on the top of the screen.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return true, if the navigation is rendered for a fluid page layout.
     */
    public boolean isFluid() {
        return fluid;
    }

    /**
     * adds a button to the given position inside the navbar
     *
     * @param position The position of the button (left, right)
     * @param buttons  the buttons to add
     * @return this component
     */
    public final Navbar addButton(ButtonPosition position, Component... buttons) {
        if (ButtonPosition.LEFT.equals(position)) {
            buttonLeftList.addAll(Lists.newArrayList(buttons));
        } else if (ButtonPosition.RIGHT.equals(position)) {
            buttonRightList.addAll(Lists.newArrayList(buttons));
        }

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
     * Sets the prefered position of the navigation bar on the screen.
     *
     * @return the component's current instance.
     */
    public Navbar setPosition(Position position) {
        this.position = position;

        return this;
    }

}
