package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link PanelBehavior}
 *
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
class PanelBehaviorTest extends WicketApplicationTest{

	private static final String MARKUP = "<div wicket:id=\"id\"></div>";

    @Test
    void panelBehaviorDefaultCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(), MARKUP), "panel", "panel-default");
    }

    @Test
    void panelBehaviorDPrimaryCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Primary), MARKUP), "panel", "panel-primary");
    }

    @Test
    void panelBehaviorSuccessCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Success), MARKUP), "panel", "panel-success");
    }

    @Test
    void panelBehaviorInfoCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Info), MARKUP), "panel", "panel-info");
    }

    @Test
    void panelBehaviorWarningCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Warning), MARKUP), "panel", "panel-warning");
    }

    @Test
    void panelBehaviorDangerCssIsRendered() {
        assertCssClass(startBehaviorInPage(new PanelBehavior(PanelType.Danger), MARKUP), "panel", "panel-danger");
    }


}
