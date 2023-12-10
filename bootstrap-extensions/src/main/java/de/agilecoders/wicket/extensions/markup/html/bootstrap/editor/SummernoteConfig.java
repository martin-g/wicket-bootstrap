package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.wicket.WicketRuntimeException;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.util.lang.Bytes;
import java.time.Duration;

/**
 * Provides config information for the summernote editor
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_OVERLAY_TIMEOUT = (int)Duration.ofSeconds(2L).toMillis();
    private static final int DEFAULT_MAX_SIZE = (int)Bytes.megabytes(2).bytes();

    public static final IKey<String> Id = newKey("summernoteEditorId", null);
    private static final IKey<Boolean> AirMode = newKey("airMode", null);
    private static final IKey<Integer> MaxHeight = newKey("maxHeight", null);
    private static final IKey<Integer> MinHeight = newKey("minHeight", null);
    private static final IKey<Integer> Height = newKey("height", null);
    private static final IKey<Boolean> Force = newKey("force", null);
    private static final IKey<Boolean> DisableDragDrop = newKey("disableDragAndDrop", false);
    private static final IKey<Boolean> Shortcuts = newKey("shortcuts", true);
    private static final IKey<Integer> MaxFileSize = newKey("maxFilesize", null);
    private static final IKey<String> ImageUploadCallbackUrl = newKey("imageUploadUrl", null);
    private static final IKey<String> Placeholder = newKey("placeholder", null);
    private static final IKey<Integer> OverlayTimeout = newKey("overlayTimeout", null);

    /**
     * A set of storages used by the *StoredImageResourceReference and the
     * *Editor
     */
    private static Set<SummernoteStorage> storages = new HashSet<>();

    /**
     * The storage id of the storage the editor should use
     */
    private String storageId;

    private static final IKey<Map<String, List<String>>> ToolbarOptions = newKey("ToolbarOptions", null);


    private Map<String, List<String>> toolbarOptions = new LinkedHashMap<>() {
        private static final long serialVersionUID = 1L;

        {
            put("Style", Arrays.asList("style", "fontname", "fontsize", "color", "bold", "italic", "underline", "strikethrough", "clear"));
            put("Layout", Arrays.asList("ul", "ol", "paragraph", "height"));
            put("Insert", Arrays.asList("picture", "link", "video", "table", "hr"));
            put("Misc", Arrays.asList("fullscreen", "codeview", "undo", "redo", "help"));
        }
    };

    public SummernoteConfig() {
        put(ToolbarOptions, toolbarOptions);
        put(OverlayTimeout, DEFAULT_OVERLAY_TIMEOUT);
        put(MaxFileSize, DEFAULT_MAX_SIZE);
    }

    /**
     * @param airMode
     *            if air mode should be used or normal
     * @return current instance
     */
    public SummernoteConfig withAirMode(boolean airMode) {
        put(AirMode, airMode);
        return this;
    }

    public boolean isAirMode() {
        return get(AirMode);
    }

    /**
     * @param disableDragAndDrop
     *            A flag to disable/enable the drag and drop support
     * @return current instance
     */
    public SummernoteConfig withDisableDragAndDrop(boolean disableDragAndDrop) {
        put(DisableDragDrop, disableDragAndDrop);
        return this;
    }

    /**
     * @param shortcuts
     *            A flag to disable/enable the support for shortcuts
     * @return current instance
     */
    public SummernoteConfig withShortcuts(boolean shortcuts) {
        put(Shortcuts, shortcuts);
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
     * @param overlayTimeout
     *            the timeout until the overlay is shown
     * @return current instance
     */
    public SummernoteConfig withOverlayTimeout(Integer overlayTimeout) {
        put(OverlayTimeout, overlayTimeout);
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
     * @param placeholder
     *            The placeholder text in the empty editor
     * @return current instance
     */
    public SummernoteConfig withPlaceholder(String placeholder) {
        put(Placeholder, placeholder);
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
        throw new WicketRuntimeException("Cannot find a storage with id: " + storageId + ". Use #addStorage(SummernoteStorage) to setup it.");
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

    /**
     * Gets a list of button ids of the given category
     *
     * @return a list of button ids
     */
    public List<String> getButtons(String category) {
        return toolbarOptions.get(category);
    }

    /**
     * Adds buttons to the toolbar
     *
     * @param category
     *            the button category
     * @param buttonIds
     *            the ids of the buttons
     */
    public void addButtons(String category, List<String> buttonIds) {
        toolbarOptions.put(category, buttonIds);
    }

    /**
     * Removes buttons of a given category
     *
     * @param category
     *            the category to remove buttons
     */
    public void removeButtons(String category) {
        toolbarOptions.remove(category);
    }

    /**
     * Override this method to provide a customized prefix images should be
     * stored.
     *
     * @return a prefix for the image names to be stored
     */
    public String getImageNamePrefix() {
        return UUID.randomUUID().toString();
    }
}
