package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import static de.agilecoders.wicket.jquery.util.Generics2.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.EnclosureContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Behaviors;
import de.agilecoders.wicket.core.util.Models;
import de.agilecoders.wicket.jquery.util.Generics2;

/**
 * A {@link Navbar} is a navigation component that holds a list of elements,
 * mostly Links/MenuButtons/Dropdowns.
 *
 * <pre>{@code<div wicket:id="navbar" class="navbar"></div>}</pre>
 *
 * @author miha
 */
public class Navbar extends Panel implements Invertible<Navbar> {
    private static final long serialVersionUID = 1L;
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
    public enum Position implements ICssClassNameProvider {
        /**
         * fixate at the top of the screen
         */
        TOP("fixed-top"),

        /**
         * Create a full-width navbar that scrolls away with the page
         */
        STICKY_TOP("sticky-top"),

        /**
         * fixate at the bottom of the screen
         */
        BOTTOM("fixed-bottom"),

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
        Position(final String className) {
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
    public enum ComponentPosition {
        LEFT, RIGHT
    }

    /**
     * indicates the breakpoint for collapsing navigation bar.
     */
    public enum CollapseBreakpoint {
        Small("sm"),
        Medium("md"),
        Large("lg"),
        ExtraLarge("xl");

        private final String breakpoint;

        private CollapseBreakpoint(String breakpoint) {
            this.breakpoint = breakpoint;
        }

        public String cssClassName() {
            return String.format("navbar-expand-%s", this.breakpoint);
        }
    }

    private static final NavbarComponentToComponentFunction NAVBAR_COMPONENT_TO_COMPONENT_FUNCTION = new NavbarComponentToComponentFunction(componentId());
    private static final PositionFilter POSITION_FILTER_LEFT = new PositionFilter(ComponentPosition.LEFT);
    private static final PositionFilter POSITION_FILTER_RIGHT = new PositionFilter(ComponentPosition.RIGHT);

    private final IModel<String> invertModel = Model.of("navbar-light");
    private final IModel<BackgroundColorBehavior.Color> backgroundColor = Model.of(BackgroundColorBehavior.Color.Light);
    private final CssClassNameAppender activeStateAppender = new CssClassNameAppender("active");

    private final IModel<CollapseBreakpoint> collapseBreakpoint = Model.of(CollapseBreakpoint.Large);
    private final IModel<Position> position = Model.of(Position.DEFAULT);
    private Component brandNameLink;
    private final IModel<Boolean> fluid = Model.of(true);
    private final List<INavbarComponent> components = new ArrayList<>();
    private final RepeatingView extraItems = new RepeatingView("extraItems");

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
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        BootstrapResourcesBehavior.addTo(this);

        final TransparentWebMarkupContainer container = newContainer("container");
        final TransparentWebMarkupContainer collapse = newCollapseContainer("collapse");
        final TransparentWebMarkupContainer collapseButton = newCollapseButton("collapseButton", "#" + collapse.getMarkupId());

        final Component leftAlignedComponentListView = newNavigation("navLeftList", newPositionDependedComponentModel(components, POSITION_FILTER_LEFT));
        final Component rightAlignedComponentListView = newNavigation("navRightList", newPositionDependedComponentModel(components, POSITION_FILTER_RIGHT));
        collapse.add(extraItems);

        EnclosureContainer navLeftListEnclosure = new EnclosureContainer("navLeftListEnclosure", leftAlignedComponentListView);
        navLeftListEnclosure.add(leftAlignedComponentListView);
        navLeftListEnclosure.setRenderBodyOnly(false).setOutputMarkupPlaceholderTag(true);

        EnclosureContainer navRightListEnclosure = new EnclosureContainer("navRightListEnclosure", rightAlignedComponentListView);
        navRightListEnclosure.add(rightAlignedComponentListView);
        navRightListEnclosure.setRenderBodyOnly(false).setOutputMarkupPlaceholderTag(true);
        collapse.add(navLeftListEnclosure, navRightListEnclosure);

        container.add(collapse, collapseButton, getBrandNameLink());
        collapseButton.add(newToggleNavigationLabel("toggleNavigationLabel"));
        add(container);
    }

