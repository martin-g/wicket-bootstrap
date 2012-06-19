package de.agilecoders.wicket.examples.ui.demo.form;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedAjaxButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedButton;
import de.agilecoders.wicket.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.markup.html.bootstrap.form.ControlGroupBorder;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.Row;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import java.io.Serializable;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class HorizontalFormRow extends Row {
    private Person person;

    public HorizontalFormRow() {
        person = new Person();
        Form form = new BootstrapForm<Person>("form", new CompoundPropertyModel<Person>(person));

        final ControlGroupBorder nameBorder = new ControlGroupBorder("nameBorder").label(Model.of("Name:")).help(Model.of("add your name"));
        TextField<String> nameField = new TextField<String>("name");
        nameField.setRequired(true);
        nameField.add(StringValidator.minimumLength(5));
        nameBorder.add(nameField);
        form.add(nameBorder);

        TypedButton button = new TypedButton("button", ButtonType.Primary);

        form.add(button);
        add(form);
    }

    private static class Person implements Serializable {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
