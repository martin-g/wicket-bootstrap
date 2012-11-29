package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5Player;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5VideoConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Video;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.List;

/**
 * The {@code ExtensionsPage}
 *
 * @author miha
 */
@MountPath(value = "/extensions")
public class ExtensionsPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ExtensionsPage(PageParameters parameters) {
        super(parameters);

        List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo>newArrayList(
                new Video("http://ia700305.us.archive.org/18/items/CopyingIsNotTheft/CINT_Nik_H264_720.ogv", "video/ogg"),
                new Video("http://ia700305.us.archive.org/18/items/CopyingIsNotTheft/CINT_Nik_H264_720_512kb.mp4", "video/mp4")
        );
        add(new Html5Player("video", Model.ofList(videos)));
        add(new Code("video-code", Model.of("List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo>newArrayList(\n"
                                            + "\t\tnew Video(\"video.ogv\", \"video/ogg\"),\n"
                                            + "\t\tnew Video(\"video.mp4\", \"video/mp4\")\n"
                                            + ");\n"
                                            + "add(new Html5Player(\"video\", Model.ofList(videos)));")));

        add(new Html5Player("video-custom", Model.ofList(videos), new Html5VideoConfig().showProgressBar(false).autoHideControlBar(false)).setWidth(680).setHeight(360));
        add(new Code("video-custom-code", Model.of("List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo>newArrayList(\n"
                                                   + "\t\tnew Video(\"video.ogv\", \"video/ogg\"),\n"
                                                   + "\t\tnew Video(\"video.mp4\", \"video/mp4\")\n"
                                                   + ");\n"
                                                   + "add(new Html5Player(\"video\", Model.ofList(videos),\n"
                                                   + "\tnew Html5VideoConfig().showProgressBar(false).autoHideControlBar(false))\n"
                                                   + "\t\t.setWidth(680).setHeight(360));")));
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
