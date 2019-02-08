package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

public class ClientSideBootstrapTabbedPanelTest extends WicketApplicationTest {

    private static final String MARKUP = "<div wicket:id=\"id\"></div>";

    @Test
    public void testClientSideMarkupIsCreated_DefaultTabSelected() {
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
    public void testClientSideMarkupIsCreated_FirstTabSelected() {
        TagTester root = startComponentInPage(newClientSideTabs("id",  Model.of(0)), MARKUP);
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
    public void testClientSideMarkupIsCreated_SecondTabSelected() {
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
    public void testClientSideMarkupIsCreated_ThirdTabSelected() {
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

    private void assertLi(TagTester tabs, int index, boolean active) {
        String className = "tab"+ index +(active?" active":"");
        TagTester li = tabs.getChild("class", className);
        assertEquals(li.getName(), "li", className+ " is the class of " + index +" <li>");
    }

    private void assertContentTabPanel(TagTester panelContainer, int index, boolean active) {
        String className = "tab" + index + (active?" tab-pane fade in active":" tab-pane fade");
        TagTester div = panelContainer.getChild("class", className);
        assertEquals(div.getName(), "div", className+ " is the class of " + index +" <div>");
    }

    private Component newClientSideTabs(String markupId, IModel<Integer> activeTab) {
        return new ClientSideBootstrapTabbedPanel<>(markupId, Lists.newArrayList(
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
