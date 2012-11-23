package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;

import java.io.Serializable;

/**
 * References all available icons inside the icon sprite.
 *
 * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
 */
public enum IconType implements CssClassNameProvider, Serializable {
    NULL("icon-null"),
    Repeat("icon-repeat"),
    Glass("icon-glass"),
    Music("icon-music"),
    Search("icon-search"),
    Envelope("icon-envelope"),
    Star("icon-star"),
    StarEmpty("icon-star-empty"),
    Heart("icon-heart"),
    User("icon-user"),
    Film("icon-film"),
    Th("icon-th"),
    ThLarge("icon-th-large"),
    ThList("icon-th-list"),
    ZoomIn("icon-zoom-in"),
    ZoomOut("icon-zoom-out"),
    Ok("icon-ok"),
    PlayCircle("icon-play-circle"),
    Remove("icon-remove"),
    Off("icon-off"),
    Signal("icon-signal"),
    Cog("icon-cog"),
    Trash("icon-trash"),
    Home("icon-home"),
    File("icon-file"),
    Time("icon-time"),
    Road("icon-road"),
    Download("icon-download"),
    DownloadAlt("icon-download-alt"),
    Upload("icon-upload"),
    Inbox("icon-inbox"),
    Refresh("icon-refresh"),
    ListAlt("icon-list-alt"),
    Lock("icon-lock"),
    Flag("icon-flag"),
    Headphones("icon-headphones"),
    VolumeOff("icon-volume-off"),
    VolumeDown("icon-volume-down"),
    VolumeUp("icon-volume-up"),
    QrCode("icon-or-code"),
    BarCode("icon-bar-code"),
    Tag("icon-tag"),
    Tags("icon-tags"),
    Book("icon-book"),
    Bookmark("icon-bookmark"),
    Print("icon-print"),
    Camera("icon-camera"),
    Font("icon-font"),
    Bold("icon-bold"),
    Italic("icon-italic"),
    List("icon-list"),
    Picture("icon-picture"),
    FacetimeVideo("icon-facetime-video"),
    IndentLeft("icon-indent-left"),
    IndentRight("icon-indent-right"),
    TextHeight("icon-text-height"),
    TextWidth("icon-text-width"),
    AlignLeft("icon-align-left"),
    AlignCenter("icon-align-center"),
    AlignRight("icon-align-right"),
    AlignJustify("icon-align-justify");

    private final String cssClassName;

    IconType(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    @Override
    public String cssClassName() {
        return cssClassName;
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName);
    }
}