package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import org.apache.wicket.request.resource.DynamicImageResource;

/**
 * Gets the stored image resource by reading it from the storage with the given
 * id and the given image name
 * 
 * @author Tobias Soloschenko
 *
 */
public class SummernoteStoredImageResource extends DynamicImageResource {

    private static final long serialVersionUID = 1L;

    private String imageName;

    private String storageId;

    /**
     * Creates a summernote stored image resource
     * 
     * @param storageId
     *            the storage id to get the image from
     * @param imageName
     *            the name of the image
     */
    public SummernoteStoredImageResource(String storageId, String imageName) {
	this.imageName = imageName;
	this.storageId = storageId;
    }

    /**
     * Gets the image data
     */
    @Override
    protected byte[] getImageData(Attributes attributes) {
	return SummernoteConfig.getStorage(storageId).getContent(imageName);
    }
}
