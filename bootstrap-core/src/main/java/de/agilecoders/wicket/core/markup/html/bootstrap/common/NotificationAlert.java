package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import java.time.Duration;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Alert;

/**
 * An {@link Alert} implementation that shows an {@link INotificationMessage}.
 *
 * @author miha
 */
public class NotificationAlert extends Alert {
    private static final long serialVersionUID = 1L;
    private final IModel<INotificationMessage> notificationModel;
    private final Duration duration;

    /**
     * Construct.
     *
     * @param markupId The component id
     * @param message  The feedback message
     */
    public NotificationAlert(final String markupId, final FeedbackMessage message) {
        this(markupId, message, Duration.ZERO);
    }

    /**
     * Construct.
     *
     * @param markupId The component id
     * @param message  The feedback message
     * @param duration amount of time when message should be closed
     */
    public NotificationAlert(final String markupId, final FeedbackMessage message, final Duration duration) {
        super(markupId, Model.of(""));

        this.duration = duration;
        this.notificationModel = Model.of();

        type(Alert.Type.from(message.getLevelAsString()));

        if (message.getMessage() instanceof INotificationMessage) {
            withNotificationMessage((INotificationMessage) message.getMessage());
        } else {
            withMessage(Model.of(String.valueOf(message.getMessage())));
            hideAfter(duration);
        }
    }

    /**
     * sets the notification message
     *
     * @param notificationMessage The notification message
     * @return this instance for chaining.
     */
    private NotificationAlert withNotificationMessage(final INotificationMessage notificationMessage) {
        notificationModel.setObject(notificationMessage);

        hideAfter(notificationMessage.hideAfter() == null ? duration : notificationMessage.hideAfter());
        useInlineHeader(notificationMessage.inlineHeader());
        withHeader(notificationMessage.header());
        withMessage(notificationMessage.message());

        getMessage().setEscapeModelStrings(notificationMessage.escapeModelStrings());

        return this;
    }

    @Override
    public void detachModels() {
        super.detachModels();

        notificationModel.detach();
    }
}
