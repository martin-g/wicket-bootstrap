package de.agilecoders.wicket.samples.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.annotation.mount.MountPath;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
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

/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */
@MountPath(value = "/components")
public class ComponentsPage extends BasePage {

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

        // add example for dropdown button with sub-menu
//        add(newDropDownSubMenuExample());

        add(newTabs("tabs"));

        add(newClientSideTabs("tabsClient"));

        addProgressBars();
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
            @Override
            protected IModel<String> createLabelModel() {
                return new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return String.format("The progress is: %s%%", getModelObject());
                    }
                };
            }
        };
        labeledStack.labeled(true).type(ProgressBar.Type.SUCCESS);
        labeledProgressBar.addStacks(labeledStack);
        add(labeledProgressBar);


        ProgressBar stacked = new ProgressBar("stacked");
        add(stacked);
        Stack stackedStack1 = new Stack(stacked.getStackId(), Model.of(35)).type(ProgressBar.Type.SUCCESS);
        Stack stackedStack2 = new Stack(stacked.getStackId(), Model.of(20)).type(ProgressBar.Type.WARNING);
        Stack stackedStack3 = new Stack(stacked.getStackId(), Model.of(10)).type(ProgressBar.Type.DANGER);
        stacked.addStacks(stackedStack1, stackedStack2, stackedStack3);

        ProgressBar coloredInfo = new ProgressBar("coloredInfo", Model.of(20), ProgressBar.Type.INFO);
        add(coloredInfo);

        ProgressBar coloredSuccess = new ProgressBar("coloredSuccess", Model.of(40), ProgressBar.Type.SUCCESS);
        add(coloredSuccess);

        ProgressBar coloredWarning = new ProgressBar("coloredWarning", Model.of(60), ProgressBar.Type.WARNING);
        add(coloredWarning);

        ProgressBar coloredDanger = new ProgressBar("coloredDanger", Model.of(80), ProgressBar.Type.DANGER);
        add(coloredDanger);

        UpdatableProgressBar updatableBar = new UpdatableProgressBar("updatable", Model.of(0)) {
            @Override
            protected IModel<Integer> newValue() {
                int newValue = (value() + 1) % ProgressBar.MAX;
                return Model.of(newValue);
            }
        };
        updatableBar.updateInterval(Duration.seconds(80));
        add(updatableBar);


    }

    private void addRadioGroups() {
        add(new BooleanRadioGroup("boolean", new Model<Boolean>(Boolean.FALSE)));

        IModel<Boolean> booleanAjaxSelectedModel = Model.of(true);
        booleanAjaxSelected = new Label("booleanAjaxSelected", booleanAjaxSelectedModel);
        booleanAjaxSelected.setOutputMarkupId(true);
        add(booleanAjaxSelected);

        add(new AjaxBooleanRadioGroup("booleanAjax", booleanAjaxSelectedModel) {
            @Override
            protected void onSelectionChanged(AjaxRequestTarget target, Boolean value) {
                target.add(booleanAjaxSelected);
            }
        });


        IModel<Status> enumAjaxSelectedModel = Model.of(Status.submitted);
        enumAjaxSelected = new Label("enumAjaxSelected", enumAjaxSelectedModel);
        enumAjaxSelected.setOutputMarkupId(true);
        add(enumAjaxSelected);

        AjaxBootstrapRadioGroup<Status> enumAjax = new AjaxBootstrapRadioGroup<Status>("enumAjax", Arrays.asList(Status.values())) {
            @Override
            protected void onSelectionChanged(AjaxRequestTarget target, Status value) {
                target.add(enumAjaxSelected);
            }
        };
        enumAjax.setDefaultModel(enumAjaxSelectedModel);
        enumAjax.setChoiceRenderer(new EnumRadioChoiceRenderer<Status>(Buttons.Type.Success, enumAjax));
        add(enumAjax);
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
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new WebMarkupContainer(panelId) {
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
            @Override
            protected AbstractLink newBaseButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
                return new BootstrapAjaxLink<String>(markupId, labelModel, Buttons.Type.Default) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        target.appendJavaScript("alert('clicked');");
                    }
                };
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<>();
                subMenu.add(new MenuBookmarkablePageLink<Void>(ComponentsPage.class, Model.of("Link 1")));
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
