package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.BootstrapFileInput;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class ExtensionsBootstrapFileInputPage extends BasePage {
    private static final long serialVersionUID = 1L;
    private final NotificationPanel feedback;

    public ExtensionsBootstrapFileInputPage(PageParameters parameters) {
        super(parameters);

        addBootstrapFileUploadDemo();
        this.feedback = new NotificationPanel("feedback");
        feedback.setOutputMarkupId(true);
        add(feedback);
    }
    private void addBootstrapFileUploadDemo() {
        final IModel<List<FileUpload>> model = new ListModel<>();
        Form<Void> bootstrapFileUploadForm = new Form<>("bootstrapFileUploadForm") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                super.onSubmit();

                List<FileUpload> fileUploads = model.getObject();
                if (fileUploads != null) {
                    for (FileUpload upload : fileUploads) {
                        success("Normal Upload: " + upload.getClientFileName());
                    }
                }
            }
        };
        bootstrapFileUploadForm.setOutputMarkupId(true);
        add(bootstrapFileUploadForm);

        BootstrapFileInput bootstrapFileInput = new BootstrapFileInput("bootstrapFileinput", model) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                List<FileUpload> fileUploads = model.getObject();
                if (fileUploads != null) {
                    for (FileUpload upload : fileUploads) {
                        success("Uploaded: " + upload.getClientFileName());
                    }
                }

                target.add(feedback);
            }
        };
        bootstrapFileInput.getConfig().maxFileCount(1);
        bootstrapFileUploadForm.add(bootstrapFileInput);
    }

    @Override
    protected boolean hasNavigation() {
        return false;
    }
}
