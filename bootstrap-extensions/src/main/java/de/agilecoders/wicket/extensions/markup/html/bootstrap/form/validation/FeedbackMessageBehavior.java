package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.util.lang.Args;

/**
 * <h1>Component behavior.</h1>
 * <p>This behavior adds an attribute to DOM element with feedback message.</p>
 *
 * @author Alexey Volkov
 * @since 15.09.2014
 */
class FeedbackMessageBehavior extends Behavior {

    private static final long serialVersionUID = 3116618186507530804L;
    private final String attributeName;

    /**
     * Construct.
     *
     * @param attribute name of tag attribute
     */
    FeedbackMessageBehavior(String attribute) {
        Args.notNull(attribute, "attribute");
        this.attributeName = attribute;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        FeedbackMessages messages = component.getFeedbackMessages();
        if (messages != null && !messages.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (FeedbackMessage message : messages.messages(ErrorLevelFeedbackMessageFilter.ALL)) {
                if (!message.isRendered()) {
                    String msg = message.getMessage().toString();
                    if (msg != null && !msg.isEmpty()) {
                        sb.append(msg);
                    }
                }
            }
            String messageString = sb.toString();
            if (!messageString.isEmpty()) {
                tag.getAttributes().put(attributeName, messageString);
            }
        }
    }
}
