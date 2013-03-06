package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.javascript.jasny.FileUploadField;
import de.agilecoders.wicket.javascript.jasny.InputMaskBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.markup.html.bootstrap.dialog.TextContentModal;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.Draggable;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.DraggableConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.behavior.Resizable;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.button.DropDownAutoOpen;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.contextmenu.ButtonListContextMenu;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5Player;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5VideoConfig;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Video;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.icon.OpenWebIconType;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.tour.TourBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.tour.TourStep;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.samples.panels.pagination.InfinitePaginationPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
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
                new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time),
                new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.book),
                new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.qrcode)
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

        DropDownButton dropDownButton = new DropDownButton("dropdown", Model.of("open-on-hover"));
        final List<? extends AbstractLink> buttons2 = Lists.<AbstractLink>newArrayList(
                new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time),
                new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.book),
                new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.qrcode)
        );
        dropDownButton.addButtons(buttons2);
        dropDownButton.add(new DropDownAutoOpen());
        add(dropDownButton, new Code("dropdown-code", Model.of("dropDownButton.add(new DropDownAutoOpen());")));

        addTour();
        add(new Icon("html5-colored", OpenWebIconType.html5_colored_large), new Icon("apml", OpenWebIconType.apml), new Icon("feed", OpenWebIconType.feed_colored_large));
        add(new Icon("html5", OpenWebIconType.html5), new Code("openwebicon-code", Model.of("response.render(JavaScriptHeaderItem.forReference(OpenWebIconsCssReference.instance()));\n\nadd(new Icon(\"html5\", OpenWebIconType.html5));")));

        addJasnyFileUploadDemo();
        addJasnyInputMaskDemo();

        add(new InfinitePaginationPanel("infinite"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(OpenWebIconsCssReference.instance()));
    }


    private void addJasnyFileUploadDemo() {
        FileUploadField fileUpload = new FileUploadField("fileUpload");
        add(fileUpload);
    }

    private void addJasnyInputMaskDemo() {
        TextField textField = new TextField("inputMask", Model.of("l0rdn1kk0n"));
        InputMaskBehavior inputMask = new InputMaskBehavior() {
            @Override
            protected String getMask() {
                // Allow entering l0rdn1kk0n
                return "a9aaa9aa9a";
            }
        };
        textField.add(inputMask);
        add(textField);
    }

    /**
     * Demo for TourBehavior. Issue #116
     */
    private void addTour() {
        RepeatingView view = new RepeatingView("tourDemo");
        add(view);

        Label stepOne = new Label(view.newChildId(), "Step One");
        view.add(stepOne);

        Label stepTwo = new Label(view.newChildId(), "Step Two");
        view.add(stepTwo);

        Label stepThree = new Label(view.newChildId(), "Step Three");
        view.add(stepThree);

        TourBehavior tourBehavior = new TourBehavior() {
            @Override
            protected CharSequence createExtraConfig() {
                return "if ( tour.ended() ) {\n" +
                       "    $('<div class=\"alert\">\\\n" +
                       "      <button class=\"close\" data-dismiss=\"alert\">&times;</button>\\\n" +
                       "      You ended the demo tour. <a href=\"\" class=\"restart\">Restart the demo tour.</a>\\\n" +
                       "      </div>').prependTo(\".content\").alert();\n" +
                       "  }\n" +
                       "\n" +
                       "  $(\".restart\").click(function (e) {\n" +
                       "    e.preventDefault();\n" +
                       "    tour.restart();\n" +
                       "    $(this).parents(\".alert\").alert(\"close\");\n" +
                       "  });";
            }
        };
        tourBehavior.addStep(new TourStep()
                                     .title(Model.of("Step One Title"))
                                     .element(stepOne)
                                     .content(Model.of("Some help <strong>content.Some help </strong>content.Some help content.Some help content.Some help content.")));
        tourBehavior.addStep(new TourStep()
                                     .title(new ResourceModel("tour.step.two"))
                                     .element(stepTwo)
                                     .placement(TooltipConfig.Placement.left));
        tourBehavior.addStep(new TourStep()
                                     .title(Model.of("Step Three Title"))
                                     .element(stepThree)
                                     .placement(TooltipConfig.Placement.left)
                                     .content(Model.of("Step 3 content")));
        view.add(tourBehavior);
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
