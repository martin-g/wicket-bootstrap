package de.agilecoders.wicket.markup.html.bootstrap.common;

import com.google.common.base.Strings;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

import java.io.Serializable;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class NotificationMessage implements Serializable {

    private IModel<String> message;
    private IModel<String> header;
    private boolean inlineHeader;
    private Duration duration;

    public NotificationMessage(IModel<String> message) {
        this(message, Model.of(""), true);
    }

    public NotificationMessage(IModel<String> message, IModel<String> header) {
        this(message, header, true);
    }

    public NotificationMessage(IModel<String> message, IModel<String> header, boolean inlineHeader) {
        this.message = message;
        this.header = header;
        this.inlineHeader = inlineHeader;
    }

    public NotificationMessage hideAfter(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Duration hideAfter() {
        return duration;
    }

    public IModel<String> message() {
        return message;
    }

    public IModel<String> header() {
        return header;
    }

    public boolean inlineHeader() {
        return inlineHeader;
    }

    @Override
    public String toString() {
        return Strings.isNullOrEmpty(header.getObject()) ? message.getObject()
                                                         : header.getObject() + " " + message.getObject();
    }
}
