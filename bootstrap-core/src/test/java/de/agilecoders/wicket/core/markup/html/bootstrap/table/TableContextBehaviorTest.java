package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

/**
 * Tests the {@link TableContextBehavior}
 *
 * @author Eric Hamel <eric.hamel@me.com>
 */
public class TableContextBehaviorTest extends WicketApplicationTest{

	private static final String MARKUP = "<tr wicket:id=\"id\"></tr>";
	
    @Test
    public void tableContextActiveCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(), MARKUP), "active");
    }
    
    @Test
    public void tableContextInfoCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Info), MARKUP), "info");
    }
    
    @Test
    public void tableContextSuccessCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Success), MARKUP), "success");
    }
    
    @Test
    public void tableContextWarningCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Warning), MARKUP), "warning");
    }
    
    @Test
    public void tableContextDangerCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Danger), MARKUP), "danger");
    }
	
}
