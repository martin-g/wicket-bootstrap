package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link TableBehavior}
 *
 * @author miha
 */
class TableBehaviorTest extends WicketApplicationTest {
    private static final String MARKUP = "<table wicket:id=\"id\"></table>";

    @Test
    void tableCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior(), MARKUP), "table");
    }

    @Test
    void multipleTableStylesAreRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().striped().bordered().condensed(), MARKUP), "table", "table-striped", "table-bordered", "table-condensed");
    }

    @Test
    void stripedCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().striped(), MARKUP), "table", "table-striped");
    }

    @Test
    void condensedCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().condensed(), MARKUP), "table", "table-condensed");
    }

    @Test
    void borderedCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().bordered(), MARKUP), "table", "table-bordered");
    }

    @Test
    void hoverCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().hover(), MARKUP), "table", "table-hover");
    }
}
