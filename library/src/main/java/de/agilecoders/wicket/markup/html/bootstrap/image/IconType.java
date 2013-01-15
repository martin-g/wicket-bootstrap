package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * References all available icons inside the icon sprite.
 *
 * @see {http://twitter.github.com/bootstrap/base-css.html#buttons}
 */
public class IconType implements ICssClassNameProvider, ICssClassNameModifier {

    public static final IconType NULL = new IconType("null");

    public static final IconType search = new IconType("search");
    public static final IconType envelope = new IconType("envelope");
    public static final IconType heart = new IconType("heart");
    public static final IconType star = new IconType("star");
    public static final IconType starempty = new IconType("star-empty");
    public static final IconType user = new IconType("user");
    public static final IconType film = new IconType("film");
    public static final IconType thlarge = new IconType("th-large");
    public static final IconType th = new IconType("th");
    public static final IconType thlist = new IconType("th-list");
    public static final IconType ok = new IconType("ok");
    public static final IconType remove = new IconType("remove");
    public static final IconType zoomin = new IconType("zoom-in");
    public static final IconType zoomout = new IconType("zoom-out");
    public static final IconType off = new IconType("off");
    public static final IconType signal = new IconType("signal");
    public static final IconType cog = new IconType("cog");
    public static final IconType trash = new IconType("trash");
    public static final IconType home = new IconType("home");
    public static final IconType file = new IconType("file");
    public static final IconType time = new IconType("time");
    public static final IconType road = new IconType("road");
    public static final IconType downloadalt = new IconType("download-alt");
    public static final IconType download = new IconType("download");
    public static final IconType upload = new IconType("upload");
    public static final IconType inbox = new IconType("inbox");
    public static final IconType playcircle = new IconType("play-circle");
    public static final IconType repeat = new IconType("repeat");
    public static final IconType refresh = new IconType("refresh");
    public static final IconType listalt = new IconType("list-alt");
    public static final IconType lock = new IconType("lock");
    public static final IconType flag = new IconType("flag");
    public static final IconType headphones = new IconType("headphones");
    public static final IconType volumeoff = new IconType("volume-off");
    public static final IconType volumedown = new IconType("volume-down");
    public static final IconType volumeup = new IconType("volume-up");
    public static final IconType qrcode = new IconType("qrcode");
    public static final IconType barcode = new IconType("barcode");
    public static final IconType tag = new IconType("tag");
    public static final IconType tags = new IconType("tags");
    public static final IconType book = new IconType("book");
    public static final IconType bookmark = new IconType("bookmark");
    public static final IconType print = new IconType("print");
    public static final IconType camera = new IconType("camera");
    public static final IconType font = new IconType("font");
    public static final IconType bold = new IconType("bold");
    public static final IconType italic = new IconType("italic");
    public static final IconType textheight = new IconType("textheight");
    public static final IconType textwidth = new IconType("text-width");
    public static final IconType alignleft = new IconType("align-left");
    public static final IconType aligncenter = new IconType("align-center");
    public static final IconType alignright = new IconType("align-right");
    public static final IconType alignjustify = new IconType("align-justify");
    public static final IconType list = new IconType("list");
    public static final IconType indentleft = new IconType("indent-left");
    public static final IconType indentright = new IconType("indent-right");
    public static final IconType facetimevideo = new IconType("facetime-video");
    public static final IconType picture = new IconType("picture");
    public static final IconType pencil = new IconType("pencil");
    public static final IconType mapmarker = new IconType("map-marker");
    public static final IconType adjust = new IconType("adjust");
    public static final IconType tint = new IconType("tint");
    public static final IconType edit = new IconType("edit");
    public static final IconType share = new IconType("share");
    public static final IconType check = new IconType("check");
    public static final IconType move = new IconType("move");
    public static final IconType stepbackward = new IconType("step-backward");
    public static final IconType fastbackward = new IconType("fast-backward");
    public static final IconType backward = new IconType("backward");
    public static final IconType play = new IconType("play");
    public static final IconType pause = new IconType("pause");
    public static final IconType stop = new IconType("stop");
    public static final IconType forward = new IconType("forward");
    public static final IconType fastforward = new IconType("fast-forward");
    public static final IconType stepforward = new IconType("step-forward");
    public static final IconType eject = new IconType("eject");
    public static final IconType chevronleft = new IconType("chevron-left");
    public static final IconType chevronright = new IconType("chevron-right");
    public static final IconType plussign = new IconType("plus-sign");
    public static final IconType minussign = new IconType("minus-sign");
    public static final IconType removesign = new IconType("remove-sign");
    public static final IconType oksign = new IconType("ok-sign");
    public static final IconType questionsign = new IconType("question-sign");
    public static final IconType infosign = new IconType("info-sign");
    public static final IconType screenshot = new IconType("screenshot");
    public static final IconType removecircle = new IconType("remove-circle");
    public static final IconType okcircle = new IconType("ok-circle");
    public static final IconType bancircle = new IconType("ban-circle");
    public static final IconType arrowleft = new IconType("arrow-left");
    public static final IconType arrowright = new IconType("arrow-right");
    public static final IconType arrowup = new IconType("arrow-up");
    public static final IconType arrowdown = new IconType("arrow-down");
    public static final IconType sharealt = new IconType("share-alt");
    public static final IconType resizefull = new IconType("resize-full");
    public static final IconType resizesmall = new IconType("resize-small");
    public static final IconType plus = new IconType("plus");
    public static final IconType minus = new IconType("minus");
    public static final IconType asterisk = new IconType("asterisk");
    public static final IconType exclamationsign = new IconType("exclamation-sign");
    public static final IconType gift = new IconType("gift");
    public static final IconType leaf = new IconType("leaf");
    public static final IconType fire = new IconType("fire");
    public static final IconType eyeopen = new IconType("eye-open");
    public static final IconType eyeclose = new IconType("eye-close");
    public static final IconType warningsign = new IconType("warning-sign");
    public static final IconType plane = new IconType("plane");
    public static final IconType calendar = new IconType("calendar");
    public static final IconType random = new IconType("random");
    public static final IconType comment = new IconType("comment");
    public static final IconType magnet = new IconType("magnet");
    public static final IconType chevronup = new IconType("chevron-up");
    public static final IconType chevrondown = new IconType("chevron-down");
    public static final IconType retweet = new IconType("retweet");
    public static final IconType shoppingcart = new IconType("shopping-cart");
    public static final IconType folderclose = new IconType("folder-close");
    public static final IconType folderopen = new IconType("folder-open");
    public static final IconType resizevertical = new IconType("resize-vertical");
    public static final IconType resizehorizontal = new IconType("resize-horizontal");
    public static final IconType hdd = new IconType("hdd");
    public static final IconType bullhorn = new IconType("bullhorn");
    public static final IconType bell = new IconType("bell");
    public static final IconType certificate = new IconType("certificate");
    public static final IconType thumbsup = new IconType("thumbs-up");
    public static final IconType thumbsdown = new IconType("thumbs-down");
    public static final IconType handright = new IconType("hand-right");
    public static final IconType handleft = new IconType("hand-left");
    public static final IconType handup = new IconType("hand-up");
    public static final IconType handdown = new IconType("hand-down");
    public static final IconType circlearrowright = new IconType("circle-arrow-right");
    public static final IconType circlearrowleft = new IconType("circle-arrow-left");
    public static final IconType circlearrowup = new IconType("circle-arrow-up");
    public static final IconType circlearrowdown = new IconType("circle-arrow-down");
    public static final IconType globe = new IconType("globe");
    public static final IconType wrench = new IconType("wrench");
    public static final IconType tasks = new IconType("tasks");
    public static final IconType filter = new IconType("filter");
    public static final IconType briefcase = new IconType("briefcase");
    public static final IconType fullscreen = new IconType("fullscreen");

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
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }
}
