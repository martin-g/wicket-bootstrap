package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileUpload;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Configuration for {@link DropZoneFileUpload}
 */
public class DropZoneConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final IKey<String> CallbackUrl = newKey("url", null);
    private static final IKey<String> ParamName = newKey("paramName", "file");
    private static final IKey<Integer> MaxFileSize = newKey("maxFilesize", null);
    private static final IKey<Integer> ParallelUploads = newKey("parallelUploads", null);
    private static final IKey<String> AcceptedFiles = newKey("acceptedFiles", null);
    private static final IKey<Integer> ThumbnailWidth = newKey("thumbnailWidth", null);
    private static final IKey<Integer> ThumbnailHeight = newKey("thumbnailHeight", null);
    private static final IKey<Boolean> AutoQueue = newKey("autoQueue", Boolean.FALSE);
    private static final IKey<String> PreviewsContainer = newKey("previewsContainer", null);
    private static final IKey<String> Clickable = newKey("clickable", ".fileinput-button");

    /**
     * @param callbackUrl
     *            The url to call back
     * @return current instance
     */
    public DropZoneConfig withCallbackUrl(String callbackUrl) {
        put(CallbackUrl, callbackUrl);
        return this;
    }

    /**
     * @param paramName
     *            The name that will be used to transfer the file(s)
     * @return current instance
     */
    public DropZoneConfig withParamName(String paramName) {
        put(ParamName, paramName);
        return this;
    }

    /**
     * @param maxFileSize
     * @return current instance
     */
    public DropZoneConfig withMaxFileSize(int maxFileSize) {
        put(MaxFileSize, maxFileSize);
        return this;
    }

    public int getMaxFileSize() {
    return get(MaxFileSize);
    }

    /**
     * @param parallelUploads
     * @return current instance
     */
    public DropZoneConfig withParallelUploads(int parallelUploads) {
        put(ParallelUploads, parallelUploads);
        return this;
    }

    /**
     * @param acceptedFiles
     * @return current instance
     */
    public DropZoneConfig withAcceptedFiles(String acceptedFiles) {
        put(AcceptedFiles, acceptedFiles);
        return this;
    }

    /**
     * @param thumbnailWidth
     * @return current instance
     */
    public DropZoneConfig withThumbnailWidth(int thumbnailWidth) {
        put(ThumbnailWidth, thumbnailWidth);
        return this;
    }

    /**
     * @param thumbnailHeight
     * @return current instance
     */
    public DropZoneConfig withThumbnailHeight(int thumbnailHeight) {
        put(ThumbnailHeight, thumbnailHeight);
        return this;
    }

    /**
     * @param autoQueue
     * @return current instance
     */
    public DropZoneConfig withAutoQueue(boolean autoQueue) {
        put(AutoQueue, autoQueue);
        return this;
    }

    /**
     * @param previewsContainer
     * @return current instance
     */
    public DropZoneConfig withPreviewsContainer(String previewsContainer) {
        put(PreviewsContainer, previewsContainer);
        return this;
    }

    /**
     * @param clickable
     * @return current instance
     */
    public DropZoneConfig withClickable(String clickable) {
        put(Clickable, clickable);
        return this;
    }
}
