package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.markup.html.bootstrap.dialog.TextContentModal;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.Draggable;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.DraggableConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.Resizable;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.contextmenu.ButtonListContextMenu;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5Player;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5VideoConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Video;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
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

        final List<? extends AbstractLink> buttons = Lists.<AbstractLink>newArrayList(
                new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.Time),
                new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.Book),
                new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.QrCode)
        );
        final Component contextPanel = new TransparentWebMarkupContainer("context-panel");
        final ButtonListContextMenu contextMenu = new ButtonListContextMenu("contextmenu", Model.ofList(buttons));
        contextMenu.assignTo(contextPanel);
        add(contextMenu, contextPanel,
            new Code("context-code", Model.of(""
                                              + "final List<? extends AbstractLink> buttons = Lists.<AbstractLink>newArrayList(\n"
                                              + "\tnew MenuBookmarkablePageLink<>(...),\n"
                                              + "\t[...]\n"
                                              + ");\n"
                                              + "final Component contextPanel = new TransparentWebMarkupContainer(\"context-panel\");\n"
                                              + "final ButtonListContextMenu contextMenu = new ButtonListContextMenu(\"contextmenu\", \n"
                                              + "\t\tModel.ofList(buttons));\n"
                                              + "contextMenu.assignTo(contextPanel);\n"
                                              + "add(contextMenu, contextPanel,")));

        Modal draggableModal = new TextContentModal("draggable-modal", Model.of("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."));
        draggableModal.add(new Draggable(new DraggableConfig().withHandle(".modal-header").withCursor("move")));
        draggableModal.add(new Resizable());
        draggableModal.setUseKeyboard(true).addCloseButton();
        draggableModal.setFadeIn(false);
        Label draggableButton = new Label("open-draggable", "Open Modal Dialog");
        draggableModal.addOpenerAttributesTo(draggableButton);
        add(draggableModal, draggableButton, new Code("draggable-code", Model.of("")));
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
