package de.agilecoders.wicket.samples.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
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

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ComponentsPage(PageParameters parameters) {
        super(parameters);

        add(newSplitButton("splitbutton"));
        add(new ButtonGroups("buttonGroups"));

        // add example for dropdown button with sub-menu
//        add(newDropDownSubMenuExample());

        add(newTabs("tabs"));
        
        add(newClientSideTabs("tabsClient"));
    }

    private Component newTabs(String markupId) {
        return new AjaxBootstrapTabbedPanel<AbstractTab>(markupId, Lists.<AbstractTab>newArrayList(
                createTab("Section 1"), createTab("Section 2"), createTab("Section 3")
        ));
    }

    private Component newClientSideTabs(String markupId) {
        return new ClientSideBootstrapTabbedPanel<AbstractTab>(markupId, Lists.<AbstractTab>newArrayList(
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
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                subMenu.add(new MenuBookmarkablePageLink<ComponentsPage>(ComponentsPage.class, Model.of("Link 1")));
                subMenu.add(new MenuBookmarkablePageLink<ComponentsPage>(ComponentsPage.class, Model.of("Link 2")));
                subMenu.add(new MenuBookmarkablePageLink<ComponentsPage>(ComponentsPage.class, Model.of("Link 3")));

                return subMenu;
            }
        };
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
