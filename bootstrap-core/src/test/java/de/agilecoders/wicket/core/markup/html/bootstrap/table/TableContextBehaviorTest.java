package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link TableContextBehavior}
 *
 * @author Eric Hamel <eric.hamel@me.com>
 */
class TableContextBehaviorTest extends WicketApplicationTest{

	private static final String MARKUP = "<tr wicket:id=\"id\"></tr>";

    @Test
    void tableContextActiveCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(), MARKUP), "active");
    }

    @Test
    void tableContextInfoCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Info), MARKUP), "info");
    }

    @Test
    void tableContextSuccessCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Success), MARKUP), "success");
    }

    @Test
    void tableContextWarningCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Warning), MARKUP), "warning");
    }

    @Test
    void tableContextDangerCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Danger), MARKUP), "danger");
    }

}
