package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.upload.FileItem;
import org.apache.wicket.util.upload.FileUploadException;

public class SummernoteEditor extends FormComponent<String> {

    private static final long serialVersionUID = 1L;

    private SummernoteConfig config;
    
    private SummernoteEditorImageAjaxEventBehavior summernoteEditorImageAjaxEventBehavior;
    
    private class SummernoteEditorImageAjaxEventBehavior extends AbstractDefaultAjaxBehavior {

        private static final long serialVersionUID = 1L;

        @Override
        protected void respond(AjaxRequestTarget target) {
            try {
                ServletWebRequest webRequest = (ServletWebRequest) getRequest();
                MultipartServletWebRequest multiPartRequest = webRequest.newMultipartWebRequest(
                    Bytes.megabytes(config.getMaxFileSize()), "ignored");
                multiPartRequest.parseFileParts();
                onImageUpload(target, multiPartRequest.getFiles());
            } catch (FileUploadException fux) {
                onImageError(target, fux);
            }
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);
            attributes.setMultipart(true);
        }
    }
    

    public SummernoteEditor(String id) {
	this(id, null, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<String> model) {
	this(id, model, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<String> model, SummernoteConfig config) {
	super(id, model);
	this.config = Args.notNull(config, "config");
	add(summernoteEditorImageAjaxEventBehavior = new SummernoteEditorImageAjaxEventBehavior());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
	response.render(JavaScriptHeaderItem.forReference(SummernoteEditorJavaScriptReference.instance()));
	response.render(CssHeaderItem.forReference(SummernoteEditorCssReference.instance()));
	response.render(CssHeaderItem.forUrl("//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"));
	PackageTextTemplate summernoteTemplate = null;
	try {
	    summernoteTemplate = new PackageTextTemplate(SummernoteEditor.class, "js/summernote_init.js");
	    config.withImageUploadCallbackUrl(summernoteEditorImageAjaxEventBehavior.getCallbackUrl().toString());
	    config.put(SummernoteConfig.Id, getMarkupId());
	    String jsonConfig = config.toJsonString();
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("summernoteconfig", jsonConfig);
	    String summernoteTemplateJavaScript = summernoteTemplate.asString(variables);
	    response.render(OnDomReadyHeaderItem.forScript(summernoteTemplateJavaScript));
	} finally {
	    IOUtils.closeQuietly(summernoteTemplate);
	}
    }
    
    protected void onSubmit(AjaxRequestTarget target){
	// TODO receive text on submit in form component ajax behavior
    }
    
    public void onImageUpload(AjaxRequestTarget target, Map<String, List<FileItem>> files) {
	// NOOP
    }
    
    public void onImageError(AjaxRequestTarget target, FileUploadException fux) {
	// NOOP
    }
}
