package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.WicketApplicationTest;

public class ClientSideBootstrapTabbedPanelTest extends WicketApplicationTest {

	private static final String MARKUP = "<div wicket:id=\"id\"></div>";

	@Test
	public void clientSideMarkupIsCreated() {
		TagTester root = startComponentInPage(newClientSideTabs("id"), MARKUP);
		assertTrue(root.hasChildTag("ul"));
		assertTrue(root.hasChildTag("div"));
		//test tabs
		TagTester tabs = root.getChild("class", "nav nav-tabs");	
		assertEquals("nav nav-tabs is the class of UL", tabs.getName(), "ul");		
		assertLi(tabs,0, true);	
		assertLi(tabs,1, false);
		assertLi(tabs,2, false);
		//there is no tab3
		TagTester li3 = tabs.getChild("class", "tab3");
		assertNull(li3);
		//test main panel container.
		TagTester panelContainer = root.getChild("class", "tab-content");
		assertEquals("tab-content is the attribute of panelContainer", panelContainer.getName(), "div");		
	}
	
	private void assertLi(TagTester tabs, int index, boolean active) {
		String className = "tab"+ index +(active?" active":"");
		TagTester li = tabs.getChild("class", className);
		assertEquals(className+ " is the class of " + index +" <LI>", li.getName(), "li");
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
                        replaceComponentTagBody(markupStream, openTag, "I'm in " + title);
                    }
                };
            }
        };
    }
}
