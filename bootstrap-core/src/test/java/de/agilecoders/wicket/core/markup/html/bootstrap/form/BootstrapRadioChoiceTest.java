package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.google.common.collect.Lists;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;

@Category(IntegrationTest.class)
public class BootstrapRadioChoiceTest extends WicketApplicationTest {

    @Test
    public void radioChoice() {
        BootstrapRadioChoice<String> radio = new BootstrapRadioChoice<String>("id", Lists.newArrayList("One", "Two"));

        tester().startComponentInPage(radio);
        TagTester spanTester = tester().getTagByWicketId("id");

        TagTester divTester = spanTester.getChild("class", "radio");
        assertThat(divTester.getName(), is(equalTo("div")));

        TagTester labelTester = divTester.getChild("label");
        assertThat(labelTester.getName(), is(equalTo("label")));
        assertThat(labelTester.getAttribute("class"), is(nullValue()));
        assertThat(labelTester.getValue(), endsWith(" One"));

        TagTester radioTester = labelTester.getChild("type", "radio");
        assertThat(radioTester.getName(), is(equalTo("input")));

    }

    @Test
    public void inlineRadioChoice() {
        BootstrapRadioChoice<String> radio = new BootstrapRadioChoice<String>("id", Lists.newArrayList("One", "Two"));
        radio.setInline(true);

        tester().startComponentInPage(radio);
        TagTester spanTester = tester().getTagByWicketId("id");

        TagTester divTester = spanTester.getChild("class", "radio");
        assertThat(divTester.getName(), is(equalTo("div")));

        TagTester labelTester = divTester.getChild("label");
        assertThat(labelTester.getName(), is(equalTo("label")));
        assertThat(labelTester.getAttribute("class"), is(equalTo("radio-inline")));
    }
}
