package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

/**
 * References all available icons inside the icon sprite.
 *
 * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
 */
public class IconType implements CssClassNameProvider {

    public static final IconType NULL = new IconType("null");
    public static final IconType Repeat = new IconType("repeat");
    public static final IconType Glass = new IconType("glass");
    public static final IconType Music = new IconType("music");
    public static final IconType Search = new IconType("search");
    public static final IconType Envelope = new IconType("envelope");
    public static final IconType Star = new IconType("star");
    public static final IconType StarEmpty = new IconType("star-empty");
    public static final IconType Heart = new IconType("heart");
    public static final IconType User = new IconType("user");
    public static final IconType Film = new IconType("film");
    public static final IconType Th = new IconType("th");
    public static final IconType ThLarge = new IconType("th-large");
    public static final IconType ThList = new IconType("th-list");
    public static final IconType ZoomIn = new IconType("zoom-in");
    public static final IconType ZoomOut = new IconType("zoom-out");
    public static final IconType Ok = new IconType("ok");
    public static final IconType PlayCircle = new IconType("play-circle");
    public static final IconType Remove = new IconType("remove");
    public static final IconType Off = new IconType("off");
    public static final IconType Signal = new IconType("signal");
    public static final IconType Cog = new IconType("cog");
    public static final IconType Trash = new IconType("trash");
    public static final IconType Home = new IconType("home");
    public static final IconType File = new IconType("file");
    public static final IconType Time = new IconType("time");
    public static final IconType Road = new IconType("road");
    public static final IconType Download = new IconType("download");
    public static final IconType DownloadAlt = new IconType("download-alt");
    public static final IconType Upload = new IconType("upload");
    public static final IconType Inbox = new IconType("inbox");
    public static final IconType Refresh = new IconType("refresh");
    public static final IconType ListAlt = new IconType("list-alt");
    public static final IconType Lock = new IconType("lock");
    public static final IconType Flag = new IconType("flag");
    public static final IconType Headphones = new IconType("headphones");
    public static final IconType VolumeOff = new IconType("volume-off");
    public static final IconType VolumeDown = new IconType("volume-down");
    public static final IconType VolumeUp = new IconType("volume-up");
    public static final IconType QrCode = new IconType("or-code");
    public static final IconType BarCode = new IconType("bar-code");
    public static final IconType Tag = new IconType("tag");
    public static final IconType Tags = new IconType("tags");
    public static final IconType Book = new IconType("book");
    public static final IconType Bookmark = new IconType("bookmark");
    public static final IconType Print = new IconType("print");
    public static final IconType Camera = new IconType("camera");
    public static final IconType Font = new IconType("font");
    public static final IconType Bold = new IconType("bold");
    public static final IconType Italic = new IconType("italic");
    public static final IconType List = new IconType("list");
    public static final IconType Picture = new IconType("picture");
    public static final IconType FacetimeVideo = new IconType("facetime-video");
    public static final IconType IndentLeft = new IconType("indent-left");
    public static final IconType IndentRight = new IconType("indent-right");
    public static final IconType TextHeight = new IconType("text-height");
    public static final IconType TextWidth = new IconType("text-width");
    public static final IconType AlignLeft = new IconType("align-left");
    public static final IconType AlignCenter = new IconType("align-center");
    public static final IconType AlignRight = new IconType("align-right");
    public static final IconType AlignJustify = new IconType("align-justify");

    private final String cssClassName;

    /**
     * Constructor.
     *
     * @param cssClassName The css class name of the icon reference
     */
    public IconType(String cssClassName) {
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