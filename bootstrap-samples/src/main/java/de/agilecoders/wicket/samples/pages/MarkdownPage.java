package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown.MarkdownConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown.MarkdownTextArea;


@MountPath(value = "/markdown")
public class MarkdownPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public MarkdownPage(PageParameters parameters) {
        super(parameters);

        addForm("", false);
    }

    private void addForm(String idSuffix, final boolean isEditable) {
        final NotificationPanel feedback = new NotificationPanel("feedback" + idSuffix);
        feedback.setOutputMarkupId(true);

        Form<Void> form = new Form<>("form" + idSuffix);

        final IModel<String> model = Model.of("###Lorem \n\n _ipsum_ **dolor** sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam \r\nnvoluptua.");

        MarkdownTextArea editor = new MarkdownTextArea("markdown" + idSuffix, model);
        MarkdownConfig markdownConfig = editor.getConfig();
        markdownConfig.withHeight("550px")
            .withWidth("col-lg-8")
            .withResize(MarkdownConfig.Resize.both)
            .withIconLibrary(MarkdownConfig.IconLibrary.fa)
            .withAutofocus(true);

        form.add(editor);
        form.add(new AjaxButton("submit" + idSuffix) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                info("Submitted text: \n" + model.getObject());
                target.add(feedback);
            }
        });

        add(feedback, form);
    }

}
