package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.WicketApplicationTest;
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
