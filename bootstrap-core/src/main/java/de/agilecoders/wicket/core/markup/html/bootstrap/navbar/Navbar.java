package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Behaviors;
import de.agilecoders.wicket.core.util.Generics2;
import de.agilecoders.wicket.core.util.Models;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.List;

import static de.agilecoders.wicket.core.util.Generics2.filter;
import static de.agilecoders.wicket.core.util.Generics2.transform;

/**
 * A {@link Navbar} is a navigation component that holds a list of elements,
 * mostly Links/MenuButtons/Dropdowns.
 * <p/>
 * <pre><div wicket:id="navbar" class="navbar"></div></pre>
 *
 * @author miha
 */
public class Navbar extends Panel implements Invertible<Navbar> {

    private static final String COMPONENT_ID = "component";

    /**
     * @return the component id that is used for all navbar components.
     */
    public static String componentId() {
        return COMPONENT_ID;
    }

    /**
     * indicates the position of the navigation bar itself
     */
    public static enum Position implements ICssClassNameProvider {
        /**
         * fixate at the top of the screen
         */
        TOP("navbar-fixed-top"),

        /**
         * Create a full-width navbar that scrolls away with the page
         */
        STATIC_TOP("navbar-static-top"),

        /**
         * fixate at the bottom of the screen
         */
        BOTTOM("navbar-fixed-bottom"),

        /**
         * do not fixate the position
         */
        DEFAULT("");

        private final String className;

        /**
         * Construct.
         *
         * @param className css class name of this position enum
         */
        private Position(final String className) {
            this.className = className;
        }

        @Override
        public String cssClassName() {
            return className;
        }

    }

    /**
     * indicates the position of a button inside the navigation bar.
     */
    public static enum ComponentPosition {
        LEFT, RIGHT
    }

    private static final NavbarComponentToComponentFunction NAVBAR_COMPONENT_TO_COMPONENT_FUNCTION = new NavbarComponentToComponentFunction(componentId());
    private static final PositionFilter POSITION_FILTER_LEFT = new PositionFilter(ComponentPosition.LEFT);
    private static final PositionFilter POSITION_FILTER_RIGHT = new PositionFilter(ComponentPosition.RIGHT);

    private IModel<String> invertModel;
    private CssClassNameAppender activeStateAppender;

    private final Label brandLabel;
    private final Image brandImage;

    private final IModel<Position> position = Model.of(Position.DEFAULT);
    private final IModel<Boolean> fluid = Model.of(false);

    private final List<INavbarComponent> components = new ArrayList<INavbarComponent>();

    /**
     * Construct.
     *
     * @param componentId The non-null id of this component
     */
    public Navbar(final String componentId) {
        this(componentId, Model.of());
    }

