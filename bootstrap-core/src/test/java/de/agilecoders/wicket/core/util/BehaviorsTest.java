package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.core.test.IntegrationTest;
import de.agilecoders.wicket.core.util.Behaviors;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Behaviors} class
 *
 * @author miha
 */
@Category(IntegrationTest.class)
public class BehaviorsTest extends WicketApplicationTest {

    @Test
    public void removeRemovesBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.remove(component, behavior), is(equalTo(true)));
        assertThat(component.getBehaviors().size(), is(equalTo(0)));
    }

    @Test
    public void containsClassReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, CssClassNameModifier.class), is(equalTo(false)));
    }

    @Test
    public void containsClassReturnsTrueForAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, CssClassNameAppender.class), is(equalTo(true)));
    }

    @Test
    public void containsReturnsTrueForAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, behavior), is(equalTo(true)));
    }

    @Test
    public void containsReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Behavior behavior2 = new CssClassNameAppender("classname2");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, behavior2), is(equalTo(false)));
    }

    @Test
    public void removeReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Behavior behavior2 = new CssClassNameAppender("classname2");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.remove(component, behavior2), is(equalTo(false)));
    }
}
