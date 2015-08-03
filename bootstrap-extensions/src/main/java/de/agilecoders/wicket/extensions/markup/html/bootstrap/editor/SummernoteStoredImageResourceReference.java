package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.File;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;

public class SummernoteStoredImageResourceReference extends ResourceReference {

    private static final long serialVersionUID = -6463400105027492640L;

    public static final String SUMMERNOTE_MOUNT_PATH = "/summernoteimages";

    private String subFolderName;

    /**
     * Creates a summer note image resource reference
     * 
     * @param uploadFolder
     *            the upload folder
     */
    public SummernoteStoredImageResourceReference(File uploadFolder) {
	super("summernoteimages");
	SummernoteConfig.setUploadFolder(uploadFolder,null);
    }
    
    /**
     * Creates a summer note image resource reference
     * 
     * @param uploadFolder
     *            the upload folder
     * @param subFolderName
     *            the name of the sub folder within the upload folder
     */
    public SummernoteStoredImageResourceReference(File uploadFolder, String subFolderName) {
	super("summernoteimages");
	SummernoteConfig.setUploadFolder(uploadFolder, subFolderName);
	this.subFolderName = subFolderName;
    }

    @Override
    public IResource getResource() {
	StringValue parameterValue = RequestCycle.get().getRequest().getRequestParameters().getParameterValue("image");
	return new SummernoteStoredImageResource(new File(SummernoteConfig.getUploadFolder(subFolderName),
		parameterValue.toString()));
    }

}
