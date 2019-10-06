package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * @author Jan Ferko
 */
class BorderBehaviorTest extends WicketApplicationTest {

    @Test
    void testRenderBorderColorCorrectly() {
        for (BorderBehavior.Color color : BorderBehavior.Color.values()) {
            assertCssClass(new BorderBehavior().color(color), color.cssClassName());
        }
    }

    @Test
    void testRenderBorderRadiusCorrectly() {
        for (BorderBehavior.Radius radius : BorderBehavior.Radius.values()) {
            assertCssClass(new BorderBehavior().radius(radius), radius.cssClassName());
        }
    }

    @Test
    void testRenderTypeCorrectly() {
        for (BorderBehavior.Type type : BorderBehavior.Type.values()) {
            assertCssClass(new BorderBehavior().type(type), type.cssClassName());
        }
    }

    @Test
    void testRenderBorderClassesCorrectly() {
        BorderBehavior behavior = new BorderBehavior()
                .type(BorderBehavior.Type.ExceptRight)
                .radius(BorderBehavior.Radius.Circle)
                .color(BorderBehavior.Color.Danger);
        assertCssClass(behavior,
                BorderBehavior.Type.ExceptRight.cssClassName(),
                BorderBehavior.Radius.Circle.cssClassName(),
                BorderBehavior.Color.Danger.cssClassName());
    }
}
