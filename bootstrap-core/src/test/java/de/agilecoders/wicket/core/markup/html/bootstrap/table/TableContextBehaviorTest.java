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
        assertCssClass(startBehaviorInPage(new TableContextBehavior(), MARKUP), "table-active");
    }

    @Test
    void tableContextInfoCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Info), MARKUP), "table-info");
    }

    @Test
    void tableContextSuccessCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Success), MARKUP), "table-success");
    }

    @Test
    void tableContextWarningCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Warning), MARKUP), "table-warning");
    }

    @Test
    void tableContextDangerCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Danger), MARKUP), "table-danger");
    }

    @Test
    void tableContextPrimaryCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Primary), MARKUP), "table-primary");
    }

    @Test
    void tableContextSecondaryCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Secondary), MARKUP), "table-secondary");
    }

    @Test
    void tableContextDarkCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Dark), MARKUP), "table-dark");
    }

    @Test
    void tableContextLightCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableContextBehavior(TableContextType.Light), MARKUP), "table-light");
    }
}
