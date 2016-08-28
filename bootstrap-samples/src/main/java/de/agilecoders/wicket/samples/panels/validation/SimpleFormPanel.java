package de.agilecoders.wicket.samples.panels.validation;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.samples.components.base.StateSelect;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author Alexey Volkov
 * @since 18.09.2014
 */
public class SimpleFormPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private class SimpleForm extends BootstrapForm<String> {

        private static final long serialVersionUID = 1L;

        /**
         * @param componentId component id
         */
        public SimpleForm(String componentId) {
            super(componentId);
            type(FormType.Horizontal);
            add(new RequiredTextField<>("required", Model.of("")).setLabel(Model.of("Username")));
            add(new PasswordTextField("pass", Model.of("")).setLabel(Model.of("Password")));
            add(new DateTextField("date", Model.of()).setRequired(true).setLabel(Model.of("Date")));
            add(new AjaxButton("submitBtn") {
                @Override
                protected void onSubmit(AjaxRequestTarget target) {
                    super.onSubmit(target);
                    SimpleFormPanel.this.onSubmit(target);
                }

                @Override
                protected void onError(AjaxRequestTarget target) {
                    super.onError(target);
                    target.add(form);
                }
            });
        }
    }

    private final SimpleForm form;

    /**
     * @param componentId component id
     */
    public SimpleFormPanel(String componentId) {
        super(componentId);
        form = new SimpleForm("form");
        add(form);
    }

    /**
     * with ajax
     *
     * @return current instance
     */
    public SimpleFormPanel withSelect() {
        WebMarkupContainer select = new WebMarkupContainer("select");
        select.add(new StateSelect("state").setRequired(true));
        form.add(select);
        return this;
    }

    /**
     * with ajax
     *
     * @return current instance
     */
    public SimpleFormPanel withAjax() {
        form.add(new AjaxFormSubmitBehavior("submit") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(form);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                target.add(form);
                SimpleFormPanel.this.onSubmit(target);
            }
        });
        return this;
    }

    protected void onSubmit(AjaxRequestTarget target) {
    }
}
