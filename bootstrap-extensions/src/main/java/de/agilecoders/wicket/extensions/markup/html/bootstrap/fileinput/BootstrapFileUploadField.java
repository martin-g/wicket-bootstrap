package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileinput;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.fileinput.release.FileinputCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.fileinput.release.FileinputJsReference;
import de.agilecoders.wicket.jquery.util.Strings2;

@NotThreadSafe
public class BootstrapFileUploadField extends FileUploadField {

    private static final String UPLOAD_BUTTON_CLASS = "fileinput-upload-button";
    private static final String AJAX_EVENT_NAME = "fileinput-upload-button-clicked";

    private final AjaxFormSubmitBehavior ajaxBehavior;
    private boolean showCaption = false;
    private boolean showPreview = true;
    private boolean showRemove = false;
    private boolean showUpload = true;

    public BootstrapFileUploadField(final String id, final IModel<List<FileUpload>> model) {
        super(id, model);
        ajaxBehavior = newAjaxFormSubmitBehavior(AJAX_EVENT_NAME);
        add(ajaxBehavior);
    }

    protected AjaxFormSubmitBehavior newAjaxFormSubmitBehavior(final String event) {
        return new AjaxFormSubmitBehavior(event){};
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

    public BootstrapFileUploadField withShowCaption(final boolean showCaption) {
        this.showCaption = showCaption;
        return this;
    }

    public boolean isShowPreview() {
        return showPreview;
    }

    public BootstrapFileUploadField withShowPreview(final boolean showPreview) {
        this.showPreview = showPreview;
        return this;
    }

    public boolean isShowRemove() {
        return showRemove;
    }

    public BootstrapFileUploadField withShowRemove(final boolean showRemove) {
        this.showRemove = showRemove;
        return this;
    }

    public boolean isShowUpload() {
        return showUpload;
    }

    public BootstrapFileUploadField withShowUpload(final boolean showUpload) {
        this.showUpload = showUpload;
        return this;
    }

    private CharSequence createScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("$('#");
        sb.append(Strings2.getMarkupId(this));
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
        for (final String label : new String[] { "browseLabel", "removeLabel", "uploadLabel", "msgLoading",
                "msgProgress", "msgSelected" }) {
            sb.append(", ");
            sb.append(label);
            sb.append(": '");
            sb.append(getString(label));
            sb.append("'");
        }
        sb.append("});\n");

        //bind ajax submit behavior on upload button click
        sb.append("$('.file-input:has(#");
        sb.append(Strings2.getMarkupId(this));
        sb.append(") .");
        sb.append(UPLOAD_BUTTON_CLASS);
        sb.append("').click(function(e){\n");
        sb.append("    e.preventDefault();\n");
        sb.append("    $('#");
        sb.append(Strings2.getMarkupId(this));
        sb.append("').triggerHandler('");
        sb.append(AJAX_EVENT_NAME);
        sb.append("');\n");
        sb.append("});");
        return sb;
    }
}
