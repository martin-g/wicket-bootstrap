package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import de.agilecoders.wicket.core.WicketApplicationTest;

import org.junit.Test;

/**
 * Tests the {@link BadgeBehavior}
 *
 * @author miha
 */
public class BadgeBehaviorTest extends WicketApplicationTest {

    @Test
    public void classNameWasAdded() {
        assertCssClass(new BadgeBehavior(), "badge");
    }

    @Test
    public void rendersContextualClassCorrectly() {
        for (BadgeBehavior.Type type : BadgeBehavior.Type.values()) {
            assertCssClass(new BadgeBehavior(type), type.cssClassName());
        }
    }

    @Test
    public void rendersPillClassCorrectly() {
        assertCssClass(new BadgeBehavior(BadgeBehavior.Type.Light, true), "badge-pill");
    }

}
