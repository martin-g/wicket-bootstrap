package de.agilecoders.wicket.markup.html.bootstrap.common;

import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import java.io.Serializable;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class NotificationPanel extends FeedbackPanel implements IFeedback {

    private List<Serializable> messages;

    public NotificationPanel(String id) {
        super(id);
    }

    public NotificationPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }

}
