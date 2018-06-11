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
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@Category(IntegrationTest.class)
public class FormGroupTest extends WicketApplicationTest {

    @Test
    public void isInstantiableWithoutError() {

        FormGroup group = new FormGroup("id");

        tester().startComponentInPage(group, Markup.of("<div wicket:id='id'>Group</div>"));
        tester().assertNoErrorMessage();
    }

    @Test(expected = MarkupException.class)
    public void tagNameIsAsserted() {

        FormGroup group = new FormGroup("id");

        tester().startComponentInPage(group, Markup.of("<span wicket:id='id'>Group</span>"));
    }

    @Test
    public void formGroupWithInput() {

        FormGroup group = new FormGroup("id");
        TextField<String> input = new TextField<String>("value");
        group.add(input);

        tester().startComponentInPage(group,
                Markup.of("<div wicket:id='id'><input type='text' wicket:id='value'/></div>"));
        tester().assertNoErrorMessage();
    }

    @Test
    public void formGroupSubmit() {
        final AtomicInteger updateModelCounter = new AtomicInteger(0);

        Model<FormData> model = Model.of(new FormData());
        model.getObject();

        Form<FormData> form = new Form<FormData>("form", new CompoundPropertyModel<FormData>(model));

        FormGroup group = new FormGroup("id");
        form.add(group);

        TextField<String> input = new TextField<String>("value") {
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
    public void formGroupLarge() {

        Model<FormData> model = Model.of(new FormData());
        model.getObject();

        Form<FormData> form = new Form<FormData>("form", new CompoundPropertyModel<FormData>(model));

        FormGroup group = new FormGroup("formGroup");
        group.size(FormGroup.Size.Large);
        form.add(group);

        TextField<String> input = new TextField<String>("value");
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
                form,
                Markup.of("<form wicket:id='form'><div wicket:id='formGroup'><input type='text' wicket:id='value'/></div></form>"));
        TagTester formGroupTester = tester().getTagByWicketId("formGroup");
        assertThat(formGroupTester.getAttribute("class"), containsString("form-group-lg"));
    }

    @Test
    public void formGroupSmall() {

        Model<FormData> model = Model.of(new FormData());
        model.getObject();

        Form<FormData> form = new Form<FormData>("form", new CompoundPropertyModel<FormData>(model));

        FormGroup group = new FormGroup("formGroup");
        group.size(FormGroup.Size.Small);
        form.add(group);

        TextField<String> input = new TextField<String>("value");
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
                form,
                Markup.of("<form wicket:id='form'><div wicket:id='formGroup'><input type='text' wicket:id='value'/></div></form>"));
        TagTester formGroupTester = tester().getTagByWicketId("formGroup");
        assertThat(formGroupTester.getAttribute("class"), containsString("form-group-sm"));
    }

    @Test
    public void formGroupSubmitValidation() {

        tester().getSession().setLocale(Locale.ENGLISH); // for the validation message

        Model<FormData> model = Model.of(new FormData());
        model.getObject();

        Form<FormData> form = new Form<FormData>("form", new CompoundPropertyModel<FormData>(model));

        FormGroup group = new FormGroup("id");
        form.add(group);

        TextField<String> input = new TextField<String>("value");
        input.setRequired(true);
        group.add(input);

        tester().startComponentInPage(
                form,
                Markup.of("<form wicket:id='form'><div wicket:id='id'><input type='text' wicket:id='value'/></div></form>"));
        FormTester formTester = tester().newFormTester("form", false);

        formTester.submit();

        tester().assertLabel("form:id:error", "&#039;value&#039; is required.");
        tester().assertContains("class=\".*is-invalid.*\""); //assert error CSS class is present
    }

    private class FormData implements Serializable {

        private static final long serialVersionUID = 1L;

        private String value;

        /**
         * @return Returns the value.
         */
        public String getValue() {

            return value;
        }

        /**
         * @param value The value to set.
         */
        public void setValue(String value) {

            this.value = value;
        }
    }
}
