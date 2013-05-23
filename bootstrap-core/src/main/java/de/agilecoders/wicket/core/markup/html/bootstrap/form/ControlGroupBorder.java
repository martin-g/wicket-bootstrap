package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.io.Serializable;
import java.util.List;

/**
 * A {@link ControlGroupBorder} is a special collection of components that
 * contains a form field, a label, a help message (optional) and a container
 * for feedback messages.
 *
 * @author miha
 * @deprecated please use {@link ControlGroup} instead
 */
@Deprecated
public class ControlGroupBorder extends Border {
    private final Component feedback;
    private final Component label;
    private final Component help;

    /**
     * Construct. This constructor uses an empty label.
     *
     * @param id the wicket component id
     */
    public ControlGroupBorder(final String id) {
        this(id, new Model<String>());
    }

    /**
     * Construct.
     *
     * @param id    the wicket component id
     * @param model the control group label
     */
    public ControlGroupBorder(String id, IModel<String> model) {
        super(id, model);

        addToBorder(feedback = newFeedbackMessageContainer("feedback"),
                    label = newLabel("label"),
                    help = newHelpLabel("help"));
    }

    /**
     * creates a new label
     *
     * @param id the component id
     * @return new label
     */
    protected Component newLabel(final String id) {
        return new Label(id, getDefaultModel());
    }

    /**
     * creates a new container for a feedback message
     *
     * @param id the component id
     * @return new feedback message container
     */
    protected Component newFeedbackMessageContainer(final String id) {
        return new Label(id, new Model<String>());
    }

    /**
     * creates a new help label that contains a help message for the form field. This field
     * will be set to invisible if there is no content.
     *
     * @param id the component id
     * @return new help label
     */
    protected Component newHelpLabel(final String id) {
        return new Label(id, new Model<String>());
    }

    /**
     * updates the label, if given model contains null or an empty
     * string, the label will be set to invisible.
     *
     * @param labelModel the label as model
     * @return this instance for chaining
     */
    public ControlGroupBorder label(IModel<String> labelModel) {
        label.setDefaultModel(labelModel);

        return this;
    }

    /**
     * updates the help message, if given model contains null or an empty
     * string, the label will be set to invisible.
     *
     * @param helpModel the help label as model
     * @return this instance for chaining
     */
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

        label.setVisible(!Strings.isEmpty(label.getDefaultModelObjectAsString()));
        help.setVisible(!Strings.isEmpty(help.getDefaultModelObjectAsString()));
    }

    /**
     * @return all feedback messages that passes the {@link #newMessagesFilter()}
     */
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
