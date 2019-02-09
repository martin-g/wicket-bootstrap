package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link TableBehavior}
 *
 * @author miha
 */
public class TableBehaviorTest extends WicketApplicationTest {
    private static final String MARKUP = "<table wicket:id=\"id\"></table>";

    @Test
    public void tableCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior(), MARKUP), "table");
    }

    @Test
    public void multipleTableStylesAreRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().striped().bordered().sm(), MARKUP), "table", "table-striped", "table-bordered", "table-sm");
    }

    @Test
    public void stripedCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().striped(), MARKUP), "table", "table-striped");
    }

    @Test
    public void smCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().sm(), MARKUP), "table", "table-sm");
    }

    @Test
    public void borderedCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().bordered(), MARKUP), "table", "table-bordered");
    }

    @Test
    public void hoverCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().hover(), MARKUP), "table", "table-hover");
    }

    @Test
    public void darkCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().dark(), MARKUP), "table", "table-dark");
    }

    @Test
    public void lightCssIsRendered() {
        assertCssClass(startBehaviorInPage(new TableBehavior().light(), MARKUP), "table", "table-light");
    }
}
