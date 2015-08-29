package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.wicket.WicketRuntimeException;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Provides config information for the summernote editor
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    public static final IKey<String> Id = newKey("summernoteEditorId", null);
    public static final IKey<String> Content = newKey("content", null);
    private static final IKey<Boolean> AirMode = newKey("airMode", null);
    private static final IKey<Integer> MaxHeight = newKey("maxHeight", null);
    private static final IKey<Integer> MinHeight = newKey("minHeight", null);
    private static final IKey<Integer> Height = newKey("height", null);
    private static final IKey<Boolean> Force = newKey("force", null);
    private static final IKey<Integer> MaxFileSize = newKey("maxFilesize", 2097152);
    private static final IKey<String> ImageUploadCallbackUrl = newKey("imageUploadUrl", null);

    /**
     * A set of storages used by the *StoredImageResourceReference and the
     * *Editor
     */
    private static Set<SummernoteStorage> storages = new HashSet<SummernoteStorage>();

    /**
     * The storage id of the storage the editor should use
     */
    private String storageId;

    private static final IKey<Map<String, List<String>>> ToolbarOptions = newKey("ToolbarOptions", null);


    private Map<String, List<String>> toolbarOptions = new LinkedHashMap<String, List<String>>() {
        {
            put("Style", Lists.newArrayList("style", "fontname", "fontsize", "color", "bold", "italic", "underline", "strikethrough", "clear"));
            put("Layout", Lists.newArrayList("ul", "ol", "paragraph", "height"));
            put("Insert", Lists.newArrayList("picture", "link", "video", "table", "hr"));
            put("Misc", Lists.newArrayList("fullscreen", "codeview", "undo", "redo", "help"));
        }
    };

    public SummernoteConfig() {
        put(ToolbarOptions, toolbarOptions);
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
