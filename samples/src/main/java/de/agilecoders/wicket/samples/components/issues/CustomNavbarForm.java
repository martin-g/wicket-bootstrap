package de.agilecoders.wicket.samples.components.issues;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarForm;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 * Sample Navbar containing a search form.
 *
 * @author miha
 */
public class CustomNavbarForm extends NavbarForm {
    public CustomNavbarForm(String componentId) {
        super(componentId);

        add(newSearchField("search"),
            newSubmitButton("submit"));
    }

    private Component newSubmitButton(String markupId) {
        return new TypedButton(markupId, Model.of("Search"), ButtonType.Default);
    }

    private Component newSearchField(String markupId) {
        return new TextField<String>(markupId);
    }
}
