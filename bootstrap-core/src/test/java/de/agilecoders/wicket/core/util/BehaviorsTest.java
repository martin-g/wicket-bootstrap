package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Behaviors} class
 *
 * @author miha
 */
class BehaviorsTest extends WicketApplicationTest {

    @Test
    void removeRemovesBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.remove(component, behavior), is(equalTo(true)));
        assertThat(component.getBehaviors().size(), is(equalTo(0)));
    }

    @Test
    void containsClassReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, CssClassNameModifier.class), is(equalTo(false)));
    }

    @Test
    void containsClassReturnsTrueForAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, CssClassNameAppender.class), is(equalTo(true)));
    }

    @Test
    void containsReturnsTrueForAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, behavior), is(equalTo(true)));
    }

    @Test
    void containsReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Behavior behavior2 = new CssClassNameAppender("classname2");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.contains(component, behavior2), is(equalTo(false)));
    }

    @Test
    void removeReturnsFalseForNotAddedBehavior() {
        final Behavior behavior = new CssClassNameAppender("classname");
        final Behavior behavior2 = new CssClassNameAppender("classname2");
        final Component component = new WebMarkupContainer("container");
        component.add(behavior);

        assertThat(Behaviors.remove(component, behavior2), is(equalTo(false)));
    }
}
