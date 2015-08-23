package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;

/**
 * Provides the image data by the given storage id
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteStoredImageResourceReference extends ResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String SUMMERNOTE_MOUNT_PATH = "/summernoteimages";

    private String storageId;

    /**
     * Creates a summer note image resource reference
     *
     * @param storageId
     *            the id of the file storage
     */
    public SummernoteStoredImageResourceReference(String storageId) {
        super("summernoteimages");
        this.storageId = storageId;
    }

    /**
     * Gets the resource of an image
     */
    @Override
    public IResource getResource() {
        Request request = RequestCycle.get().getRequest();
        IRequestParameters requestParameters = request.getRequestParameters();
        StringValue imageName = requestParameters.getParameterValue("image");
        return new SummernoteStoredImageResource(storageId, imageName.toString());
    }

}
