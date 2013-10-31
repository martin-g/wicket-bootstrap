package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.ExtraSmallSpanType;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.LargeScreenSpanType;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.tester.TagTester;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for InputBehavior
 */
public class InputBehaviorTest extends WicketApplicationTest {

    @Test
    public void withoutHeightSizeWithoutColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        tester().startPage(page);

        tester().assertContainsNot("<div class=\"col-");
        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertEquals("form-control", cssClass);
    }

    @Test
    public void withHeightSizeWithoutColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(InputBehavior.Size.Large);
        tester().startPage(page);

        tester().assertContainsNot("<div class=\"col-");
        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.containsString("input-lg"));
    }

    @Test
    public void withoutHeightSizeWithColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(InputBehavior.Size.Small);
        page.inputBehavior.size(LargeScreenSpanType.SPAN11);
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.containsString("input-sm"));

        assertThat(tester().getLastResponseAsString(), Matchers.containsString("<div class=\"col-lg-11\""));
        assertThat(tester().getLastResponseAsString(), Matchers.containsString("</div"));
    }

    @Test
    public void withHeightSizeWithColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(ExtraSmallSpanType.SPAN10);
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.not(Matchers.containsString("input-lg")));

        assertThat(tester().getLastResponseAsString(), Matchers.containsString("<div class=\"col-xs-10\""));
        assertThat(tester().getLastResponseAsString(), Matchers.containsString("</div"));
    }

    private static class InputBehaviorPage extends WebPage implements IMarkupResourceStreamProvider {

        private InputBehavior inputBehavior = new InputBehavior();

        private InputBehaviorPage() {
            TextField<String> textField = new TextField<String>("id", Model.of("data"));
            add(textField);
            textField.setMarkupId("input");
            textField.add(inputBehavior);
        }

        @Override
        public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {
            return new StringResourceStream("<html><body><input wicket:id='id' type='text'/></body></html>");
        }
    }
}
