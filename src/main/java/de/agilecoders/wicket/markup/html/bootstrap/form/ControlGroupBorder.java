package de.agilecoders.wicket.markup.html.bootstrap.form;

import com.google.common.base.Strings;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ControlGroupBorder extends Border {
    private Label feedback;
    private Label label;
    private Label help;

    public ControlGroupBorder(String id) {
        super(id);

        commonInit();
    }

    public ControlGroupBorder(String id, IModel<?> model) {
        super(id, model);

        commonInit();
    }

    private void commonInit() {
        feedback = new Label("feedback", new Model<String>());
        label = new Label("label", new Model<String>());
        help = new Label("help", new Model<String>());

        addToBorder(feedback, label, help);
    }
    
    public ControlGroupBorder label(IModel<String> labelModel) {
        label.setDefaultModel(labelModel);
        
        return this;
    }

    public ControlGroupBorder help(IModel<String> helpModel) {
        help.setDefaultModel(helpModel);

        return this;
    }

    /**
     * Update the 'visible' flag to indicate the existence (or lack thereof) of feedback messages
     */
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        // Get the messages for the current page
        List<FeedbackMessage> feedbackMessageList = collectFeedbackMessages();
        if (feedbackMessageList != null && !feedbackMessageList.isEmpty()) {
            FeedbackMessage feedbackMessage = feedbackMessageList.get(0);

            feedback.setDefaultModel(new Model<Serializable>(feedbackMessage.getMessage()));
            feedback.setVisible(true);

            add(new CssClassNameAppender(feedbackMessage.getLevelAsString().toLowerCase()));
        } else {
            feedback.setVisible(false);
        }
        
        label.setVisible(!Strings.isNullOrEmpty(label.getDefaultModelObjectAsString()));
        help.setVisible(!Strings.isNullOrEmpty(help.getDefaultModelObjectAsString()));
    }

    private List<FeedbackMessage> collectFeedbackMessages() {
        return getSession().getFeedbackMessages().messages(newMessagesFilter());
    }

    /**
     * @return Let subclass specify some other filter
     */
    protected IFeedbackMessageFilter newMessagesFilter() {
        return new ContainerFeedbackMessageFilter(this);
    }
}
