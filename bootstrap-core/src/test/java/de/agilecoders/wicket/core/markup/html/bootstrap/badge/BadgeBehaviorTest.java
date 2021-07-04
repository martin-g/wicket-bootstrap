package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;

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
