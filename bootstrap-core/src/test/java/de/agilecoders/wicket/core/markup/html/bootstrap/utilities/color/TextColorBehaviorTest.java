package de.agilecoders.wicket.core.markup.html.bootstrap.utilities.color;

import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior.TextColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior.TextOpacity;

/**
 * @author Jan Ferko
 */
class TextColorBehaviorTest extends WicketApplicationTest {

	@Test
	void testRendersCorrectCssClass() {
		for (TextColorBehavior.TextColor color : TextColorBehavior.TextColor.values()) {
			String markup = "<span wicket:id='" + id() + "'></span>";

			TagTester tag = startBehaviorInPage(new TextColorBehavior(color), markup);
			assertCssClass(tag, color.cssClassName());
		}
	}

	@Test
	void testPreserveOtherCssClasses() {
		String markup = "<span class='my-class' wicket:id='" + id() + "'></span>";
		TextColorBehavior behavior = TextColorBehavior.info();

		TagTester tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, "my-class");
	}

	@Test
	void testOpacityCssClass() {
		final String markup = "<span class='my-class' wicket:id='" + id() + "'></span>";
		TextColorBehavior behavior = TextColorBehavior.info().withTextOpacity(TextOpacity.Opacity_50);

		TagTester tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, TextColor.Info.cssClassName());
		assertCssClass(tag, TextOpacity.Opacity_50.cssClassName());

		// Test opacity null
		behavior = TextColorBehavior.dark().withTextOpacity(null);
		tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, TextColor.Dark.cssClassName());
		for (final TextOpacity textOpacity : TextOpacity.values()) {
			assertNotContainsCssClass(tag, textOpacity.cssClassName());
		}
	}
}
