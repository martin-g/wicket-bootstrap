package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link RichPopoverBehavior}
 *
 * @author miha
 */
public class RichPopoverBehaviorTest extends WicketApplicationTest {

    @Test
    public void simpleBodyComponentIsRendered() {
        startBehaviorInPage(new RichPopoverBehavior(Model.of("header")) {
            @Override
            public Component newBodyComponent(String markupId) {
                return new Label(markupId, "content");
            }
        });

        TagTester tag = tester().getTagByWicketId(id());
        assertThat(tag.getAttribute("data-content"), is(equalTo("<wicket:container wicket:id=\\\"compId\\\">content</wicket:container>")));
    }

    @Test
    public void complexBodyComponentIsRendered() {
        startBehaviorInPage(new RichPopoverBehavior(Model.of("header")) {
            @Override
            public Component newBodyComponent(String markupId) {
                return new ProgressBar(markupId, Model.of(50));
            }
        });

        TagTester tag = tester().getTagByWicketId(id());
        assertThat(tag.getAttribute("data-content"), is(equalTo("<wicket:div wicket:id=\\\"compId\\\" class=\\\"progress \\\"><wicket:panel>\\n            <div wicket:id=\\\"indicator\\\" class=\\\"bar\\\" style=\\\"width: 50%\\\"></div>\\n        </wicket:panel></wicket:div>")));
    }
}
