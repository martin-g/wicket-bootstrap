package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.Model;

public class ModalTestPanel extends Modal<String>
{
    public ModalTestPanel(String id)
    {
        super(id);

        setOutputMarkupId(true);

        Form<Void> form = new Form<Void>("form");
        form.add(new RequiredTextField<>("title"));

        addButton(new AjaxLink<Void>(Modal.BUTTON_MARKUP_ID)
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                close(target);
            }
        }.setBody(Model.of("Save")));
        add(form);
    }
}
