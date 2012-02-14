package de.agilecoders.wicket.ui.bootstrap.image;

import de.agilecoders.wicket.ui.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

import java.util.Arrays;

/**
 * An Icon component displays a non localizable image resource.
 *
 * @author miha
 * @version 1.0
 */
public class Icon extends WebMarkupContainer {

    /**
     * References all available icons inside the icon sprite.
     *
     * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
     */
    public enum Type {
        NULL, Repeat, Glass, Music, Search, Envelope, Star, StarEmpty("star-empty"), Heart, User, Film, Th,
        ThLarge("th-large"), ThList("th-list"), ZoomIn("zoom-in"), ZoomOut("zoom-out"), Ok, PlayCircle("play-circle"),
        Remove, Off, Signal, Cog, Trash, Home, File, Time, Road, Download, DownloadAlt("download-alt"), Upload, Inbox,
        Refresh, ListAlt("list-alt"), Lock, Flag, Headphones, VolumeOff("volume-off"), VolumeDown("volume-down"), VolumeUp("volume-up"),
        QrCode, BarCode, Tag, Tags, Book, Bookmark, Print, Camera, Font, Bold, Italic, List, Picture, FacetimeVideo("facetime-video"),
        IndentLeft("indent-left"), IndentRight("indent-right"), TextHeight("text-height"), TextWidth("text-width"),
        AlignLeft("align-left"), AlignCenter("align-center"), AlignRight("align-right"), AlignJustify("align-justify");

        private final String cssClassName;

        /**
         * Constructor.
         */
        private Type() {
            this.cssClassName = name().toLowerCase();
        }

        /**
         * Constructor.
         *
         * @param cssClassName The css class name of the icon reference
         */
        private Type(String cssClassName) {
            this.cssClassName = cssClassName.toLowerCase();
        }

        /**
         * @return the icon's css class name
         */
        private String cssClassName() {
            return "icon-" + cssClassName;
        }
    }

    private final Type type;
    private boolean invert = false;

    /**
     * @param componentId The non-null id of a new component
     * @param type        The type of the icon, e.g. Search, Home, User,...
     */
    public Icon(final String componentId, final Type type) {
        super(componentId);

        this.type = type;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (type != null && !type.equals(Type.NULL)) {
            final String invertPostfix = isInverted() ? "icon-white" : "";

            add(new CssClassNameAppender(Arrays.asList(type.cssClassName(), invertPostfix)));
        } else {
            setVisible(false);
        }
    }

    /**
     * @return true, if the icon color is inverted
     */
    public boolean isInverted() {
        return this.invert;
    }

    /**
     * marks the icon as inverted.
     *
     * @return the component's current instance
     */
    public Icon invert() {
        this.invert = true;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "i");
    }
}
