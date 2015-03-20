package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.AnimatedBehavior.Animation;

/**
 * @author daniel.jipa
 *
 */
public class AnimateBehaviorTest extends WicketApplicationTest {

    @Test
    public void classNameWasAdded() {
        assertCssClass(new AnimatedBehavior(Animation.bounce), "animated bounce");
    }

}

