package de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior.AnimatedBehavior.Animation;

/**
 * @author daniel.jipa
 */
public class AnimateBehaviorTest extends WicketApplicationTest {

    @Test
    public void classNameWasAdded() {
        TagTester tag = startBehaviorInPage(new AnimatedBehavior(Animation.bounce));
        Args.notNull(tag, "tag");
        final CssClassNames.Builder cssClasses = CssClassNames.parse(tag.getAttribute("class"));
        assertThat(cssClasses.contains("animated")
                        && cssClasses.contains("bounce"), is(equalTo(true)));
    }

    protected TagTester startBehaviorInPage(final Behavior behavior) {
        Component component = new WebMarkupContainer("id");
        component.add(behavior);
        tester().startComponentInPage(component);
        return tester().getTagByWicketId("id");
    }
}
