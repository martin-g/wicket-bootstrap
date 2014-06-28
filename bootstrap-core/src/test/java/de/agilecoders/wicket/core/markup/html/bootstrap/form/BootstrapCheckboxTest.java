package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;

@Category(IntegrationTest.class)
public class BootstrapCheckboxTest extends WicketApplicationTest {

    @Test
    public void checkbox() {
        String labelContent = "Label";
        BootstrapCheckbox checkbox = new BootstrapCheckbox("id", Model.of(Boolean.FALSE), Model.of(labelContent));

        tester().startComponentInPage(checkbox);
        TagTester labelContainerTester = tester().getTagByWicketId("label");
        assertThat(labelContainerTester.getAttribute("class"), is(nullValue()));

        TagTester checkboxTester = tester().getTagByWicketId("checkbox");
        assertThat(checkboxTester.getName(), is(equalTo("input")));
        assertThat(checkboxTester.getAttribute("type"), is(equalTo("checkbox")));

        TagTester postLabelTester = tester().getTagByWicketId("post-label");
        assertThat(postLabelTester.getValue(), is(equalTo(labelContent)));
    }

    @Test
    public void inlineCheckbox() {
        BootstrapCheckbox checkbox = new BootstrapCheckbox("id", Model.of(Boolean.FALSE)).setInline(true);

        tester().startComponentInPage(checkbox);
        TagTester labelContainerTester = tester().getTagByWicketId("label");
        assertThat(labelContainerTester.getAttribute("class"), is(equalTo("checkbox-inline")));
    }
}
