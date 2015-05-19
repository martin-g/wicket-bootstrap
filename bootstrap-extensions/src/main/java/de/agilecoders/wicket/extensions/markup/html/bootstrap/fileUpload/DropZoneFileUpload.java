package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileUpload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.template.PackageTextTemplate;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.References;

/**
 * Provides a modern file upload by using dropzone.js
 *
 * Important: DropZoneFileUpload has to be placed into a bootstrap container!
 *
 * @author Tobias Soloschenko
 */
public abstract class DropZoneFileUpload extends Panel {

    private static final long serialVersionUID = 1L;

    private final DropZoneConfig config;

    private DropZoneFileUploadAjaxEventBehavior dropZoneFileUploadAjaxEventBehavior;

    private class DropZoneFileUploadAjaxEventBehavior extends AbstractDefaultAjaxBehavior {

        private static final long serialVersionUID = 1L;

        @Override
        protected void respond(AjaxRequestTarget target) {
            try {
                ServletWebRequest webRequest = (ServletWebRequest) getRequest();
                MultipartServletWebRequest multiPartRequest = webRequest.newMultipartWebRequest(
                    Bytes.megabytes(config.getMaxFileSize()), "ignored");
                multiPartRequest.parseFileParts();
                onUpload(target, multiPartRequest.getFiles());
            } catch (FileUploadException fux) {
                onError(target, fux);
            }
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);
            attributes.setMultipart(true);
        }
    }

    /**
     * Creates a DropZoneFileUpload with the given id
     *
     * @param id
     *            the wicket id where the component is going to be rendered
     */
    public DropZoneFileUpload(String id) {
        this(id, null, new DropZoneConfig());
    }

    /**
     * Creates a DropZoneFileUpload with the given id
     *
     * @param id
     *            the wicket id where the component is going to be rendered
     * @param model
     *            the model of this component
     */
    public DropZoneFileUpload(String id, IModel<?> model) {
        this(id, model, new DropZoneConfig());
    }

    /**
     * Creates a DropZoneFileUpload with the given id
     *
     * @param id
     *            the wicket id where the component is going to be rendered
     * @param model
     *            the model of this component
     * @param config
     *            the configuration for the widget
     */
    public DropZoneFileUpload(String id, IModel<?> model, DropZoneConfig config) {
        super(id, model);
        this.config = Args.notNull(config, "config");
        add(dropZoneFileUploadAjaxEventBehavior = new DropZoneFileUploadAjaxEventBehavior());
    }

    /**
     * Initializes the drop zone with the required scripts
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        References.renderWithFilter(Bootstrap.getSettings(), response,
            JavaScriptReferenceHeaderItem.forReference(DropZoneFileUploadJavaScriptReference.instance()));

        PackageTextTemplate dropZoneTemplate = null;
        try {
            dropZoneTemplate = new PackageTextTemplate(DropZoneFileUpload.class, "js/dropzone_init.js");
            config.withCallbackUrl(dropZoneFileUploadAjaxEventBehavior.getCallbackUrl().toString());
            String jsonConfig = config.toJsonString();
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("config", jsonConfig);
            String js = dropZoneTemplate.asString(variables);
            response.render(OnDomReadyHeaderItem.forScript(js));
        } finally {
            IOUtils.closeQuietly(dropZoneTemplate);
        }
    }

    /**
     * Gets the default max file size to be used for file uploads
     *
     * @return the max file size in mb
     */
    public final DropZoneConfig getConfig() {
        return config;
    }

    /**
     * Needs to be overridden to handle file upload.<br/>
     * <br/>
     * 
     * @param target 
     * 		  target the target to update components
     *
     * @param fileMap
     *            providing the file information
     */
    protected abstract void onUpload(AjaxRequestTarget target, Map<String, List<FileItem>> fileMap);

    /**
     * Callback method called when an error occurs while parcing the uploaded
     * files<br/>
     * <br/>
     * @param target 
     * 		  target the target to update components
     *
     * @param fux
     *            the thrown exception
     */
    protected void onError(AjaxRequestTarget target, FileUploadException fux) {
    }
}
