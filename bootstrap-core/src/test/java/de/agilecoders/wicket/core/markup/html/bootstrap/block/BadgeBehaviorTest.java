package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link BadgeBehavior}
 *
 * @author miha
 */
class BadgeBehaviorTest extends WicketApplicationTest {

    @Test
    void classNameWasAdded() {
        assertCssClass(new BadgeBehavior(), "badge");
    }

}
