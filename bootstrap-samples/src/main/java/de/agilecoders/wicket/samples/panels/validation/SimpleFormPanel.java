package de.agilecoders.wicket.samples.panels.validation;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.samples.components.base.StateSelect;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import java.util.Date;

/**
 * @author Alexey Volkov
 * @since 18.09.2014
 */
public class SimpleFormPanel extends Panel {

    private static final long serialVersionUID = -3738010405913903578L;

    private static class SimpleForm extends BootstrapForm<String> {

        private static final long serialVersionUID = -3738010405913903578L;

        /**
         * @param componentId component id
         */
        public SimpleForm(String componentId) {
            super(componentId);
            type(FormType.Horizontal);
            add(new RequiredTextField<String>("required", Model.of("")));
            add(new PasswordTextField("pass", Model.of("")));
            add(new DateTextField("date", Model.<Date>of()).setRequired(true));
        }
    }

    private SimpleForm form;

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

            private static final long serialVersionUID = -7413005434537494275L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(form);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                target.add(form);
            }
        });
        return this;
    }
}
