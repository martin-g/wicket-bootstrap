package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.EnclosureContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior.Radius;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

/**
 * A convenient implementation of the Bootstrap-styled Card of a Wicket {@link GenericPanel}.
 *
 * documentation: https://getbootstrap.com/docs/5.3/components/card/
 *
 * Implement as you would a standard Wicket {@link GenericPanel}.
 *
 * Adding components to this panel will insert them below the markup for the body and above the markup for the
 * footer, per the Bootstrap documentation.
 *
 * @author Eric Hamel <eric.hamel@me.com>
 *
 * @param <T> the type of the panel's model object
 */
public class BootstrapGenericPanel<T> extends GenericPanel<T>{

	private static final String _PANEL_HEADER_ID = "panelHeader";
	private static final String _PANEL_TITLE_ID = "panelTitle";
	private static final String _PANEL_IMAGE_ID = "panelImageTop";
	private static final String _PANEL_BODY_ID = "panelBody";
	private static final String _PANEL_FOOTER_ID = "panelFooter";

	private final IModel<String> titleModel;

	private PanelType panelType;

	/**
	 * Construct.
	 *
	 * @param id the component id
	 */
	public BootstrapGenericPanel(String id){
		this(id, null, null, null);
	}

	/**
	 * Construct.
	 *
	 * @param id the component id
	 * @param panelType styling of the panel
	 */
	public BootstrapGenericPanel(String id, PanelType panelType){
		this(id, null, null, panelType);
	}

	/**
	 * Construct.
	 *
	 * @param id the component id
	 * @param model the component model
	 * @param panelTitleModel the title model
	 */
	public BootstrapGenericPanel(String id, IModel<T> model, IModel<String> panelTitleModel) {
		this(id, model, panelTitleModel, null);
	}

	/**
	 * Construct.
	 *
	 * @param id the component id
	 * @param model the component model
	 * @param panelTitleModel the title model
	 * @param panelType styling of the panel
	 */
	public BootstrapGenericPanel(String id, IModel<T> model, IModel<String> panelTitleModel, PanelType panelType) {
		super(id, model);

		this.titleModel = panelTitleModel;
		this.panelType = panelType;
	}

	/**
	 * @param panelType
	 * @return this for chaining
	 */
	public BootstrapGenericPanel<T> withPanelType(PanelType panelType) {
		this.panelType = panelType;
		return this;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		//Panel Title
		Label panelTitle = newTitleLabel(_PANEL_TITLE_ID, getModel(), getTitleModel());
		EnclosureContainer header = new EnclosureContainer(_PANEL_HEADER_ID, panelTitle);
		header.setRenderBodyOnly(false);
		header.add(panelTitle);
		add(header);
		Components.hideIfModelIsEmpty(panelTitle);

		//Top Panel Image
        Component topImage = newTopImage(_PANEL_IMAGE_ID, getModel());
        add(topImage);
        Components.hideIfModelIsEmpty(topImage);

		//Panel Body
		Panel panelBody = newBodyPanel(_PANEL_BODY_ID, getModel());
		add(panelBody);
		Components.hideIfModelIsEmpty(panelBody);

		//Panel Footer
		Panel panelFooter = newFooterPanel(_PANEL_FOOTER_ID, getModel());
		add(panelFooter);
		Components.hideIfModelIsEmpty(panelFooter);

		header.add(new BackgroundColorBehavior(() -> panelType.getBackgroundColor()) {
			@Override
			public boolean isEnabled(Component component) {
				return isCustomPanelStyleSet();
			}
		});
		header.add(new TextColorBehavior(() -> panelType.getTextColor()) {
			@Override
			public boolean isEnabled(Component component) {
				return isCustomPanelStyleSet();
			}
		});

		add(new BorderBehavior() {
			public void onConfigure(Component component) {
				color(panelType.getBorderColor());
			}
			@Override
			public boolean isEnabled(Component component) {
				return isCustomPanelStyleSet();
			}
		}.type(Type.All).radius(Radius.All));
	}

	private boolean isCustomPanelStyleSet() {
		return !PanelType.Default.equals(panelType) && panelType != null;
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);

		Components.assertTag(this, tag, "div");
		Attributes.addClass(tag, "card");
	}

	/**
	 * Getter for Title Model.
	 *
	 * @return the Title Model
	 */
	public IModel<String> getTitleModel() {
		return titleModel;
	}

	/**
	 * Override this method to provide your own implementation of a Label.
	 *
	 * @param id the component id
	 * @param model the component model
	 * @param titleModel the title model
	 * @return the label to use
	 */
	protected Label newTitleLabel(String id, IModel<T> model, IModel<String> titleModel){
		return new Label(id, titleModel);
	}

	/**
	 * Override this method to provide a body for this panel..
	 *
	 * @param id the component id
	 * @param model the component model
	 * @return the panel to use
	 */
	protected Panel newBodyPanel(String id, IModel<T> model){

		Panel emptyPanel = new EmptyPanel(id);
		emptyPanel.setDefaultModel(new Model<>());

		return emptyPanel;

	}

	/**
	 * Override this method to provide a footer for this panel.
	 * @param id
	 * @param model
	 * @return
	 */
	protected Panel newFooterPanel(String id, IModel<T> model){

		Panel emptyPanel = new EmptyPanel(id);
		emptyPanel.setDefaultModel(new Model<>());

		return emptyPanel;
	}

    /**
     * Override this method to provide top image for this panel.
     *
     * @param id the image identifier
     * @param model the panel model
     * @return image to show in panel
     */
	protected Component newTopImage(String id, IModel<T> model) {
	    Panel emptyTopImage = new EmptyPanel(id);
	    emptyTopImage.setDefaultModel(new Model<>());

	    return emptyTopImage;
    }
}
