package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * See <a href="http://plugins.krajee.com/file-input#options">File Input
 * Options</a>
 */
public class FileInputConfig extends AbstractConfig {

    public static final IKey<Boolean> ShowCaption = newKey("showCaption", Boolean.TRUE);

    public static final IKey<Boolean> ShowPreview = newKey("showPreview", Boolean.TRUE);

    public static final IKey<Boolean> ShowRemove = newKey("showRemove", Boolean.TRUE);

    public static final IKey<Boolean> ShowUpload = newKey("showUpload", Boolean.TRUE);

    public static final IKey<String> CaptionClass = newKey("captionClass", null);

    public static final IKey<String> PreviewClass = newKey("previewClass", null);

    public static final IKey<String> MainClass = newKey("mainClass", null);

    public static final IKey<String> InitialCaption = newKey("initialCaption", null);

    public static final IKey<String> BrowseClass = newKey("browseClass", "btn btn-primary");

    public static final IKey<String> RemoveClass = newKey("removeClass", "btn btn-default");

    public static final IKey<String> UploadClass = newKey("uploadClass", "btn btn-default");

    public static final IKey<Integer> WrapTextLength = newKey("wrapTextLength", 250);

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
}
