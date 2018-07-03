package de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.fileUpload.DropZoneConfig;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * Provides config information for the markdown editor
 *
 * @see <a href="http://www.codingdrama.com/bootstrap-markdown/">Bootstrap Markdown</a>
 */
public class MarkdownConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    public enum Resize {
        none,
        both,
        horizontal,
        vertical
    }

    public enum IconLibrary {
        glyph,
        fa
    }

    static final IKey<Boolean> Autofocus = newKey("autofocus", false);
    static final IKey<Boolean> Hideable = newKey("hideable", false);
    static final IKey<String> Width = newKey("width", null);
    static final IKey<String> Height = newKey("height", null);
    static final IKey<Resize> _Resize = newKey("resize", Resize.none);
    static final IKey<IconLibrary> _IconLibrary = newKey("iconlibrary", IconLibrary.glyph);
    static final IKey<DropZoneConfig> DropZoneOptions = newKey("dropZoneOptions", null);

    public MarkdownConfig() {
        put(_IconLibrary, IconLibrary.fa);
    }

    /**
     * @param autofocus
     *            Indicates that editor will focused after instantiated. Default to false.
     * @return current instance
     */
    public MarkdownConfig withAutofocus(boolean autofocus) {
        put(Autofocus, autofocus);
        return this;
    }

    /**
     * @param hideable
     *            If set to true then the editor will be hidden on blur event. Default to false.
     * @return current instance
     */
    public MarkdownConfig withHideable(boolean hideable) {
        put(Hideable, hideable);
        return this;
    }

    /**
     * @param width
     *            The editor width. Default to inherit. You could supply any CSS class (custom or Bootstrap one).
     * @return current instance
     */
    public MarkdownConfig withWidth(String width) {
        put(Width, width);
        return this;
    }

    /**
     * @param height
     *            The editor height. Default to <em>inherit</em>.
     * @return current instance
     */
    public MarkdownConfig withHeight(String height) {
        put(Height, height);
        return this;
    }

    /**
     * @param resize
     *            Option to disable or change the resize property, possible values none,both,horizontal,vertical. Default {@link Resize#none}.
     *            If this option is enabled, the user will be able to resize the editor and preview screen.
     * @return current instance
     */
    public MarkdownConfig withResize(Resize resize) {
        put(_Resize, resize);
        return this;
    }

    /**
     * @param iconLibrary
     *            The icon library to use. Glyphicons ({@link IconLibrary#glyph}) and Font Awesome ({@link IconLibrary#fa}) are supported.
     *            In order to use Font Awesome properly, you'll need to include Font Awesome stylesheet yourself. Default to {@link IconLibrary#glyph}
     * @return current instance
     */
    public MarkdownConfig withIconLibrary(IconLibrary iconLibrary) {
        put(_IconLibrary, iconLibrary);
        return this;
    }

    /**
     * @param dropZoneOptions
     *            Enables integration with DropZone for allowing file upload/linking via drag&drop.
     *            The options are directly passed to the DropZone library. Valid options are described {@link DropZoneConfig here}.
     * @return current instance
     */
    public MarkdownConfig withDropZoneOptions(DropZoneConfig dropZoneOptions) {
        put(DropZoneOptions, dropZoneOptions);
        return this;
    }
}
