package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;

import de.agilecoders.wicket.core.WicketApplicationTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClientSideBootstrapTabbedPanelTest extends WicketApplicationTest {

    private static final String MARKUP = "<div wicket:id=\"id\"></div>";

    @Test
    void testClientSideMarkupIsCreated_DefaultTabSelected() {
        TagTester root = startComponentInPage(newClientSideTabs("id", null), MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabs = root.getChild("class", "nav nav-tabs");
        assertEquals(tabs.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabs,0, true);
        assertLi(tabs,1, false);
        assertLi(tabs,2, false);
        //there is no tab3
        TagTester li3 = tabs.getChild("class", "tab3");
        assertNull(li3);
        //test main panel container.
        TagTester panelContainer = root.getChild("class", "tab-content");
        assertEquals(panelContainer.getName(), "div", "tab-content is the attribute of panelContainer");
        assertContentTabPanel(panelContainer, 0, true);
        assertContentTabPanel(panelContainer, 1, false);
        assertContentTabPanel(panelContainer, 2, false);
    }

    @Test
    void testClientSideMarkupIsCreated_FirstTabSelected() {
        TagTester root = startComponentInPage(newClientSideTabs("id", Model.of(0)), MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabs = root.getChild("class", "nav nav-tabs");
        assertEquals(tabs.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabs,0, true);
        assertLi(tabs,1, false);
        assertLi(tabs,2, false);
        //there is no tab3
        TagTester li3 = tabs.getChild("class", "tab3");
        assertNull(li3);
        //test main panel container.
        TagTester panelContainer = root.getChild("class", "tab-content");
        assertEquals(panelContainer.getName(), "div", "tab-content is the attribute of panelContainer");
        assertContentTabPanel(panelContainer, 0, true);
        assertContentTabPanel(panelContainer, 1, false);
        assertContentTabPanel(panelContainer, 2, false);
    }

    @Test
    void testClientSideMarkupIsCreated_SecondTabSelected() {
        TagTester root = startComponentInPage(newClientSideTabs("id", Model.of(1)), MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabs = root.getChild("class", "nav nav-tabs");
        assertEquals(tabs.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabs,0, false);
        assertLi(tabs,1, true);
        assertLi(tabs,2, false);
        //there is no tab3
        TagTester li3 = tabs.getChild("class", "tab3");
        assertNull(li3);
        //test main panel container.
        TagTester panelContainer = root.getChild("class", "tab-content");
        assertEquals(panelContainer.getName(), "div", "tab-content is the attribute of panelContainer");
        assertContentTabPanel(panelContainer, 0, false);
        assertContentTabPanel(panelContainer, 1, true);
        assertContentTabPanel(panelContainer, 2, false);
    }

    @Test
    void testClientSideMarkupIsCreated_ThirdTabSelected() {
        TagTester root = startComponentInPage(newClientSideTabs("id", Model.of(2)), MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabs = root.getChild("class", "nav nav-tabs");
        assertEquals(tabs.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabs,0, false);
        assertLi(tabs,1, false);
        assertLi(tabs,2, true);
        //there is no tab3
        TagTester li3 = tabs.getChild("class", "tab3");
        assertNull(li3);
        //test main panel container.
        TagTester panelContainer = root.getChild("class", "tab-content");
        assertEquals(panelContainer.getName(), "div", "tab-content is the attribute of panelContainer");
        assertContentTabPanel(panelContainer, 0, false);
        assertContentTabPanel(panelContainer, 1, false);
        assertContentTabPanel(panelContainer, 2, true);
    }

    @Test
    void testClientSideMarkupIsCreated_NotifyTabChange() {
        List<ITab> tabs = Arrays.asList(createTab("Section 1"), createTab("Section 2"), createTab("Section 3"));
        IModel<Integer> activeTabModel = Model.of(0);
        ClientSideBootstrapTabbedPanel<ITab> panel = new ClientSideBootstrapTabbedPanel<>("id", tabs, activeTabModel)
            .withShouldNotifyTabChange(true);

        TagTester root = startComponentInPage(panel, MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabsContainer = root.getChild("class", "nav nav-tabs");
        assertEquals(tabsContainer.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabsContainer, 0, true);
        assertLi(tabsContainer, 1, false);
        assertLi(tabsContainer, 2, false);
        //there is no tab3
        TagTester li3 = tabsContainer.getChild("class", "tab3");
        assertNull(li3);

        //test main panel container.
        TagTester panelContainer = root.getChild("class", "tab-content");
        assertEquals(panelContainer.getName(), "div", "tab-content is the attribute of panelContainer");
        assertContentTabPanel(panelContainer, 0, true);
        assertContentTabPanel(panelContainer, 1, false);
        assertContentTabPanel(panelContainer, 2, false);

        // test tab change notification
        final List<Component> linkComponents = new ArrayList<>();
        ((RepeatingView) panel.get("tabsContainer:tabs")).visitChildren((component, iVisit) -> {
            if ("link".equals(component.getId())) {
                linkComponents.add(component);
            }
        });
        assertEquals(3, linkComponents.size());
        // execute AjaxBehavior of second link
        tester().executeAjaxEvent(linkComponents.get(1), "click");
        assertEquals(1, activeTabModel.getObject());

        // execute AjaxBehavior of third link
        tester().executeAjaxEvent(linkComponents.get(2), "click");
        assertEquals(2, activeTabModel.getObject());

        // simulate F5 refresh
        ComponentRenderer.renderComponent(panel);
        assertEquals(2, activeTabModel.getObject()); // the third tab must stay active
    }

    private void assertLi(TagTester tabs, int index, boolean active) {
        String classNameForLi = "nav-item tab" + index;
        String classNameForLink = "nav-link" + (active ? " active" : "");
        TagTester li = tabs.getChild("class", classNameForLi);
        final TagTester link = li.getChild("class", classNameForLink);
        assertEquals(li.getName(), "li", classNameForLi + " is the class of " + index + " <li>");
        assertEquals("a", link.getName(), classNameForLink + " is the class of " + index + " <a>");
    }

    private void assertContentTabPanel(TagTester panelContainer, int index, boolean active) {
        String className = "tab" + index + (active ? " tab-pane fade show active" : " tab-pane fade");
        TagTester div = panelContainer.getChild("class", className);
        assertEquals(div.getName(), "div", className + " is the class of " + index + " <div>");
    }

    private Component newClientSideTabs(String markupId, IModel<Integer> activeTab) {
        return new ClientSideBootstrapTabbedPanel<>(markupId, Arrays.asList(
            createTab("Section 1"), createTab("Section 2"), createTab("Section 3")
        ), activeTab);
    }

    private AbstractTab createTab(final String title) {
        return new AbstractTab(Model.of(title)) {
            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new WebMarkupContainer(panelId) {
                    @Override
                    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
                        replaceComponentTagBody(markupStream, openTag, "I'm in " + title);
                    }
                };
            }
        };
    }
}
