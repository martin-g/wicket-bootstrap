package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

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

    private static final long serialVersionUID = -6463400105027492640L;

    public static final String SUMMERNOTE_MOUNT_PATH = "/summernoteimages";

    private String storageId;

    /**
     * Creates a summer note image resource reference
     * 
     * @param uploadFolder
     *            the upload folder
     * @param subFolderName
     *            the name of the sub folder within the upload folder
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
	StringValue imageName = RequestCycle.get().getRequest().getRequestParameters().getParameterValue("image");
	return new SummernoteStoredImageResource(storageId, imageName.toString());
    }

}
