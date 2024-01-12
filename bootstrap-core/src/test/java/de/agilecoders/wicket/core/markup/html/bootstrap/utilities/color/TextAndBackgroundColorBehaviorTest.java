package de.agilecoders.wicket.core.markup.html.bootstrap.utilities.color;

import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;
import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior.TextAndBackgroundColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior;

/**
 * Tests for {@link TextAndBackgroundColorBehavior}.
 */
class TextAndBackgroundColorBehaviorTest extends WicketApplicationTest {

	@Test
	void testRendersCorrectCssClass() {
		for (TextAndBackgroundColor color : TextAndBackgroundColor.values()) {
			String markup = "<span wicket:id='" + id() + "'></span>";

			TagTester tag = startBehaviorInPage(new TextAndBackgroundColorBehavior(color), markup);
			assertCssClass(tag, color.cssClassName());
		}
	}

	@Test
	void testPreserveOtherCssClasses() {
		String markup = "<span class='my-class' wicket:id='" + id() + "'></span>";
		TextAndBackgroundColorBehavior behavior = TextAndBackgroundColorBehavior.info();

		TagTester tag = startBehaviorInPage(behavior, markup);
		assertCssClass(tag, "my-class");
	}
}
