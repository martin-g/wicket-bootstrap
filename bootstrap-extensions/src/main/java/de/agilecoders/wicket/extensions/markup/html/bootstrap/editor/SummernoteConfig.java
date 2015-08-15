package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.WicketRuntimeException;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

public class SummernoteConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    public static final IKey<String> Id = newKey("summernoteEditorId", null);
    private static final IKey<Boolean> AirMode = newKey("airMode", null);
    private static final IKey<Integer> MaxHeight = newKey("maxHeight", null);
    private static final IKey<Integer> MinHeight = newKey("minHeight", null);
    private static final IKey<Integer> Height = newKey("height", null);
    private static final IKey<Boolean> Force = newKey("force", null);
    private static final IKey<Integer> MaxFileSize = newKey("maxFilesize", 2097152);
    private static final IKey<String> ImageUploadCallbackUrl = newKey("imageUploadUrl", null);

    private static Set<SummernoteStorage> storages = new HashSet<SummernoteStorage>();

    private String storageId;

    /**
     * @param airmode
     *            if air mode should be used or normal
     * @return current instance
     */
    public SummernoteConfig withAirMode(boolean airMode) {
	put(AirMode, airMode);
	return this;
    }

    /**
     * @param maxHeight
     *            the max height of the editor
     * @return current instance
     */
    public SummernoteConfig withMaxHeight(Integer maxHeight) {
	put(MaxHeight, maxHeight);
	return this;
    }

    /**
     * @param minHeight
     *            the min height of the editor
     * @return current instance
     */
    public SummernoteConfig withMinHeight(Integer minHeight) {
	put(MinHeight, minHeight);
	return this;
    }

    /**
     * @param height
     *            the height of the editor
     * @return current instance
     */
    public SummernoteConfig withHeight(Integer height) {
	put(Height, height);
	return this;
    }

    /**
     * @param force
     *            if the editor should gain the focus
     * @return current instance
     */
    public SummernoteConfig force(boolean force) {
	put(Force, force);
	return this;
    }

    /**
     * @param maxFileSize
     * @return current instance
     */
    public SummernoteConfig withMaxFileSize(int maxFileSize) {
	put(MaxFileSize, maxFileSize);
	return this;
    }

    /**
     * Gets the max file size
     * 
     * @return the max file size
     */
    public int getMaxFileSize() {
	return get(MaxFileSize);
    }

    /**
     * @param callbackUrl
     *            The image upload url to call back
     * @return current instance
     */
    public SummernoteConfig withImageUploadCallbackUrl(String callbackUrl) {
	put(ImageUploadCallbackUrl, callbackUrl);
	return this;
    }

    /**
     * Gets the storage
     * 
     * @param storageId
     *            the storage id;
     * @return the storage
     */
    public static SummernoteStorage getStorage(String storageId) {
	for (SummernoteStorage storage : storages) {
	    if (storage.getId().equals(storageId)) {
		return storage;
	    }
	}
	throw new WicketRuntimeException("Please ensure that you provided a storage by the id " + storageId);
    }

    /**
     * Adds a summer note storage
     * 
     * @param storage
     *            the storage to be added
     */
    public static void addStorage(SummernoteStorage storage) {
	storages.add(storage);
    }

    /**
     * Gets the storage id
     * 
     * @return the storage id
     */
    public String getStorageId() {
	return storageId;
    }

    /**
     * Tells the summernote editor which storage should be used
     * 
     * @param storageId
     *            the storage id to be used
     */
    public void useStorageId(String storageId) {
	this.storageId = storageId;
    }
}
