package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UploadProgressBar;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link RichPopoverBehavior}
 *
 * @author miha
 */
class RichPopoverBehaviorTest extends WicketApplicationTest {

    @Test
    void simpleBodyComponentIsRendered() {
        startBehaviorInPage(new RichPopoverBehavior(Model.of("header")) {
            @Override
            public Component newBodyComponent(String markupId) {
                return new Label(markupId, "content");
            }
        });

        TagTester tag = tester().getTagByWicketId(id());
        assertThat(tag.getAttribute("data-content"), is(equalTo("<wicket:container wicket:id=\"compId\">content</wicket:container>")));
    }

    @Test
    void complexBodyComponentIsRendered() {
        startBehaviorInPage(new RichPopoverBehavior(Model.of("header")) {
            @Override
            public Component newBodyComponent(String markupId) {
                return new UploadProgressBar(markupId, new Form("dummy"), Model.of(50));
            }
        });

        TagTester tag = tester().getTagByWicketId(id());
        assertThat(tag.getAttribute("data-content"), startsWith("<wicket:div wicket:id=\"compId\" class=\"progress\"><wicket:panel>"));
        assertThat(tag.getAttribute("data-content"), endsWith("</wicket:panel></wicket:div>"));
        assertThat(tag.getAttribute("data-content"), containsString("<div wicket:id=\"bar\" class=\"progress-bar bg-secondary\" style=\"width: 50%\""));
    }
}
