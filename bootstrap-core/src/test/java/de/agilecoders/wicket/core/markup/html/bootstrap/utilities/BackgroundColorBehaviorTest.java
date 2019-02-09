package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.util.tester.TagTester;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * @author Jan Ferko
 */
public class BackgroundColorBehaviorTest extends WicketApplicationTest {

    @Test
    public void testRendersCorrectCssClass() {
        for (ColorBehavior.Color color : ColorBehavior.Color.values()) {
            String markup = "<span wicket:id='" + id() + "'></span>";

            TagTester tag = startBehaviorInPage(new ColorBehavior(color), markup);
            assertCssClass(tag, color.cssClassName());
        }
    }

    @Test
    public void testPreserveOtherCssClasses() {
        String markup = "<span class='my-class' wicket:id='" + id() + "'></span>";
        ColorBehavior behavior = ColorBehavior.info();

        TagTester tag = startBehaviorInPage(behavior, markup);

        assertCssClass(tag, "my-class");
    }
}
