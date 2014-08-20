package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable.XEditableBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/x-editable")
public class XEditablePage extends BasePage {

    private String value, reason;

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public XEditablePage(PageParameters parameters) {
        super(parameters);

        final Label valueLabel = new Label("newValue", new PropertyModel<String>(this, "value"));
        valueLabel.setOutputMarkupId(true);

        final Label reasonLabel = new Label("reason", new PropertyModel<String>(this, "reason"));
        reasonLabel.setOutputMarkupId(true);

        Label editableLabel = new Label("label", Model.of("X-Editable"));
        editableLabel.add(new XEditableBehavior() {

            @Override
            protected void onSave(AjaxRequestTarget target, String value) {
                XEditablePage.this.value = value;
                target.add(valueLabel);
            }

            @Override
            protected void onHidden(AjaxRequestTarget target, Reason reason) {
                XEditablePage.this.reason = reason.name();
                target.add(reasonLabel);
            }

            @Override
            protected boolean wantOnHiddenNotifications() {
                return true;
            }
        });

        add(editableLabel, valueLabel, reasonLabel);
    }

}
