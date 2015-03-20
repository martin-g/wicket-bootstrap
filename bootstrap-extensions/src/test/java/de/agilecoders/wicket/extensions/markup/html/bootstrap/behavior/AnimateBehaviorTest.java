package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import org.junit.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior.AnimatedBehavior.Animation;

/**
 * @author daniel.jipa
 *
 */
public class AnimateBehaviorTest extends WicketApplicationTest {

	@Test
	public void classNameWasAdded() {
		assertCssClass(new AnimatedBehavior(Animation.bounce),
				"animated bounce");
	}

}
