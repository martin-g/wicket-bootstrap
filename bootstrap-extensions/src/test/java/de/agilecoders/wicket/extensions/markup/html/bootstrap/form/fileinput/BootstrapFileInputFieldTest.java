package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.fileinput;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.form.Form;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class BootstrapFileInputFieldTest extends WicketApplicationTest {

    /**
     * A test case for https://github.com/l0rdn1kk0n/wicket-bootstrap/pull/406
     */
    @Test
    public void showUploadAjaxFormSubmitBehavior() {
        Form form = new Form("form");
        BootstrapFileInputField inputField = new BootstrapFileInputField("field");
        inputField.getConfig().showUpload(false);
        form.add(inputField);
        startComponentInPage(form);

        assertSubmitBehavior(inputField, 0);

        inputField.getConfig().showUpload(true);
        startComponentInPage(form);
        assertSubmitBehavior(inputField, 1);

        inputField.getConfig().showUpload(false);
        startComponentInPage(form);
        assertSubmitBehavior(inputField, 0);
    }

    private void assertSubmitBehavior(BootstrapFileInputField inputField, int expectedNumber) {
        assertThat(inputField.getBehaviors(AjaxFormSubmitBehavior.class).size(), is(expectedNumber));
    }

    private void startComponentInPage(Form form) {
        tester().startComponentInPage(form, Markup.of("<form wicket:id='form'><input wicket:id='field' type='file'></form>"));
    }
}
