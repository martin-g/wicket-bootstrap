package de.agilecoders.wicket.core.markup.html.bootstrap.utilities;

import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;

/**
 * @author Jan Ferko
 */
class BackgroundColorBehaviorTest extends WicketApplicationTest {

	@Test
	void testRendersCorrectCssClass() {
		for (BackgroundColorBehavior.BackgroundColor color : BackgroundColorBehavior.BackgroundColor.values()) {
			String markup = "<span wicket:id='" + id() + "'></span>";

			TagTester tag = startBehaviorInPage(new BackgroundColorBehavior(color), markup);
			assertCssClass(tag, color.cssClassName());
		}
	}

	@Test
	void testPreserveOtherCssClasses() {
		String markup = "<span class='my-class' wicket:id='" + id() + "'></span>";
		BackgroundColorBehavior behavior = BackgroundColorBehavior.info();

		TagTester tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, "my-class");
	}

	@Test
	void testGradientCssClasses() {
		for (BackgroundColorBehavior.BackgroundColor color : BackgroundColorBehavior.BackgroundColor.values()) {
			String markup = "<span wicket:id='" + id() + "'></span>";

			TagTester tag = startBehaviorInPage(new BackgroundColorBehavior(color).withGradient(true), markup);
			assertCssClass(tag, color.cssClassName());
			assertCssClass(tag, "bg-gradient");
		}
	}

	@Test
	void testOpacityCssClasses() {
		final String markup = "<span wicket:id='" + id() + "'></span>";
		for (final BackgroundOpacity backgroundOpacity : BackgroundOpacity.values()) {
			TagTester tag = startBehaviorInPage(new BackgroundColorBehavior(BackgroundColor.Danger).withOpacity(backgroundOpacity), markup);
			assertCssClass(tag, BackgroundColor.Danger.cssClassName());
			assertCssClass(tag, backgroundOpacity.cssClassName());
		}

		// Test opacity null
		final BackgroundColorBehavior behavior = BackgroundColorBehavior.dark().withOpacity(null);
		TagTester tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, BackgroundColor.Dark.cssClassName());
		for (final BackgroundOpacity backgroundOpacity : BackgroundOpacity.values()) {
			assertNotContainsCssClass(tag, backgroundOpacity.cssClassName());
		}
	}
}
