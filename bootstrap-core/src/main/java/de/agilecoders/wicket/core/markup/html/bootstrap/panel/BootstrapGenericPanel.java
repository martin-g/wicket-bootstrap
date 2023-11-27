package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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

	private static final String _PANEL_TITLE_ID = "panelTitle";
	private static final String _PANEL_IMAGE_ID = "panelImageTop";
	private static final String _PANEL_BODY_ID = "panelBody";
	private static final String _PANEL_FOOTER_ID = "panelFooter";

	private final IModel<String> titleModel;

	/**
	 * Construct.
	 *
	 * @param id the component id
	 */
	public BootstrapGenericPanel(String id){
		this(id, null, null);
	}

	/**
	 * Construct.
	 *
	 * @param id the component id
	 * @param model the component model
	 * @param panelTitleModel the title model
	 */
	public BootstrapGenericPanel(String id, IModel<T> model, IModel<String> panelTitleModel) {
		super(id, model);

		this.titleModel = panelTitleModel;
	}
	
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		//Panel Title
		Label panelTitle = newTitleLabel(_PANEL_TITLE_ID, getModel(), getTitleModel());
		add(panelTitle);
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
