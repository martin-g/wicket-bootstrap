package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.badge.BadgeBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.badge.BootstrapBadge;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.ColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.Stack;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UpdatableProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.AjaxBooleanRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.AjaxBootstrapRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.BooleanRadioGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.radio.EnumRadioChoiceRenderer;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.ClientSideBootstrapTabbedPanel;
import de.agilecoders.wicket.samples.components.basecss.ButtonGroups;
import de.agilecoders.wicket.samples.panels.SimpleCard;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import java.time.Duration;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */
@MountPath(value = "/components")
public class ComponentsPage extends BasePage {
    private static final long serialVersionUID = 1L;

    private static enum Status {
        submitted,
        onReview,
        discarded,
        accepted
    }

    private Label booleanAjaxSelected;
    private Label enumAjaxSelected;

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ComponentsPage(PageParameters parameters) {
        super(parameters);

        add(newSplitButton("splitbutton"));

        add(new ButtonGroups("buttonGroups"));

        // create radio buttons
        addRadioGroups();

        // create bootstrap labels
        addLabels();

        addBadges();

        // add example for dropdown button with sub-menu
//        add(newDropDownSubMenuExample());

        add(newTabs("tabs"));

        add(newClientSideTabs("tabsClient"));

        addProgressBars();

        add(newCard("card-demo"));
    }

    private void addProgressBars() {
        ProgressBar basic = new ProgressBar("basic", Model.of(60));
        add(basic);

        ProgressBar striped = new ProgressBar("striped", Model.of(20)).striped(true);
        add(striped);

        ProgressBar animated = new ProgressBar("animated", Model.of(45)).active(true);
        add(animated);

        ProgressBar labeledProgressBar = new ProgressBar("labeled");
        Stack labeledStack = new Stack(labeledProgressBar.getStackId(), Model.of(45)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected IModel<String> createLabelModel() {
                return () -> String.format("The progress is: %s%%", getModelObject());
            }
        };
        labeledStack.labeled(true).color(BackgroundColorBehavior.Color.Success);
        labeledProgressBar.addStacks(labeledStack);
        add(labeledProgressBar);


        ProgressBar stacked = new ProgressBar("stacked");
        add(stacked);
        Stack stackedStack1 = new Stack(stacked.getStackId(), Model.of(35)).color(BackgroundColorBehavior.Color.Success);
        Stack stackedStack2 = new Stack(stacked.getStackId(), Model.of(20)).color(BackgroundColorBehavior.Color.Warning);
        Stack stackedStack3 = new Stack(stacked.getStackId(), Model.of(10)).color(BackgroundColorBehavior.Color.Danger);
        stacked.addStacks(stackedStack1, stackedStack2, stackedStack3);

        ProgressBar coloredInfo = new ProgressBar("coloredInfo", Model.of(20), BackgroundColorBehavior.Color.Info);
        add(coloredInfo);

        ProgressBar coloredSuccess = new ProgressBar("coloredSuccess", Model.of(40), BackgroundColorBehavior.Color.Success);
        add(coloredSuccess);

        ProgressBar coloredWarning = new ProgressBar("coloredWarning", Model.of(60), BackgroundColorBehavior.Color.Warning);
        add(coloredWarning);

        ProgressBar coloredDanger = new ProgressBar("coloredDanger", Model.of(80), BackgroundColorBehavior.Color.Danger);
        add(coloredDanger);

        UpdatableProgressBar updatableBar = new UpdatableProgressBar("updatable", Model.of(0)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected IModel<Integer> newValue() {
                int newValue = (value() + 1) % ProgressBar.MAX;
                return Model.of(newValue);
            }
        };
        updatableBar.updateInterval(Duration.ofSeconds(80L));
        add(updatableBar);


    }

