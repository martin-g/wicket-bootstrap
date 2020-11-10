package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@IntegrationTest
class FormGroupTest extends WicketApplicationTest {

    @Test
    void isInstantiableWithoutError() {

        FormGroup group = new FormGroup("id");

        tester().startComponentInPage(group, Markup.of("<div wicket:id='id'>Group</div>"));
        tester().assertNoErrorMessage();
    }

    @Test
    void tagNameIsAsserted() {

        FormGroup group = new FormGroup("id");

        assertThrows(MarkupException.class, () ->
            tester().startComponentInPage(group, Markup.of("<span wicket:id='id'>Group</span>")));
    }

    @Test
    void formGroupWithInput() {

        FormGroup group = new FormGroup("id");
        TextField<String> input = new TextField<>("value");
        group.add(input);

        tester().startComponentInPage(group,
                Markup.of("<div wicket:id='id'><input type='text' wicket:id='value'/></div>"));
        tester().assertNoErrorMessage();
    }

    @Test
    void formGroupSubmit() {
        final AtomicInteger updateModelCounter = new AtomicInteger(0);

        Model<FormData> model = Model.of(new FormData());
        model.getObject();

        Form<FormData> form = new Form<>("form", new CompoundPropertyModel<>(model));

        FormGroup group = new FormGroup("id");
        form.add(group);

        TextField<String> input = new TextField<>("value") {
            private static final long serialVersionUID = 1L;

            @Override
            public void updateModel() {
                super.updateModel();
                updateModelCounter.incrementAndGet();
            }
        };
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
            form,
            Markup.of("<form wicket:id='form'><div wicket:id='id'><input type='text' wicket:id='value'/></div></form>"));

        TagTester formGroupTester = tester().getTagByWicketId("id");
        assertThat(formGroupTester.getAttribute("class"), containsString("form-group"));

        FormTester formTester = tester().newFormTester("form", false);
        formTester.setValue(input, "Hello World!");

        updateModelCounter.set(0);
        formTester.submit();

        tester().assertNoErrorMessage();
        tester().assertNoInfoMessage();
        assertThat(updateModelCounter.get(), is(1));
    }

    @Test
    void formGroupSubmitValidation() {

        tester().getSession().setLocale(Locale.ENGLISH); // for the validation message

        Model<FormData> model = Model.of(new FormData());
        Form<FormData> form = new Form<>("form", new CompoundPropertyModel<>(model));

        FormGroup group = new FormGroup("id");
        form.add(group);

        TextField<String> input = new TextField<>("value");
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
                form,
                Markup.of("<form wicket:id='form'><div wicket:id='id'><input type='text' wicket:id='value'/></div></form>"));
        FormTester formTester = tester().newFormTester("form", false);

        formTester.submit();

        tester().assertLabel("form:id:error", "&#039;value&#039; is required.");
        tester().assertContains("class=\"invalid-feedback\""); //assert invalid-feedback CSS class is present
    }

    @Test
    void formGroupSubmitValidationSuccess() {

        Model<FormData> model = Model.of(new FormData("test"));
        Form<FormData> form = new Form<>("form", new CompoundPropertyModel<>(model));

        FormGroup group = new FormGroup("id");
        form.add(group);

        TextField<String> input = new TextField<>("value") {
            @Override
            protected void onValid() {
                super.onValid();
                success("Value is valid.");
            }
        };
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
                form,
                Markup.of("<form wicket:id='form'><div wicket:id='id'><input type='text' wicket:id='value'/></div></form>"));
        FormTester formTester = tester().newFormTester("form", false);

        formTester.submit();

        tester().assertLabel("form:id:error", "Value is valid.");
        tester().assertContains("class=\"valid-feedback\""); //assert valid-feedback CSS class is present
    }

    private static class FormData implements Serializable {

        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unused")
        private String value;


        private FormData() {
            this(null);
        }

        private FormData(String value) {
            this.value = value;
        }
    }
}
