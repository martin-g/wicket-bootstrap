package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapClientSideLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

/**
 * A simple {@link BootstrapLink} close button for the {@link Modal} component.
 * 
 * @author reiern70
 * 
 */
public class BootstrapModalCloseButton extends BootstrapClientSideLink<String> {

	/**
	 * @param id
	 * @param model
	 */
	public BootstrapModalCloseButton(IModel<String> model) {
		this(model, Buttons.Type.Default);
	}

	/**
	 * @param id
	 * @param type
	 */
	public BootstrapModalCloseButton(Type type) {
		this(Model.of("Close"), type);
	}

	/**
	 * @param id
	 * @param model
	 * @param type
	 */
	public BootstrapModalCloseButton(IModel<String> model, Type type) {
		super(Modal.BUTTON_MARKUP_ID, model, type, Model.of(""));
		add(new AttributeModifier("data-dismiss", "modal"));
	}

	@Override
	protected IModel<String> getTarget() {
		String target = null;
		IModal modal = findParent(IModal.class);
	    if(modal != null) {
	    	target = modal.getModal().getMarkupId();
	    }
		return Model.of(target);
	}
}
