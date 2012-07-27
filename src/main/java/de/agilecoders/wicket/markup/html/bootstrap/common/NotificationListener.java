package de.agilecoders.wicket.markup.html.bootstrap.common;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.util.Map;

/**
 * A {@code NotificationListener} adds the {@link NotificationAware} page's notification panel to the
 * ajax response if there are components which have any feedback message.
 *
 * @author miha
 * @version 1.0
 */
public class NotificationListener implements AjaxRequestTarget.IListener {

    @Override
    public void onBeforeRespond(Map<String, Component> map, final AjaxRequestTarget target) {
        if (hasNotifications()) {
            Page page = target.getPage();

            if (page instanceof NotificationAware) {
                target.add(((NotificationAware) page).getNotificationPanel());
            }
        }
    }

    @Override
    public void onAfterRespond(Map<String, Component> map, AjaxRequestTarget.IJavaScriptResponse response) {
        if (hasNotifications()) {
            response.addJavaScript("$(\".alert\").alert();");
        }
    }

    /**
     * Check for notifications in the {@link Session}
     *
     * @return true, if there are existing feedback messages
     */
    private boolean hasNotifications() {
        return Session.exists() && !Session.get().getFeedbackMessages().isEmpty();
    }

}
