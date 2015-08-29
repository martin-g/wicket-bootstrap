package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPicker;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkbox.bootstrapcheckbox.BootstrapCheckBoxPickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkboxx.CheckBoxX;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;


@MountPath(value = "/checkboxes")
public class CheckboxesPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public CheckboxesPage(PageParameters parameters) {
        super(parameters);

        addCheckboxPicker();
        addCheckboxX();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
    }

    private void addCheckboxPicker() {
        final NotificationPanel feedback = new NotificationPanel("checkBoxPickerFeedback");
        feedback.setOutputMarkupId(true);

        BootstrapCheckBoxPickerConfig config = new BootstrapCheckBoxPickerConfig();
        config
            .withOnClass("btn-info").withOffClass("btn-warning")
            .withOnIcon(FontAwesomeIconType.thumbs_up).withOffIcon(FontAwesomeIconType.thumbs_down)
            .withReverse(true)
            .withStyle(ButtonGroup.Size.ExtraSmall);

        final BootstrapCheckBoxPicker checkBoxPicker = new BootstrapCheckBoxPicker("checkboxPicker", Model.of(true), config);
        checkBoxPicker.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                info("Selected: " + checkBoxPicker.getModelObject());
                target.add(feedback);
            }
        });
        add(checkBoxPicker, feedback);
    }

    private void addCheckboxX() {
        final NotificationPanel feedback = new NotificationPanel("checkBoxXFeedback");
        feedback.setOutputMarkupId(true);

        CheckBoxX checkBoxX = new CheckBoxX("checkboxX", new Model<Boolean>(true)) {
            @Override
            protected void onChange(Boolean value, AjaxRequestTarget target) {
                super.onChange(value, target);

                String s;
                if (Boolean.FALSE.equals(value)) {
                    s = "unchecked";
                } else if (Boolean.TRUE.equals(value)) {
                    s = "checked";
                } else {
                    s = "undefined";
                }
                info("The selection is: " + s);
                target.add(feedback);
            }
        };

        Code code = new Code("linkCode",
                             Model.of("CheckboxX checkboxX = new CheckboxX(\"checkboxX\", new Model<Boolean>(true)) {\n"
                                      + "  @Override\n" + "  protected void onChange(Boolean value, AjaxRequestTarget target) {\n"
                                      + "    info(\"The selection is: \" + value);\n" + "    target.add(feedback);\n" + "  }\n"
                                      + "};"));

        add(checkBoxX, feedback, code);
    }
}
