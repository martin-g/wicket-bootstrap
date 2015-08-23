package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteEditor;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteFileStorage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.file.Folder;
import org.wicketstuff.annotation.mount.MountPath;


@MountPath(value = "/summernote")
public class SummernotePage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public SummernotePage(PageParameters parameters) {
        super(parameters);

        final NotificationPanel feedback = new NotificationPanel("feedback");
        feedback.setOutputMarkupId(true);

        Form<Void> form = new Form<Void>("form");

        SummernoteConfig summernoteConfig = new SummernoteConfig();
        Folder folder = new Folder(System.getProperty("java.io.tmpdir"), "bootstrap-summernote");
        folder.mkdirs();
        SummernoteConfig.addStorage(new SummernoteFileStorage("fileStorage", folder));
        final SummernoteEditor summernoteEditor = new SummernoteEditor("summernote", Model.of("sxa"), summernoteConfig);
        form.add(summernoteEditor);
        form.add(new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                info("Submitted text: \n" + summernoteEditor.getSubmittedContent());
                target.add(feedback);
            }
        }.setDefaultFormProcessing(false));

        add(feedback, form);
    }

}
