package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.samples.components.basecss.DatePickerModal;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
public class DatePickerPage extends BasePage {
    private static final long serialVersionUID = 1L;
    private Date date = null;
    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public DatePickerPage(PageParameters parameters) {
        super(parameters);

        add(newDefaultDatePicker("default"),
            new Code("default-html-code", Model.of("//HTML\n<form><input wicket:id=\"default\"></form>")).setShowLineNumbers(true),
            new Code("default-java-code", Model.of("//JAVA\nadd(new DateTextField(\"default\"));")).setShowLineNumbers(true));

        add(newDatePicker("birthday", new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.TRUE).withStartDate(LocalDateTime.now().withYear(1900)).autoClose(true).withView(DateTextFieldConfig.View.Decade)),
            new Code("birthday-html-code", Model.of("//HTML\n<form><input wicket:id=\"birthday\"></form>")).setShowLineNumbers(true),
            new Code("birthday-java-code", Model.of("//JAVA\nadd(new DateTextField(\"birthday\",\n\t\t new DateTextFieldConfig()\n"
                                                    + "\t\t\t.autoClose(true)\n"
                                                    + "\t\t\t.withView(DateTextFieldConfig.View.Decade)\n"
                                                    + "\t\t\t.showTodayButton(true)\n"
                                                    + "\t\t\t.withStartDate(new DateTime().withYear(1900));")).setShowLineNumbers(true));

        add(newDatePicker("language", new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage("es")),
            new Code("language-html-code", Model.of("//HTML\n<form><input wicket:id=\"language\"></form>")).setShowLineNumbers(true),
            new Code("language-java-code", Model.of("//JAVA\nadd(new DateTextField(\"language\",\n\t\t new DateTextFieldConfig()\n"
                                                    + "\t\t\t.autoClose(true)\n"
                                                    + "\t\t\t.withLanguage(\"es\")\n"
                                                    + "\t\t\t.showTodayButton(true);")).setShowLineNumbers(true));

        DatePickerModal modal = new DatePickerModal("modal");
        modal.show(false);
        modal.size(Modal.Size.Small);
        modal.setUseKeyboard(true);
        BootstrapButton modalButton = new BootstrapButton("modal-opener", Buttons.Type.Secondary);
        modalButton.setLabel(Model.of("Open Modal Dialog"));
        modal.addOpenerAttributesTo(modalButton);

        add(modal, modalButton);

        final Label selectedDate = new Label("selectedDate", new IModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return date != null? date.toString() : "--NO SELECTED DATE--";
            }
        });
        selectedDate.setOutputMarkupId(true);

        add(selectedDate);
        add(new DateTextField("ajax-default").addAjaxEvent(DateTextField.Event.changeDate, new DateTextField.IAjaxEventHandler() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onAjaxEvent(AjaxRequestTarget target, Date date, DateTextField.Event event) {
                DatePickerPage.this.date = date;
                target.add(selectedDate);
            }
        }));



        add(new Code("ajax-default-html-code", Model.of("//HTML\n<form><input wicket:id=\"ajax-default\"></form>\n" +
                " <p>\n" +
                "   Selected date: <span wicket:id=\"selectedDate\"></span>\n" +
                " </p>")).setShowLineNumbers(true),
            new Code("ajax-default-java-code", Model.of("//JAVA\nadd(new DateTextField(\"ajax-default\").addAjaxEvent(DateTextField.Event.changeDate, new DateTextField.IAjaxEventHandler() {\n" +
                "            @Override\n" +
                "            public void onAjaxEvent(AjaxRequestTarget target, Date date, DateTextField.Event event) {\n" +
                "                DatePickerPage.this.date = date;\n" +
                "                target.add(selectedDate);\n" +
                "            }\n" +
                "        }));")).setShowLineNumbers(true));
    }

    private Component newDatePicker(String markupId, DateTextFieldConfig dateTextFieldConfig) {
        return new DateTextField(markupId, dateTextFieldConfig);
    }

    private Component newDefaultDatePicker(String markupId) {
        return newDatePicker(markupId, new DateTextFieldConfig());
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
