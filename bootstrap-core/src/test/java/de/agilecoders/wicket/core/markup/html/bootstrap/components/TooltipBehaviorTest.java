package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.WicketApplicationTest;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link TooltipBehavior} class
 *
 * @author miha
 */
public class TooltipBehaviorTest extends WicketApplicationTest {

    @Test
    public void relIsSet() {
        tester().startComponentInPage(newTooltip("text"));
        final TagTester tag = tester().getTagByWicketId(id());

        assertThat(tag.getAttribute("rel"), is(equalTo("tooltip")));
    }

    @Test
    public void titleIsSet() {
        tester().startComponentInPage(newTooltip("text"));
        final TagTester tag = tester().getTagByWicketId(id());

        assertThat(tag.getAttribute("title"), is(equalTo("text")));
    }

    @Test
    public void isRenderedWithoutException() {
        tester().startComponentInPage(newTooltip("text"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }
    
    @Test
    public void labelIsLocalized() {
        tester().startComponentInPage(new MyContainer(id()).add(new TooltipBehavior(new ResourceModel("label"))));
        final TagTester tag = tester().getTagByWicketId(id());
        assertThat(tag.getAttribute("title"), is(equalTo("bar")));
    }

    /**
     * creates a new tooltip
     *
     * @param tooltip the tooltip content
     * @return new component that has a tooltip behavior
     */
    private Component newTooltip(final String tooltip) {
        return new WebMarkupContainer(id()).add(new TooltipBehavior(Model.of(tooltip)));
    }
}
