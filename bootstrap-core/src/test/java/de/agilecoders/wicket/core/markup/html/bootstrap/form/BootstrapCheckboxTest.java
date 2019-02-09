package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.junit.jupiter.api.Test;

@IntegrationTest
public class BootstrapCheckboxTest extends WicketApplicationTest {

    @Test
    public void checkbox() {
        String labelContent = "Label";
        BootstrapCheckbox checkbox = new BootstrapCheckbox("id", Model.of(Boolean.FALSE), Model.of(labelContent));

        tester().startComponentInPage(checkbox);

        TagTester checkboxTester = tester().getTagByWicketId("checkbox");
        assertThat(checkboxTester.getName(), is(equalTo("input")));
        assertThat(checkboxTester.getAttribute("type"), is(equalTo("checkbox")));

        TagTester labelTester = tester().getTagByWicketId("label");
        assertThat(labelTester.getValue(), is(equalTo(labelContent)));
    }

    @Test
    public void inlineCheckbox() {
        BootstrapCheckbox checkbox = new BootstrapCheckbox("id", Model.of(Boolean.FALSE)).setInline(true);

        tester().startComponentInPage(checkbox);
        TagTester container = tester().getTagByWicketId("wrapper");
        assertThat(container.getAttribute("class"), is(containsString("form-check-inline")));
    }
}
