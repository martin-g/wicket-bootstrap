package de.agilecoders.wicket.samples.components.issues;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarForm;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CustomNavbarForm extends NavbarForm {
    public CustomNavbarForm(String componentId) {
        super(componentId);

        add(newSearchField("search"),
            newSubmitButton("submit"));
    }

    private Component newSubmitButton(String markupId) {
        return new TypedButton(markupId, ButtonType.Default);
    }

    private Component newSearchField(String markupId) {
        return new TextField<String>(markupId);
    }
}
