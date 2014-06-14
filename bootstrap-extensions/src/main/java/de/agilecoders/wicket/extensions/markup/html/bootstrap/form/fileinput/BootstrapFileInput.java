package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.release.FileinputCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput.release.FileinputJsReference;
import de.agilecoders.wicket.jquery.util.Strings2;

/**
 * Integration with <a href="https://github.com/kartik-v/bootstrap-fileinput">Bootstrap FileInput</a>
 */
public class BootstrapFileInput extends FileUploadField {

    private static final String UPLOAD_BUTTON_CLASS = "fileinput-upload-button";
    private static final String AJAX_EVENT_NAME_SUFFIX = "_fileinput-upload-button-clicked";

    private AjaxEventBehavior ajaxBehavior;
    private boolean showCaption = true;
    private boolean showPreview = true;
    private boolean showRemove = true;
    private boolean showUpload = true;

    /**
     * Constructor
     *
     * @param id The component id
     */
    public BootstrapFileInput(String id) {
        this(id, null);
    }

    /**
     * Constructor
     *
     * @param id The component id
     * @param model The model that will store the uploaded files8
     */
    public BootstrapFileInput(final String id, final IModel<List<FileUpload>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        String ajaxEventName = Strings2.getMarkupId(this) + AJAX_EVENT_NAME_SUFFIX;
        ajaxBehavior = newAjaxFormSubmitBehavior(ajaxEventName);
        add(ajaxBehavior);
    }

    protected AjaxEventBehavior newAjaxFormSubmitBehavior(String event) {
        return new AjaxFormSubmitBehavior(event) {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                target.add(getForm());
                BootstrapFileInput.this.onSubmit(target);
            }
        };
    }

    /**
     * A callback method that is called when an error has occurred
     * after usage of the <em>Upload</em> button
     *
     * @param target The Ajax request handler
     */
    protected void onError(AjaxRequestTarget target) {
    }

    /**
     * A callback method that is called on successful file upload triggered
     * by the usage of the <em>Upload</em> button.
     *
     * @param target The Ajax request handler
     */
    protected void onSubmit(AjaxRequestTarget target) {
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        FileinputJsReference.INSTANCE.renderHead(response);
        FileinputCssReference.INSTANCE.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript(createScript()));
    }

    public boolean isShowCaption() {
        return showCaption;
    }

    public BootstrapFileInput withShowCaption(final boolean showCaption) {
        this.showCaption = showCaption;
        return this;
    }

    public boolean isShowPreview() {
        return showPreview;
    }

    public BootstrapFileInput withShowPreview(final boolean showPreview) {
        this.showPreview = showPreview;
        return this;
    }

    public boolean isShowRemove() {
        return showRemove;
    }

    public BootstrapFileInput withShowRemove(final boolean showRemove) {
        this.showRemove = showRemove;
        return this;
    }

    public boolean isShowUpload() {
        return showUpload;
    }

    public BootstrapFileInput withShowUpload(final boolean showUpload) {
        this.showUpload = showUpload;
        return this;
    }

    protected CharSequence createScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$('#");
        CharSequence markupId = Strings2.getMarkupId(this);
        sb.append(markupId);
        sb.append("').fileinput({");
        sb.append("showCaption: ");
        sb.append(isShowCaption());
        sb.append(", showPreview: ");
        sb.append(isShowPreview());
        sb.append(", showRemove: ");
        sb.append(isShowRemove());
        sb.append(", showUpload: ");
        sb.append(isShowUpload());
        sb.append(", uploadClass: 'btn btn-default ");
        sb.append(UPLOAD_BUTTON_CLASS);
        sb.append("'");
        for (String label : new String[] {"browseLabel", "removeLabel", "uploadLabel", "msgLoading", "msgProgress", "msgSelected"}) {
            sb.append(", ");
            sb.append(label);
            sb.append(": '");
            sb.append(getString(label));
            sb.append("'");
        }
        sb.append("});\n");

        // bind ajax submit behavior on upload button click
        sb.append("$('.file-input:has(#");
        sb.append(markupId);
        sb.append(") .");
        sb.append(UPLOAD_BUTTON_CLASS);
        sb.append("').click(function(e){\n");
        sb.append("    e.preventDefault();\n");
        sb.append("    $('#");
        sb.append(markupId);
        sb.append("').triggerHandler('");
        sb.append(ajaxBehavior.getEvent());
        sb.append("');\n");
        sb.append("});");
        return sb;
    }
}
