package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import java.util.List;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.util.lang.Bytes;

/**
 * See <a href="http://plugins.krajee.com/file-input#options">File Input
 * Options</a>
 */
public class FileInputConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    public static final IKey<Boolean> ShowCaption = newKey("showCaption", Boolean.TRUE);

    public static final IKey<Boolean> ShowPreview = newKey("showPreview", Boolean.TRUE);

    public static final IKey<Boolean> ShowRemove = newKey("showRemove", Boolean.TRUE);

    public static final IKey<Boolean> ShowUpload = newKey("showUpload", Boolean.TRUE);

    public static final IKey<String> CaptionClass = newKey("captionClass", null);

    public static final IKey<String> PreviewClass = newKey("previewClass", null);

    public static final IKey<String> MainClass = newKey("mainClass", null);

    public static final IKey<String> InitialCaption = newKey("initialCaption", null);

    public static final IKey<String> BrowseClass = newKey("browseClass", "btn btn-primary");

    public static final IKey<String> RemoveClass = newKey("removeClass", "btn btn-secondary");

    public static final IKey<String> UploadClass = newKey("uploadClass", "btn btn-secondary");

    public static final IKey<Integer> WrapTextLength = newKey("wrapTextLength", 250);

    public static final IKey<Integer> MaxFileCount = newKey("maxFileCount", 0);

    public static final IKey<Integer> MinFileCount = newKey("minFileCount", 0);

    public static final IKey<Integer> MaxFileSize = newKey("maxFileSize", 0);

    public static final IKey<Integer> MinFileSize = newKey("minFileSize", 0);

    public static final IKey<String> BrowseIcon = newKey("browseIcon", "<i class=\"glyphicon glyphicon-folder-open\"></i> &nbsp;");

    public static final IKey<String> RemoveIcon = newKey("removeIcon", "<i class=\"glyphicon glyphicon-trash\"></i> &nbsp;");

    public static final IKey<String> UploadIcon = newKey("uploadIcon", "<i class=\"glyphicon glyphicon-upload\"></i> &nbsp;");

    public static final IKey<String> CancelIcon = newKey("cancelIcon", "<i class=\"glyphicon glyphicon-ban-circle\"></i> &nbsp;");

    public static final IKey<String> PreviewFileType = newKey("previewFileType", "image");

    public static final IKey<List<String>> AllowedFileExtensions = newKey("allowedFileExtensions", null);

    public static final IKey<List<String>> AllowedFileTypes = newKey("allowedFileTypes", null);

    public static final IKey<String> Language = newKey("language", null);

    public static final IKey<Boolean> DropZoneEnabled = newKey("dropZoneEnabled", null);

    public static final IKey<String> DropZoneTitle = newKey("dropZoneTitle", null);

    public static final IKey<String> DropZoneTitleClass = newKey("dropZoneTitleClass", null);

    public static final IKey<String> DropZoneClickTitle = newKey("dropZoneClickTitle", null);


    public FileInputConfig showCaption(boolean showCaption) {
        put(ShowCaption, showCaption);
        return this;
    }

    public FileInputConfig showPreview(boolean showPreview) {
        put(ShowPreview, showPreview);
        return this;
    }

    public FileInputConfig showRemove(boolean showRemove) {
        put(ShowRemove, showRemove);
        return this;
    }

    public FileInputConfig showUpload(boolean showUpload) {
        put(ShowUpload, showUpload);
        return this;
    }

    public FileInputConfig captionClass(String captionClass) {
        put(CaptionClass, captionClass);
        return this;
    }

    public FileInputConfig mainClass(String mainClass) {
        put(MainClass, mainClass);
        return this;
    }

    public FileInputConfig initialCaption(String initialCaption) {
        put(InitialCaption, initialCaption);
        return this;
    }

    public FileInputConfig browseClass(String browseClass) {
        put(BrowseClass, browseClass);
        return this;
    }

    public FileInputConfig previewClass(String previewClass) {
        put(PreviewClass, previewClass);
        return this;
    }

    public FileInputConfig removeClass(String removeClass) {
        put(RemoveClass, removeClass);
        return this;
    }

    public FileInputConfig uploadClass(String uploadClass) {
        if (!uploadClass.contains(BootstrapFileInputField.JQUERY_IDENTIFIER_UPLOAD_BUTTON_CLASS)) {
            uploadClass = uploadClass + " " + BootstrapFileInputField.JQUERY_IDENTIFIER_UPLOAD_BUTTON_CLASS;
        }
        put(UploadClass, uploadClass);
        return this;
    }

    public FileInputConfig wrapTextLength(int wrapTextLength) {
        put(WrapTextLength, wrapTextLength);
        return this;
    }

    public FileInputConfig minFileCount(int minFileCount) {
        put(MinFileCount, minFileCount);
        return this;
    }

    public FileInputConfig maxFileCount(int maxFileCount) {
        put(MaxFileCount, maxFileCount);
        return this;
    }

    /** Maximum file size for upload - or no restriction if 0 (default) */
    public FileInputConfig maxFileSize(Bytes maxFileSize) {
        put(MaxFileSize, maxFileSize != null ? (int) Math.round(maxFileSize.kilobytes()) : 0);
        return this;
    }

    /** Minimum file size for upload - or no restriction if 0 (default) */
    public FileInputConfig minFileSize(Bytes minFileSize) {
        put(MinFileSize, minFileSize != null ? (int) Math.round(minFileSize.kilobytes()) : 0);
        return this;
    }

    public FileInputConfig browseIcon(String browseIcon) {
        put(BrowseIcon, browseIcon);
        return this;
    }

    public FileInputConfig cancelIcon(String cancelIcon) {
        put(CancelIcon, cancelIcon);
        return this;
    }

    public FileInputConfig removeIcon(String removeIcon) {
        put(RemoveIcon, removeIcon);
        return this;
    }

    public FileInputConfig uploadIcon(String uploadIcon) {
        put(UploadIcon, uploadIcon);
        return this;
    }

    public FileInputConfig previewFileType(String previewFileType) {
        put(PreviewFileType, previewFileType);
        return this;
    }

    public FileInputConfig allowedFileExtensions(List<String> allowedFileExtensions) {
        put(AllowedFileExtensions, allowedFileExtensions);
        return this;
    }

    public FileInputConfig allowedFileTypes(List<String> allowedFileTypes) {
        put(AllowedFileTypes, allowedFileTypes);
        return this;
    }

    /**
     * Sets fileinput language.
     * Check res/js/locales folder for available locales
     */
    public FileInputConfig withLocale(String language) {
    	put(Language, language);
    	return this;
    }

    public FileInputConfig withDropZoneEnabled(boolean dropZoneEnabled) {
    	put(DropZoneEnabled, dropZoneEnabled);
    	return this;
    }

    public FileInputConfig withDropZoneTitle(String dropZoneTitle) {
    	put(DropZoneTitle, dropZoneTitle);
    	return this;
    }

    public FileInputConfig withDropZoneTitleClass(String dropZoneTitleClass) {
    	put(DropZoneTitleClass, dropZoneTitleClass);
    	return this;
    }

    public FileInputConfig withDropZoneClickTitle(String dropZoneClickTitle) {
    	put(DropZoneClickTitle, dropZoneClickTitle);
    	return this;
    }

    public List<String> allowedFileTypes() {
        return get(AllowedFileTypes);
    }

    public List<String> allowedFileExtensions() {
        return get(AllowedFileExtensions);
    }

    public boolean showCaption() {
        return get(ShowCaption);
    }

    public boolean showPreview() {
        return get(ShowPreview);
    }

    public boolean showRemove() {
        return get(ShowRemove);
    }

    public boolean showUpload() {
        return get(ShowUpload);
    }

    public String captionClass() {
        return getString(CaptionClass);
    }

    public String previewClass() {
        return getString(PreviewClass);
    }

    public String mainClass() {
        return getString(MainClass);
    }

    public String initialCaption() {
        return getString(InitialCaption);
    }

    public String browseClass() {
        return getString(BrowseClass);
    }

    public String removeClass() {
        return getString(RemoveClass);
    }

    public String uploadClass() {
        return getString(UploadClass);
    }

    public int wrapTextLength() {
        return get(WrapTextLength);
    }

    public int maxFileCount() {
        return get(MaxFileCount);
    }

    public int minFileCount() {
        return get(MinFileCount);
    }

    public Bytes maxFileSize() {
        return Bytes.kilobytes(get(MaxFileSize));
    }

    public Bytes minFileSize() {
        return Bytes.kilobytes(get(MinFileSize));
    }

    public String browseIcon() {
        return get(BrowseIcon);
    }

    public String removeIcon() {
        return get(RemoveIcon);
    }

    public String uploadIcon() {
        return get(UploadIcon);
    }

    public String cancelIcon() {
        return get(CancelIcon);
    }

    public String previewFileType() {
        return get(PreviewFileType);
    }

    public String language() {
    	return get(Language);
    }

    public boolean dropZoneEnabled(){
    	return get(DropZoneEnabled);
    }

    public String dropZoneTitle(){
    	return get(DropZoneTitle);
    }

    public String dropZoneTitleClass(){
    	return get(DropZoneTitleClass);
    }

    public String dropZoneClickTitle(){
    	return get(DropZoneClickTitle);
    }

}
