package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.tester.TagTester;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link NotificationPanel} component
 *
 * @author miha
 */
class NotificationPanelTest  extends WicketApplicationTest{

    @Test
    void infoMessageIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.info("test");

        startComponentInPage(panel);
        tester().assertNoErrorMessage();
        tester().assertInfoMessages("test");
    }

    @Test
    void errorMessageIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error("test");

        startComponentInPage(panel);
        tester().assertErrorMessages("test");
    }

    @Test
    void notificationMessageIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        NotificationMessage notificationMessage = new NotificationMessage(Model.of("test"));
        panel.error(notificationMessage);

        startComponentInPage(panel);
        tester().assertErrorMessages(notificationMessage);
    }

    @Test
    void notificationMessageHeaderIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test"), Model.of("header message")));

        startComponentInPage(panel);
        tester().assertContains("header message");
    }

    @Test
    void infoCssIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.info(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("message");

        assertCssClass(t, "alert", "alert-info");
    }

    @Test
    void errorCssIsRendered() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("message");

        assertCssClass(t, "alert", "alert-danger");
    }

    @Test
    void closeButtonIsPresent() {
        NotificationPanel panel = new NotificationPanel(id());
        panel.error(new NotificationMessage(Model.of("test")));

        startComponentInPage(panel);
        TagTester t = tester().getTagByWicketId("close");

        assertCssClass(t, "btn-close");
        MatcherAssert.assertThat(t.getAttribute("data-bs-dismiss"), is(equalTo("alert")));
    }

    @Test
    void fencing()
    {
        TestPage page = tester().startPage(TestPage.class);
        page.containerInput.error("error");

        // container messages should be visible to container feedbacks but not outside

        assertTrue(page.containerFeedback.anyMessage());
        assertTrue(page.containerFeedback2.anyMessage());
        assertFalse(page.formFeedback.anyMessage());
        assertFalse(page.externalFeedback.anyMessage());

        page = tester().startPage(TestPage.class);
        page.formInput.error("error");

        // form messages should be visible only to the form feedbacks

        assertFalse(page.containerFeedback.anyMessage());
        assertFalse(page.containerFeedback2.anyMessage());
        assertTrue(page.formFeedback.anyMessage());
        assertFalse(page.externalFeedback.anyMessage());

        page = tester().startPage(TestPage.class);
        page.externalLabel.error("error");

        // external messages should be picked up only by catch-all feedbacks

        assertFalse(page.containerFeedback.anyMessage());
        assertFalse(page.containerFeedback2.anyMessage());
        assertFalse(page.formFeedback.anyMessage());
        assertTrue(page.externalFeedback.anyMessage());

        page = tester().startPage(TestPage.class);
        page.getSession().error("error");

        // session scoped errors should only be picked up by catch-all feedbacks

        assertFalse(page.containerFeedback.anyMessage());
        assertFalse(page.containerFeedback2.anyMessage());
        assertFalse(page.formFeedback.anyMessage());
        assertTrue(page.externalFeedback.anyMessage());
    }

    @Test
    void filtering()
    {
        TestPage page = tester().startPage(TestPage.class);

        // set a filter that will only allow errors or higher

        page.containerFeedback.setFilter(new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR));

        // report an info message - should be filtered out

        page.containerInput.info("info");

        // check info message was filtered out

        assertFalse(page.containerFeedback.anyMessage());
        assertTrue(page.containerFeedback2.anyMessage());

        // ensure filtered out messages dont leak

        assertFalse(page.formFeedback.anyMessage());
        assertFalse(page.externalFeedback.anyMessage());

        // same setup

        page = tester().startPage(TestPage.class);

        page.containerFeedback.setFilter(new ErrorLevelFeedbackMessageFilter(FeedbackMessage.ERROR));

        // but now with an error message that should not be filtered out

        page.containerInput.error("info");

        // check message was not filtered out

        assertTrue(page.containerFeedback.anyMessage());
        assertTrue(page.containerFeedback2.anyMessage());

        // and that it should not leak

        assertFalse(page.formFeedback.anyMessage());
        assertFalse(page.externalFeedback.anyMessage());

    }

    @Test
    void moving()
    {
        TestPage page = tester().startPage(TestPage.class);
        page.containerInput.error("error");

        assertTrue(page.containerFeedback.anyMessage());
        assertTrue(page.containerFeedback2.anyMessage());

        // does not propagate out of container
        assertFalse(page.formFeedback.anyMessage());

        // remove one of two fencing feedback panels

        page = tester().startPage(TestPage.class);
        page.containerFeedback.remove();

        page.containerInput.error("error");

        assertTrue(page.containerFeedback2.anyMessage());

        // still does not propagate out of container because there is still a fencing panel
        assertFalse(page.formFeedback.anyMessage());

        // remove the last fencing feedback panel

        page = tester().startPage(TestPage.class);
        page.containerFeedback.remove();
        page.containerFeedback2.remove();

        page.containerInput.error("error");

        // now propagates out of container
        assertTrue(page.formFeedback.anyMessage());

    }

    public static class TestPage extends WebPage implements IMarkupResourceStreamProvider
    {
        NotificationPanel externalFeedback, formFeedback, containerFeedback, containerFeedback2;
        Component externalLabel, formInput, containerInput;

        public TestPage()
        {
            externalFeedback = new NotificationPanel("feedback");
            externalLabel = new Label("externalLabel");
            add(externalFeedback, externalLabel);

            Form form = new Form("form");
            formFeedback = new NotificationPanel("formFeedback", form);
            form.add(formFeedback);
            formInput = new TextField("formInput");
            form.add(formInput);
            WebMarkupContainer container = new WebMarkupContainer("container");
            containerFeedback = new NotificationPanel("containerFeedback", container);
            containerFeedback2 = new NotificationPanel("containerFeedback2", container);
            container.add(containerFeedback, containerFeedback2);
            containerInput = new TextField("containerInput");
            container.add(containerInput);
            form.add(container);
            add(form);
        }

        @Override
        public IResourceStream getMarkupResourceStream(MarkupContainer container, Class containerClass)
        {
            return new StringResourceStream(//
                                            "    <body>" + //
                                            "   <div wicket:id='feedback'/>" + //
                                            "   <div wicket:id='externalLabel'/>" + //
                                            "   <form wicket:id='form'>" + //
                                            "       <div wicket:id='formFeedback'/>" + //
                                            "       <input wicket:id='formInput' type='text'/>" + //
                                            "       <div wicket:id='container'>" + //
                                            "           <div wicket:id='containerFeedback'/>" + //
                                            "           <input wicket:id='containerInput' type='text'/>" + //
                                            "           <div wicket:id='containerFeedback2'/>" + //
                                            "       </div>" + //
                                            "    </form>" + //
                                            "</body>");
        }
    }
}
