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

    private final FileInputConfig config;

    private BootstrapFileInputField fileInput;

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
        this(id, model, new FileInputConfig());
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model that will store the uploaded files
     * @param config The options for the JavaScript widget
     */
    public BootstrapFileInput(String id, IModel<List<FileUpload>> model, FileInputConfig config) {
        super(id, model);

        this.config = config;

        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<Void> form = new Form<Void>("fileInputForm");
        add(form);

        this.fileInput = newBootstrapFileInputField("fileInput", getModel(), config);
        fileInput.setForm(form);
        form.add(fileInput);
    }

    private BootstrapFileInputField newBootstrapFileInputField(String id, IModel<List<FileUpload>> model, FileInputConfig config) {
        return new BootstrapFileInputField(id, model, config) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                BootstrapFileInput.this.onSubmit(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                BootstrapFileInput.this.onError(target);
            }
        };
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
        return config.showCaption();
    }

    public BootstrapFileInput withShowCaption(final boolean showCaption) {
        config.showCaption(showCaption);
        return this;
    }

    public boolean isShowPreview() {
        return config.showPreview();
    }

    public BootstrapFileInput withShowPreview(final boolean showPreview) {
        config.showPreview(showPreview);
        return this;
    }

    public boolean isShowRemove() {
        return config.showRemove();
    }

    public BootstrapFileInput withShowRemove(final boolean showRemove) {
        config.showRemove(showRemove);
        return this;
    }

    public boolean isShowUpload() {
        return config.showUpload();
    }

    public BootstrapFileInput withShowUpload(final boolean showUpload) {
        config.showUpload(showUpload);
        return this;
    }

    public FileInputConfig getConfig() {
        return config;
    }
}
