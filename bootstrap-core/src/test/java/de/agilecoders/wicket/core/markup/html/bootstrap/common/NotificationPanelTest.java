package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link NotificationPanel} component
 *
 * @author miha
 */
public class NotificationPanelTest  extends WicketApplicationTest{

    @Test
    public void infoMessageIsRendered() throws Exception {
        NotificationPanel panel = new NotificationPanel(id());
        panel.info("test");

        startComponentInPage(panel);
        tester().assertNoErrorMessage();
        tester().assertInfoMessages("test");
    }

    @Test
    public void errorMessageIsRendered() throws Exception {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error("test");

        startComponentInPage(panel);
        tester().assertErrorMessages("test");
    }

    @Test
    public void notificationMessageIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        NotificationMessage notificationMessage = new NotificationMessage(Model.of("test"));
        panel.error(notificationMessage);

        startComponentInPage(panel);
        tester().assertErrorMessages(notificationMessage);
    }

    @Test
    public void notificationMessageHeaderIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test"), Model.of("header message")));

        startComponentInPage(panel);
        tester().assertContains("header message");
    }

    @Test
    public void infoCssIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.info(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("message");

        assertCssClass(t, "alert", "alert-info");
    }

    @Test
    public void errorCssIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("message");

        assertCssClass(t, "alert", "alert-danger");
    }

    @Test
    public void closeButtonIsPresent() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("close");

        assertCssClass(t, "close");
        assertThat(t.getAttribute("data-dismiss"), is(equalTo("alert")));
    }
}