    /**
     * Construct.
     *
     * @param componentId The non-null id of this component
     * @param model       The component's model
     */
    public Navbar(final String componentId, final IModel<?> model) {
        super(componentId, model);

        BootstrapResourcesBehavior.addTo(this);

        final WebMarkupContainer container = newContainer("container");

        BookmarkablePageLink<Page> brandNameLink = newBrandNameLink("brandName");
        brandLabel = newBrandLabel("brandLabel");
        brandNameLink.add(brandLabel);
        brandImage = newBrandImage("brandImage");
        brandNameLink.add(brandImage);

        Component leftAlignedComponentListView = newNavigation("navLeftList", newPositionDependedComponentModel(components, POSITION_FILTER_LEFT));
        Component rightAlignedComponentListView = newNavigation("navRightList", newPositionDependedComponentModel(components, POSITION_FILTER_RIGHT));

        activeStateAppender = new CssClassNameAppender("active");
        invertModel = Model.of("");

        add(container, brandNameLink, leftAlignedComponentListView, rightAlignedComponentListView);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, "navbar", invertModel.getObject(), position.getObject().cssClassName());
    }

    /**
     * creates a component model which holds only the components with a specific position.
     *
     * @param components   to filter
     * @param withPosition position a component must have
     * @return new model
     */
    private IModel<List<Component>> newPositionDependedComponentModel(final List<INavbarComponent> components, final PositionFilter withPosition) {
        return new LoadableDetachableModel<List<Component>>() {
            @Override
            public List<Component> load() {
                return transform(filter(components, withPosition), NAVBAR_COMPONENT_TO_COMPONENT_FUNCTION);
            }
        };
    }

    /**
     * creates a new navigation list
     *
     * @param componentId The non-null id of a new navigation component
     * @param listModel   The component's model
     * @return a new navigation list view instance
     */
    protected Component newNavigation(String componentId, IModel<List<Component>> listModel) {
        return new ListView<Component>(componentId, listModel) {
            @Override
            protected void populateItem(ListItem<Component> components) {
                Component component = components.getModelObject();
                components.add(component);

                Behaviors.remove(components, activeStateAppender);

                if (component instanceof Activatable) {
                    Activatable activatable = (Activatable) component;

                    if (activatable.isActive(component)) {
                        components.add(activeStateAppender);
                    }
                }

                if (component instanceof Invertible) {
                    ((Invertible) component).setInverted(!Models.isNullOrEmpty(invertModel));
                }
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();

                setVisibilityAllowed(getList().size() > 0);
            }
        };
    }

    /**
     * creates a new brand name button.
     *
     * @param componentId The non-null id of a new navigation component
     * @return a new brand name page link instance
     */
    protected BookmarkablePageLink<Page> newBrandNameLink(String componentId) {
        return new BookmarkablePageLink<Page>(componentId, getHomePage()) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return brandLabel.isVisible() || brandImage.isVisible();
            }
        };
    }

    /**
     * @param markupId The component's markup id
     * @return a new brand name label.
     */
    protected Label newBrandLabel(String markupId) {
        return new Label(markupId) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return getDefaultModel() != null;
            }
        };
    }

    /**
     * @param markupId The component's markup id
     * @return a new {@link Image}
     */
    protected Image newBrandImage(String markupId) {
        return new Image(markupId, Model.of("")) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return getImageResourceReference() != null;
            }
        };
    }

    /**
     * @return the page class which is used for the brand name link. The
     *         application's homepage is used by default.
     */
    protected Class<? extends Page> getHomePage() {
        return getApplication().getHomePage();
    }

    /**
     * @return true, if the navigation is fixed on the top of the screen.
     */
    public Position getPosition() {
        return position.getObject();
    }

    /**
     * @return true, if the navigation is rendered for a fluid page layout.
     */
    public boolean isFluid() {
        return fluid.getObject();
    }

    /**
     * adds a component to the given position inside the navbar
     *
     * @param components the components to add
     * @return this component instance for chaining
     */
    public final Navbar addComponents(final INavbarComponent... components) {
        return addComponents(Generics2.newArrayList(components));
    }

    /**
     * adds a component to the given position inside the navbar
     *
     * @param components the components to add
     * @return this component instance for chaining
     */
    public final Navbar addComponents(final List<INavbarComponent> components) {
        this.components.addAll(components);

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
    public Navbar brandName(final IModel<String> brandName) {
        brandLabel.setDefaultModel(brandName);

        return this;
    }

    /**
     * sets an image in the brand button
     *
     * @param imageResourceReference required
     * @param imageAltAttrModel      optional, but should be provided
     * @return
     */
    public Navbar setBrandImage(final ResourceReference imageResourceReference, final IModel<String> imageAltAttrModel) {
        brandImage.setImageResourceReference(imageResourceReference);

        if (!Models.isNullOrEmpty(imageAltAttrModel)) {
            brandImage.add(new AttributeModifier("alt", imageAltAttrModel));
        }

        return this;
    }

    /**
     * inverts the navbar backgorund color
     *
     * @param invert whether to invert the color or not
     * @return the component's current instance
     */
    public Navbar setInverted(final boolean invert) {
        this.invertModel.setObject(invert ? "navbar-inverse" : "");

        return this;
    }

    /**
     * marks the navigation to be rendered inside a fluid page layout.
     *
     * @return the component's current instance.
     */
    public Navbar fluid() {
        this.fluid.setObject(true);

        return this;
    }

    /**
     * Sets the preferred position of the navigation bar on the screen.
     *
     * @return the component's current instance.
     */
    public Navbar setPosition(final Position position) {
        this.position.setObject(position);

        return this;
    }


    /**
     * A {@link Predicate} that filters out all {@link INavbarComponent}s that don't
     * match the given {@link ComponentPosition}.
     */
    private static final class PositionFilter implements Predicate<INavbarComponent>, IClusterable {

        private final ComponentPosition position;

        /**
         * Construct.
         *
         * @param position which filtered component must match
         */
        private PositionFilter(final ComponentPosition position) {
            Args.notNull(position, "position");

            this.position = position;
        }

        @Override
        public boolean apply(final INavbarComponent navbarComponent) {
            return navbarComponent != null && position.equals(navbarComponent.getPosition());
        }
    }

    /**
     * A {@link Function} that maps a {@link INavbarComponent} to a {@link Component}
     */
    private static final class NavbarComponentToComponentFunction implements Function<INavbarComponent, Component>, IClusterable {

        private final String markupId;

        /**
         * Construct.
         *
         * @param markupId The markup id to use for each new component
         */
        private NavbarComponentToComponentFunction(final String markupId) {
            Args.isFalse(Strings.isEmpty(markupId), "markupId");

            this.markupId = markupId;
        }

        @Override
        public Component apply(final INavbarComponent navbarComponent) {
            return navbarComponent.create(markupId);
        }
    }
}