    /**
     * creates a new transparent inner container which is used to append some css classes.
     *
     * @param componentId
     *            The non-null id of a new navigation component
     * @return a new inner container of the navigation bar.
     */
    protected TransparentWebMarkupContainer newContainer(final String componentId) {
        return new TransparentWebMarkupContainer(componentId) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);

                Attributes.removeClass(tag, "container", "container-fluid");

                if (isFluid()) {
                    Attributes.addClass(tag, "container-fluid");
                } else {
                    Attributes.addClass(tag, "container");
                }
            }
        };
    }

    /**
     * @return true, if the navigation is rendered for a fluid page layout.
     */
    public boolean isFluid() {
        return fluid.getObject();
    }

    /**
     * marks the navigation to be rendered inside a fluid page layout.
     *
     * @return the component's current instance.
     */
    public Navbar fluid(boolean fluid) {
        this.fluid.setObject(fluid);

        return this;
    }

    private Component getBrandNameLink() {
        if (brandNameLink == null) {
            brandNameLink = newBrandNameLink("brandName");
        }
        return brandNameLink;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, "navbar",
                collapseBreakpoint.getObject().cssClassName(),
                invertModel.getObject(),
                position.getObject().cssClassName(),
                backgroundColor.getObject().cssClassName()
        );

        Attributes.set(tag, "role", "navigation");
    }

    /**
     * creates a component model which holds only the components with a specific position.
     *
     * @param components   to filter
     * @param withPosition position a component must have
     * @return new model
     */
    private IModel<List<Component>> newPositionDependedComponentModel(final List<INavbarComponent> components, final PositionFilter withPosition) {
        return new LoadableDetachableModel<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Component> load() {
                return transform(Generics2.filter(components, withPosition), NAVBAR_COMPONENT_TO_COMPONENT_FUNCTION);
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
        return new ListView<>(componentId, listModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Component> components) {
                Component component = components.getModelObject();
                components.add(new CssClassNameAppender("nav-item"));
                components.add(component);
                component.add(new CssClassNameAppender("nav-link"));

                Behaviors.remove(components, activeStateAppender);

                if (component instanceof Activatable) {
                    Activatable activatable = (Activatable) component;

                    if (activatable.isActive(component)) {
                    	component.add(activeStateAppender);
                    }
                }

                if (component instanceof Invertible) {
                    ((Invertible<?>) component).setInverted(!Models.isNullOrEmpty(invertModel));
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
    protected Component newBrandNameLink(String componentId) {
        BookmarkablePageLink<Page> link = new BookmarkablePageLink<>(componentId, getHomePage()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();

                Component brandLabel = get("brandLabel");
                brandLabel.configure();
                if (brandLabel.isVisible()) {
                    setVisible(true);
                } else {
                    Component brandImage = get("brandImage");
                    brandImage.configure();
                    setVisible(brandImage.isVisible());
                }
            }
        };
        link.setOutputMarkupPlaceholderTag(true);

        link.add(newBrandLabel("brandLabel"));
        link.add(newBrandImage("brandImage"));

        return link;
    }

    /**
     * @param markupId The component's markup id
     * @return a new brand name label.
     */
    protected Label newBrandLabel(String markupId) {
        return new Label(markupId) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onConfigure() {
                super.onConfigure();

                setVisible(getDefaultModel() != null);
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
            protected void onConfigure() {
                super.onConfigure();

                setVisible(isResourceSet());
            }

            private boolean isResourceSet() {
                return getImageResourceReference() != null || getImageResource() != null;
            }

            @Override
            protected boolean getStatelessHint() {
                return isResourceSet() ? super.getStatelessHint() : true;
            }
        };
    }

    /**
     * @return the page class which is used for the brand name link. The
     * application's homepage is used by default.
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
     * @return background color of navbar
     */
    public BackgroundColorBehavior.Color getBackgroundColor() {
        return backgroundColor.getObject();
    }

    /**
     * @return the breakpoint when navigation bar is collapsed
     */
    public CollapseBreakpoint getCollapseBreakpoint() {
        return collapseBreakpoint.getObject();
    }

    /**
     * adds a component to the given position inside the navbar
     *
     * @param components the components to add
     * @return this component instance for chaining
     */
    public final Navbar addComponents(final INavbarComponent... components) {
        return addComponents(List.of(components));
    }

    /**
     * adds a component to the given position inside the navbar
     *
     * @param component the component to add
     * @return this component instance for chaining
     */
    public final Navbar addComponents(final NavbarText component) {
        extraItems.add(component);
        return this;
    }

    public final String newExtraItemId() {
        return extraItems.newChildId();
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
     * creates a new button label
     *
     * @param componentId the wicket component id
     * @return new label instance
     */
    protected Label newToggleNavigationLabel(final String componentId) {
        return new Label(componentId, "Toggle Navigation");
    }

    /**
     * creates a new transparent container which is used to append the "data-bs-target" attribute to the collapse button.
     *
     * @param componentId The non-null id of collapse button
     * @param selector    non-null jquery selector to collapse
     * @return a button container.
     */
    protected TransparentWebMarkupContainer newCollapseButton(String componentId, String selector) {
        TransparentWebMarkupContainer button = new TransparentWebMarkupContainer(componentId);
        Args.notNull(selector, "selector");
        button.add(new AttributeModifier("data-bs-target", selector));
        return button;
    }

    /**
     * creates a new transparent inner container which is used to append some
     * css classes.
     *
     * @param componentId The non-null id of a new navigation component
     * @return a new inner container of the navigation bar.
     */
    protected TransparentWebMarkupContainer newCollapseContainer(String componentId) {
        TransparentWebMarkupContainer collapse = new TransparentWebMarkupContainer(componentId);
        collapse.setOutputMarkupId(true); // needed to put the "data-bs-target" attribute of the collapse button
        return collapse;
    }

    /**
     * sets the label of the brand name button
     *
     * @param brandName the brand name label
     * @return the component's current instance
     */
    public Navbar setBrandName(final IModel<String> brandName) {
        Component name = getBrandNameLink().get("brandLabel");
        name.setDefaultModel(brandName);

        return this;
    }

    /**
     * sets an image in the brand button
     *
     * @param imageResourceReference required
     * @param imageAltAttrModel      optional, but should be provided
     * @return this instance for chaining
     */
    public Navbar setBrandImage(final ResourceReference imageResourceReference, final IModel<String> imageAltAttrModel) {
        Image brandImage = (Image) getBrandNameLink().get("brandImage");

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
    @Override
	public Navbar setInverted(final boolean invert) {
        this.invertModel.setObject(invert ? "navbar-dark" : "navbar-light");

        return this;
    }

    /**
     * Sets background color of navbar.
     *
     * @param color the background color
     * @return the component's current instace
     */
    public Navbar setBackgroundColor(final BackgroundColorBehavior.Color color) {
        this.backgroundColor.setObject(color);

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
     * Sets the preferred responsive collapsing of navigation bar.
     *
     * @param breakpoint the breakpoint for collapsing navigation bar
     * @return the component's current instance
     */
    public Navbar setCollapseBreakdown(final CollapseBreakpoint breakpoint) {
        this.collapseBreakpoint.setObject(breakpoint);

        return this;
    }

    @Override
    protected void detachModel() {
        super.detachModel();
        invertModel.detach();
        backgroundColor.detach();
        collapseBreakpoint.detach();
        position.detach();
    }

    /**
     * A {@link Predicate} that filters out all {@link INavbarComponent}s that don't
     * match the given {@link ComponentPosition}.
     */
    private static final class PositionFilter implements Predicate<INavbarComponent>, IClusterable {
        private static final long serialVersionUID = 1L;
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
        public boolean test(final INavbarComponent navbarComponent) {
            return navbarComponent != null && position.equals(navbarComponent.getPosition());
        }

    }

    /**
     * A {@link Function} that maps a {@link INavbarComponent} to a {@link Component}
     */
    private static final class NavbarComponentToComponentFunction implements Function<INavbarComponent, Component>, IClusterable {
        private static final long serialVersionUID = 1L;
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
