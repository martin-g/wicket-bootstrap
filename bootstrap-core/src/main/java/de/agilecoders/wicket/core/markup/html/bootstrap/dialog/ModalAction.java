package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.jquery.JQuery.AbstractFunction;

public class ModalAction extends AbstractFunction {
	
	public static enum Action {
		show,
		hide
	}
	
	public ModalAction() {
		super("modal");
	}
	
	
	public static ModalAction show() {
		return  action(Action.show);
	}
	
	public static ModalAction hide() {
		return  action(Action.hide);
	}

	public static ModalAction action(Action action) {
		ModalAction modalAction = new ModalAction();
		modalAction.addParameter("'"+action.name()+"'");
		return modalAction;
	}
}
