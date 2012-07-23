package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * References all available icons inside the icon sprite.
 *
 * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
 */
public enum IconType implements CssClassNameProvider {

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
    private IconType() {
        this.cssClassName = name().toLowerCase();
    }

    /**
     * Constructor.
     *
     * @param cssClassName The css class name of the icon reference
     */
    private IconType(String cssClassName) {
        this.cssClassName = cssClassName.toLowerCase();
    }

    @Override
    public String cssClassName() {
        return "icon-" + cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameAppender() {
        return new CssClassNameAppender(cssClassName());
    }
}