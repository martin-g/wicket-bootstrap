package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link PanelBehavior} 
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public class PanelBehaviorTest extends WicketApplicationTest{

	private static final String MARKUP = "<div wicket:id=\"id\"></div>";
	
    @Test
    public void panelBehaviorDefaultCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(), MARKUP), "panel", "panel-default");
    }
    
    @Test
    public void panelBehaviorDPrimaryCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Primary), MARKUP), "panel", "panel-primary");
    }
    
    @Test
    public void panelBehaviorSuccessCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Success), MARKUP), "panel", "panel-success");
    }
    
    @Test
    public void panelBehaviorInfoCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Info), MARKUP), "panel", "panel-info");
    }
    
    @Test
    public void panelBehaviorWarningCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Warning), MARKUP), "panel", "panel-warning");
    }
    
    @Test
    public void panelBehaviorDangerCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Danger), MARKUP), "panel", "panel-danger");
    }
    
	
}