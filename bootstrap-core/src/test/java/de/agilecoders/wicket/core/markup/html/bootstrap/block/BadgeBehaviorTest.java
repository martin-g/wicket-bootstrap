package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.BadgeBehavior;

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

}
