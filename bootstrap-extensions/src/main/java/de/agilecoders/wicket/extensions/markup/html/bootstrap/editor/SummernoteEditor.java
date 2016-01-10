package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.crypt.Base64;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.template.PackageTextTemplate;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCDNCSSReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.SpinJsReference;

/**
 * A summer note editor
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteEditor extends FormComponent<String> {

    private static final long serialVersionUID = 1L;

    private static final Pattern NEW_LINE_PATTERN = Pattern.compile("(\\r\\n|\\n|\\r)");

    private final SummernoteConfig config;

    private final SummernoteEditorImageAjaxEventBehavior summernoteEditorImageAjaxEventBehavior;

    public SummernoteEditor(String id) {
        this(id, null, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<String> model) {
        this(id, model, new SummernoteConfig());
    }

    public SummernoteEditor(String id, IModel<String> model, SummernoteConfig config) {
        super(id, model);

        this.setOutputMarkupId(true);
        this.config = Args.notNull(config, "config");
        if (config.isAirMode()) {
            setEscapeModelStrings(false);
        }
        add(summernoteEditorImageAjaxEventBehavior = new SummernoteEditorImageAjaxEventBehavior());
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (config.isAirMode()) {
            tag.setName("div");
        }
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        super.onComponentTagBody(markupStream, openTag);

        if (config.isAirMode()) {
            replaceComponentTagBody(markupStream, openTag, getModelObject());
        }
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(SummernoteEditorCssReference.instance()));
        response.render(CssHeaderItem.forReference(FontAwesomeCDNCSSReference.instance()));
        response.render(CssHeaderItem.forReference(SummernoteEditorOverlayCssReference.instance()));
        response.render(JavaScriptHeaderItem.forReference(SummernoteEditorJavaScriptReference.instance()));
        response.render(JavaScriptHeaderItem.forReference(SummernoteEditorFormDataReference.instance()));
        response.render(JavaScriptHeaderItem.forReference(SpinJsReference.INSTANCE));
        PackageTextTemplate summernoteTemplate = null;
        try {
            summernoteTemplate = new PackageTextTemplate(SummernoteEditor.class, "js/summernote_init.js");
            config.withImageUploadCallbackUrl(summernoteEditorImageAjaxEventBehavior.getCallbackUrl().toString());
            config.put(SummernoteConfig.Id, getMarkupId());

            // Remove picture button if no storage id is provided
            if (config.getStorageId() == null) {
            config.getButtons("Insert").remove("picture");
            }
            String jsonConfig = config.toJsonString();
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("summernoteconfig", jsonConfig);
            String summernoteTemplateJavaScript = summernoteTemplate.asString(variables);
            response.render(OnDomReadyHeaderItem.forScript(summernoteTemplateJavaScript));
        } finally {
            IOUtils.closeQuietly(summernoteTemplate);
        }

        String modelObject = getModelObject();
        if (!config.isAirMode() && !Strings.isEmpty(modelObject)) {
            modelObject = NEW_LINE_PATTERN.matcher(modelObject).replaceAll("<br/>");
            CharSequence safeModelObject = JavaScriptUtils.escapeQuotes(modelObject);
            response.render(OnDomReadyHeaderItem.forScript(String.format("$('#%s').summernote('code', '%s')",
                                                                         getMarkupId(), safeModelObject)));
        }
    }

    /**
     * If an image is going to be inserted into the editor this callback is
     * going to be invoked for each image
     *
     * @param target
     *            the target to update components
     * @param fileItemsMap
     *            a map with the image name and the list of file items (the image)
     */
    protected void onImageUpload(AjaxRequestTarget target, Map<String,FileItem> fileItemsMap) {
        // NOOP
    }

    /**
     * If an error occurred while inserting an image into the editor this
     * callback is going to be invoked for each image which can be uploaded
     *
     * @param target
     *            the target to update components (to give a hint that the
     *            upload failed)
     * @param fux
     *            the exception
     */
    protected void onImageError(AjaxRequestTarget target, FileUploadException fux) {
        // NOOP
    }

    private class SummernoteEditorImageAjaxEventBehavior extends AbstractDefaultAjaxBehavior {

        private static final long serialVersionUID = 1L;

        @Override
        protected void respond(AjaxRequestTarget target) {
            try {
                ServletWebRequest webRequest = (ServletWebRequest) getRequest();
                MultipartServletWebRequest multiPartRequest = webRequest.newMultipartWebRequest(
                    Bytes.megabytes(config.getMaxFileSize()), "ignored");
                multiPartRequest.parseFileParts();
                Map<String,FileItem> fileItemsMap = storeFile(target, multiPartRequest);
                onImageUpload(target, fileItemsMap);
            } catch (FileUploadException fux) {
                onImageError(target, fux);
            }
        }

        /**
         * Stores the submitted files
         *
         * @param target
         *            the target to apply the url to the response
         *
         * @param multiPartRequest
         *            the multi part request to get the files from
         * @throws IOException
         *             if an exception occurred while reading / writing any file
         */
        private Map<String,FileItem> storeFile(AjaxRequestTarget target, MultipartServletWebRequest multiPartRequest) {
            Map<String,FileItem> fileItemsMap = new LinkedHashMap<String, FileItem>();
            Map<String, List<FileItem>> fileMap = multiPartRequest.getFiles();
            Iterator<List<FileItem>> fileItemListIterator = fileMap.values().iterator();
            while (fileItemListIterator.hasNext()) {
                Iterator<FileItem> fileItemIterator = fileItemListIterator.next().iterator();
                while (fileItemIterator.hasNext()) {
                    FileItem fileItem = fileItemIterator.next();
                    String imageName = config.getImageNamePrefix() + fileItem.getName();
                    try {
                        SummernoteStorage storage = SummernoteConfig.getStorage(config.getStorageId());
                        storage.writeContent(imageName, fileItem.getInputStream());
                        WebResponse response = (WebResponse) target.getHeaderResponse().getResponse();
                        response.setHeader("imageUrl", SummernoteStoredImageResourceReference.SUMMERNOTE_MOUNT_PATH
                                                       + "?image=" + Base64.encodeBase64String(imageName.getBytes()));
                        fileItemsMap.put(imageName, fileItem);
                    } catch (IOException e) {
                        throw new WicketRuntimeException("Error while writing image: " + imageName, e);
                    }
                }
            }
            return fileItemsMap;
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);
            attributes.setMultipart(true);
        }
    }

}
