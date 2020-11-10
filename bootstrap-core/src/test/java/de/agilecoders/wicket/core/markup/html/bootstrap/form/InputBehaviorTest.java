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
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for InputBehavior
 */
class InputBehaviorTest extends WicketApplicationTest {

    @Test
    void withoutHeightSizeWithoutColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        tester().startPage(page);

        tester().assertContainsNot("<div class=\"col-");
        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertEquals("form-control", cssClass);
    }

    @Test
    void withHeightSizeWithoutColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(InputBehavior.Size.Large);
        tester().startPage(page);

        tester().assertContainsNot("<div class=\"col-");
        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.containsString("form-control-lg"));
    }

    @Test
    void withoutHeightSizeWithColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(InputBehavior.Size.Small);
        page.inputBehavior.size(LargeScreenSpanType.SPAN11);
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.containsString("form-control-sm"));

        assertThat(tester().getLastResponseAsString(), Matchers.containsString("<div class=\"col-lg-11\""));
        assertThat(tester().getLastResponseAsString(), Matchers.containsString("</div"));
    }

    @Test
    void withHeightSizeWithColumnSize() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.inputBehavior.size(ExtraSmallSpanType.SPAN10);
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("form-control"));
        assertThat(cssClass, Matchers.not(Matchers.containsString("form-control-lg")));

        assertThat(tester().getLastResponseAsString(), Matchers.containsString("<div class=\"col-10\""));
        assertThat(tester().getLastResponseAsString(), Matchers.containsString("</div"));
    }

    /**
     * Issue https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/307
     * Invisible input should not render the surrounding markup
     */
    @Test
    void invisibleInput() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.textField.setVisible(false);
        tester().startPage(page);

        assertThat(tester().getLastResponseAsString(), Matchers.not(Matchers.containsString("<div")));
        assertThat(tester().getLastResponseAsString(), Matchers.not(Matchers.containsString("</div")));
    }

    @Test
    void invalidInput() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.textField.error("Invalid input.");
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("is-invalid"));
    }

    @Test
    void validInput() {
        InputBehaviorPage page = new InputBehaviorPage();
        page.textField.success("Valid input.");
        tester().startPage(page);

        TagTester tagTester = tester().getTagById("input");
        String cssClass = tagTester.getAttribute("class");
        assertThat(cssClass, Matchers.containsString("is-valid"));
    }

    private static class InputBehaviorPage extends WebPage implements IMarkupResourceStreamProvider {

        private final InputBehavior inputBehavior = new InputBehavior();

        private final TextField<String> textField;

        private InputBehaviorPage() {
            this.textField = new TextField<>("id", Model.of("data"));
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
