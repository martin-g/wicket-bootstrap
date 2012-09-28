package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.Activatable;
import de.agilecoders.wicket.util.Behaviors;

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
        /**
         * fixate at the top of the screen
         */
        TOP,

        /**
         * fixate at the bottom of the screen
         */
        BOTTOM,

        /**
         * do not fixate the position
         */
        NONE
    }

    /**
     * indicates the position of a button inside the navigation bar.
     */
    public static enum ButtonPosition {
        LEFT, RIGHT
    }

    private final WebMarkupContainer container;

    private IModel<String> positionModel;
    private IModel<String> containerModel;
    private IModel<String> invertModel;
    private CssClassNameAppender activeStateAppender;

    private final BookmarkablePageLink<Page> brandNameLink;
    private final Label brandLabel;
    private final Image brandImage;
    private final Component navRightList;
    private final Component navLeftList;

    private Position position = Position.NONE;
    private boolean fluid = false;

    private final List<Component> buttonLeftList = Lists.newArrayList();
    private final List<Component> buttonRightList = Lists.newArrayList();

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

        container = newContainer("container");
        brandNameLink = newBrandNameLink("brandName");
        brandLabel = newBrandLabel("brandLabel");
        brandNameLink.add(brandLabel);
        brandImage = newBrandImage("brandImage");
        brandNameLink.add(brandImage);
        navLeftList = newNavigation("navLeftList", buttonLeftList);
        navRightList = newNavigation("navRightList", buttonRightList);

        activeStateAppender = new CssClassNameAppender("active");

        positionModel = Model.of("");
        containerModel = Model.of("");
        invertModel = Model.of("");

        add(new CssClassNameAppender("navbar"));
        add(new CssClassNameAppender(invertModel));
        add(new CssClassNameAppender(positionModel));
        container.add(new CssClassNameAppender(containerModel));
        add(container, brandNameLink, navLeftList, navRightList);
    }

    /**
     * creates a new navigation list
     *
     * @param componentId The non-null id of a new navigation component
     * @param listModel   The component's model
     * @return a new navigation list view instance
     */
    protected Component newNavigation(final String componentId, final List<Component> listModel) {
        return new ListView<Component>(componentId, listModel) {
            @Override
            protected void populateItem(ListItem<Component> components) {
                Component button = components.getModelObject();
                components.add(button);

                Behaviors.remove(components, activeStateAppender);

                if (button instanceof Activatable) {
                    Activatable activatable = (Activatable) button;

                    if (activatable.isActive(button)) {
                        components.add(activeStateAppender);
                    }
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
     * 
     * @param componentId
     * @return
     */
    protected Label newBrandLabel(String componentId) {
    	return new Label(componentId) {

    		private static final long serialVersionUID = 1L;

			@Override
    		public boolean isVisible() {
    			return getDefaultModel() != null;
    		}
    	};
    }
    
    /**
     * 
     * @param componentId
     * @return
     */
    protected Image newBrandImage(String componentId) {
    	return new Image(componentId, Model.of("")) {

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
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        containerModel.setObject(isFluid() ? "container-fluid" : "container");

        if (Position.TOP == getPosition()) {
            positionModel.setObject("navbar-fixed-top");
        } else if (Position.BOTTOM == getPosition()) {
            positionModel.setObject("navbar-fixed-bottom");
        } else {
            positionModel.setObject("");
        }

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
    	brandLabel.setDefaultModel(brandName);

        return this;
    }
    
    /**
     * sets an image in the brand button
     * 
     * @param imageResourceReference required
     * @param imageAltAttrModel		 optional, but should be provided
     * @return
     */
	public Navbar brandImage(ResourceReference imageResourceReference,
			IModel<String> imageAltAttrModel) {
		brandImage.setImageResourceReference(imageResourceReference);

		if (imageAltAttrModel != null) {
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
    public Navbar invert(boolean invert) {
        this.invertModel.setObject(invert ? "navbar-inverse" : "");

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
