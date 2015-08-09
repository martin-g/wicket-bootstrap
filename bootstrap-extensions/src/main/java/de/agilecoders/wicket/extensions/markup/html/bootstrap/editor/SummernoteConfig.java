package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.lang.Args;

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

    private static Map<String, File> uploadFolders = new HashMap<String, File>();

    private String subFolderName;

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
     * Gets the upload folder images are going to be stored
     * 
     * @param subFolderName
     *            the name of the sub folder within the upload folder
     * 
     * @return the upload folder
     */
    public static File getUploadFolder(String subFolderName) {
	File uploadFolder = SummernoteConfig.uploadFolders.get(subFolderName);
	if (uploadFolder == null) {
	    throw new WicketRuntimeException("The upload folder has not been set!");
	}
	return new File(uploadFolder, subFolderName == null ? "" : subFolderName);
    }

    /**
     * Sets the upload folder images are going to be stored
     * 
     * @param uploadFolder
     *            the upload folder
     * @param subFolderName
     *            the name of the sub folder within the upload folder
     */
    public static void setUploadFolder(File uploadFolder, String subFolderName) {
	uploadFolder = Args.notNull(uploadFolder, "uploadFolder");
	if (!uploadFolder.isDirectory()) {
	    throw new WicketRuntimeException("The given file is not a folder " + uploadFolder);
	}
	File subFolder = new File(uploadFolder, subFolderName == null ? "" : subFolderName);
	if (!subFolder.exists()) {
	    boolean created = subFolder.mkdirs();
	    if (!created) {
		throw new WicketRuntimeException("folder structure couldn't be created " + subFolder.getPath());
	    }
	}
	uploadFolders.put(subFolderName, uploadFolder);
    }

    /**
     * Gets the name of the sub folder
     * 
     * @return the name of the sub folder
     */
    public String getSubFolderName() {
	return subFolderName;
    }

    /**
     * Sets the name of the sub folder
     * 
     * @param subFolderName
     *            the name of the sub folder
     */
    public void setSubFolderName(String subFolderName) {
	subFolderName = Args.notNull(subFolderName, "uploadFolder");
	this.subFolderName = subFolderName;
    }
}
