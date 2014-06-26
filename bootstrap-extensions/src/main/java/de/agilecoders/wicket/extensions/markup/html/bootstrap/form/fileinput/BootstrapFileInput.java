package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * Integration with <a href="https://github.com/kartik-v/bootstrap-fileinput">Bootstrap FileInput</a>
 *
 * A panel that uses a special (inner) form for Ajax file uploads
 * with {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.BootstrapFileInputField}
 */
public class BootstrapFileInput extends GenericPanel<List<FileUpload>> {

    private final BootstrapFileInputField fileInput;

    /**
     * Constructor.
     *
     * @param id The component id
     */
    public BootstrapFileInput(String id) {
        this(id, null);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model that will store the uploaded files
     */
    public BootstrapFileInput(String id, IModel<List<FileUpload>> model) {
        super(id, model);

        setRenderBodyOnly(true);

        Form<Void> form = new Form<Void>("fileInputForm");
        add(form);

        this.fileInput = new BootstrapFileInputField("fileInput", model) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                BootstrapFileInput.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                BootstrapFileInput.this.onError(target);
            }
        };
        fileInput.setForm(form);
        form.add(fileInput);
    }

    /**
     * A callback method that is called on successful file upload triggered
     * by the usage of the <em>Upload</em> button.
     *
     * @param target The Ajax request handler
     */
    protected void onSubmit(AjaxRequestTarget target) {}

    /**
     * A callback method that is called when there is an error during
     * an Ajax file upload
     *
     * @param target The Ajax request handler
     */
    protected void onError(AjaxRequestTarget target) {}


    public boolean isShowCaption() {
        return getConfig().showCaption();
    }

    public BootstrapFileInput withShowCaption(final boolean showCaption) {
        getConfig().showCaption(showCaption);
        return this;
    }

    public boolean isShowPreview() {
        return getConfig().showPreview();
    }

    public BootstrapFileInput withShowPreview(final boolean showPreview) {
        getConfig().showPreview(showPreview);
        return this;
    }

    public boolean isShowRemove() {
        return getConfig().showRemove();
    }

    public BootstrapFileInput withShowRemove(final boolean showRemove) {
        getConfig().showRemove(showRemove);
        return this;
    }

    public boolean isShowUpload() {
        return getConfig().showUpload();
    }

    public BootstrapFileInput withShowUpload(final boolean showUpload) {
        getConfig().showUpload(showUpload);
        return this;
    }

    private FileInputConfig getConfig() {
        return fileInput.getConfig();
    }
}
