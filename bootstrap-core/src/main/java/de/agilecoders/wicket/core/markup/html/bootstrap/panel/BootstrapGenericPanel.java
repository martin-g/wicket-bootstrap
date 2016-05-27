package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.core.util.Components;

/**
 * A convenient implementation of the Bootstrap styles Panel of a Wicket {@link GenericPanel}.
 * 
 * documentation: http://getbootstrap.com/components/#panels
 * 
 * Implement as you would a standard Wicket {@link GenericPanel}.
 * 
 * Adding components to this panel will insert them below the markup for the body and aove the markup for the 
 * footer, per the Bootstrap documentation. This is useful for:
 * 
 * panels with tables - http://getbootstrap.com/components/#panels-tables
 * panel with list groups - http://getbootstrap.com/components/#panels-list-group
 *  
 * @author Eric Hamel <eric.hamel@me.com>
 *
 * @param <T> the type of the panel's model object
 */
public class BootstrapGenericPanel<T> extends GenericPanel<T>{
	
	private static final String _PANEL_TITLE_ID = "panelTitle";
	private static final String _PANEL_BODY_ID = "panelBody";
	private static final String _PANEL_FOOTER_ID = "panelFooter";

	private PanelType panelType;
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
		this(id, model, panelTitleModel, PanelType.Default);
	}
	
	/**
	 * Construct.
	 * 
	 * @param id the component id 
	 * @param model the component model
	 * @param panelTitleModel the title model
	 * @param panelType the panel type
	 */
	public BootstrapGenericPanel(String id, IModel<T> model, IModel<String> panelTitleModel, PanelType panelType) {
		super(id, model);
		
		this.panelType = panelType;
		this.titleModel = panelTitleModel;
		
		//Panel Title
		Label panelTitle = newTitleLabel(_PANEL_TITLE_ID, getModel(), getTitleModel());
		addOrReplace(panelTitle);
		Components.hideIfModelIsEmpty(panelTitle);
		
		//Panel Body
		Panel panelBody = newBodyPanel(_PANEL_BODY_ID, getModel());
		panelBody.add(CssClassNameModifier.append("class", "panel-body"));
		addOrReplace(panelBody);
		Components.hideIfModelIsEmpty(panelBody);
		
		
		//Panel Footer
		Panel panelFooter = newFooterPanel(_PANEL_FOOTER_ID, getModel());
		panelFooter.add(CssClassNameModifier.append("class", "panel-footer"));
		addOrReplace(panelFooter);
		Components.hideIfModelIsEmpty(panelFooter);
		
	}
	
	@Override
	protected void onBeforeRender() {
	
		add(new PanelBehavior(getPanelType()));
		
		super.onBeforeRender();
	}
	
	/**
	 * Getter for Panel Type.
	 * 
	 * @return the panelType
	 */
	public PanelType getPanelType() {
		return panelType;
	}

	/**
	 * Setter for Panel Type.
	 * 
	 * @param panelType the panelType
	 * @return the class instance
	 */
	public BootstrapGenericPanel<T> setPanelType(PanelType panelType) {
		
		this.panelType = panelType;
		
		return this;
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
		emptyPanel.setDefaultModel(null);
		
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
		emptyPanel.setDefaultModel(null);
		
		return emptyPanel;
	}
	
}
