package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import com.google.common.base.Strings;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;

/**
 * <h1>Component behavior.</h1>
 * <p>This behavior add an attribute to dom with error message.</p>
 */
public class FeedbackMessagePopupBehavior extends Behavior {

	private String attributeName;
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 *
	 * @param attribute name of tag attribute
	 */
	public FeedbackMessagePopupBehavior(String attribute) {
		this.attributeName = attribute;
	}

	@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		FeedbackMessages messages = component.getFeedbackMessages();
		if (messages != null) {
			for (FeedbackMessage message : messages.messages(ErrorLevelFeedbackMessageFilter.ALL)) {
				String messageString = Strings.nullToEmpty(message.getMessage().toString());
				tag.getAttributes().put(attributeName, messageString);
			}
		}
	}
}
