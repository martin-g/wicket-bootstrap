package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

class BootstrapTabbedPanelTest extends WicketApplicationTest {

    private static final String MARKUP = "<div wicket:id=\"id\"></div>";

    @Test
    void testMarkupIsCreated_DefaultTabSelected() {
        TagTester root = startComponentInPage(newTabs("id", null), MARKUP);
        assertTrue(root.hasChildTag("ul"));
        assertTrue(root.hasChildTag("div"));
        //test tabs
        TagTester tabs = root.getChild("class", "nav nav-tabs");
        assertEquals(tabs.getName(), "ul", "nav nav-tabs is the class of UL");
        assertLi(tabs, 0, true);
        assertLi(tabs, 1, false);
        assertLi(tabs, 2, false);
        //there is no tab3
        TagTester li3 = tabs.getChild("class", "tab3");
        assertNull(li3);
        //test tabs.
        TagTester tabContainer = root.getChild("class", "nav nav-tabs");
        assertEquals(tabContainer.getName(), "ul", "tabs are in ul container");
        assertTabLink(tabContainer, 0, true);
        assertTabLink(tabContainer, 1, false);
        assertTabLink(tabContainer, 2, false);
    }

    private Component newTabs(String markupId, IModel<Integer> activeTab) {
        return new BootstrapTabbedPanel<>(markupId, Lists.newArrayList(
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

    private void assertLi(TagTester tabs, int index, boolean active) {
        String className = "nav-item tab" + index;
        TagTester li = tabs.getChild("class", className);
        assertEquals(li.getName(), "li", className + " is the class of " + index + " <li>");
    }

    private void assertTabLink(TagTester panelContainer, int index, boolean active) {
        String className = "nav-item tab" + index;
        TagTester li = panelContainer.getChild("class", className);
        assertEquals(li.getName(), "li", className + " is the class of " + index + " <li>");

        TagTester link = li.getChild("a");
        assertEquals(active, link.getAttributeContains("class", "active"), "link is marked active or inactive");
    }
}