    private void addRadioGroups() {
        add(new BooleanRadioGroup("boolean", new Model<>(Boolean.FALSE)));

        IModel<Boolean> booleanAjaxSelectedModel = Model.of(true);
        booleanAjaxSelected = new Label("booleanAjaxSelected", booleanAjaxSelectedModel);
        booleanAjaxSelected.setOutputMarkupId(true);
        add(booleanAjaxSelected);

        add(new AjaxBooleanRadioGroup("booleanAjax", booleanAjaxSelectedModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSelectionChanged(AjaxRequestTarget target, Boolean value) {
                target.add(booleanAjaxSelected);
            }
        });


        IModel<Status> enumAjaxSelectedModel = Model.of(Status.submitted);
        enumAjaxSelected = new Label("enumAjaxSelected", enumAjaxSelectedModel);
        enumAjaxSelected.setOutputMarkupId(true);
        add(enumAjaxSelected);

        AjaxBootstrapRadioGroup<Status> enumAjax = new AjaxBootstrapRadioGroup<>("enumAjax", Arrays.asList(Status.values())) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSelectionChanged(AjaxRequestTarget target, Status value) {
                target.add(enumAjaxSelected);
            }
        };
        enumAjax.setDefaultModel(enumAjaxSelectedModel);
        enumAjax.setChoiceRenderer(new EnumRadioChoiceRenderer<Status>(Buttons.Type.Success, enumAjax));
        add(enumAjax);
    }

    private void addLabels() {
        List<BadgeBehavior.Type> types = Lists.newArrayList(BadgeBehavior.Type.values());
        add(new ListView<>("badges", types) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BadgeBehavior.Type> item) {
                BadgeBehavior.Type type = item.getModelObject();

                item.add(new BootstrapBadge("badge", type.cssClassName(), type));

                Code code = new Code(
                        "code",
                        Model.of(String.format("<span class='badge %1$s'>%1$s</span>", type.cssClassName()))
                );
                item.add(code);
            }
        });
    }

    private void addBadges() {
        List<BadgeBehavior.Type> types = Lists.newArrayList(BadgeBehavior.Type.values());

        add(new ListView<>("badge-pills", types) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BadgeBehavior.Type> item) {
                BadgeBehavior.Type type = item.getModelObject();

                item.add(new Label("name", type.cssClassName()));

                item.add(new BootstrapBadge("badge", 1, type).setPill(true));

                item.add(new Code("code",
                        Model.of(String.format("<span class='badge badge-pills %1$s'>%1$s</span>", type.cssClassName()))
                ));
            }
        });

        Link<Void> badgeButton = new Link<>("button-with-badge") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                //ok
            }
        };
        badgeButton.add(new ButtonBehavior(Buttons.Type.Primary));
        badgeButton.add(new BootstrapBadge("badge", Model.of(1), BadgeBehavior.Type.Light));
        add(badgeButton);
    }

        private Component newCard(String markupId) {
        return new SimpleCard(markupId)
                .add(new BorderBehavior()
                        .type(BorderBehavior.Type.All)
                        .color(BorderBehavior.Color.Dark)
                        .radius(BorderBehavior.Radius.All))
                .add(ColorBehavior.success());
    }

    private Component newTabs(String markupId) {
        return new AjaxBootstrapTabbedPanel<>(markupId, Lists.newArrayList(
                createTab("Section 1"), createTab("Section 2"), createTab("Section 3")
        ));
    }

    private Component newClientSideTabs(String markupId) {
        return new ClientSideBootstrapTabbedPanel<>(markupId, Lists.newArrayList(
                createTab("Section 1"), createTab("Section 2"), createTab("Section 3")
        ));
    }

    private AbstractTab createTab(final String title) {
        return new AbstractTab(Model.of(title)) {
            private static final long serialVersionUID = 1L;

            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new WebMarkupContainer(panelId) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
                        replaceComponentTagBody(markupStream, openTag, "<br/>I'm in " + title);
                    }
                };
            }
        };
    }


    /**
     * creates a new split button with some submenu links.
     *
     * @param markupId the markup id of that the split button has to use
     * @return new {@link SplitButton} instance
     */
    private Component newSplitButton(final String markupId) {
        return new SplitButton(markupId, Model.of("Action")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected AbstractLink newBaseButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
                return new BootstrapAjaxLink<>(markupId, labelModel, Buttons.Type.Secondary, labelModel) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        target.appendJavaScript("alert('clicked');");
                    }
                }.setIconType(iconTypeModel.getObject());
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<>();
                subMenu.add(new MenuBookmarkablePageLink<Void>(ComponentsPage.class, Model.of("Link 1")));
                subMenu.add(new MenuDivider());
                subMenu.add(new MenuBookmarkablePageLink<Void>(ComponentsPage.class, Model.of("Link 2")));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ComponentsPage.class, Model.of("Link 3")));

                return subMenu;
            }
        };
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
