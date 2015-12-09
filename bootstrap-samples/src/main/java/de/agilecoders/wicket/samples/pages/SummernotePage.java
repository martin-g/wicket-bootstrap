package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteAjaxButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteEditor;
import de.agilecoders.wicket.samples.WicketApplication;


@MountPath(value = "/summernote")
public class SummernotePage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public SummernotePage(PageParameters parameters) {
        super(parameters);

        addForm("", false);
        addForm("AirMode", true);
    }

    private void addForm(String idSuffix, final boolean isAirMode) {
        final NotificationPanel feedback = new NotificationPanel("feedback" + idSuffix);
        feedback.setOutputMarkupId(true);

        Form<Void> form = new Form<>("form" + idSuffix);

        SummernoteConfig summernoteConfig = new SummernoteConfig();
        summernoteConfig.useStorageId(WicketApplication.STORAGE_ID);
        summernoteConfig.withHeight(50);
        summernoteConfig.withAirMode(isAirMode);
        summernoteConfig.withPlaceholder("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.");

        final IModel<String> summernoteModel = Model.of();

        final SummernoteEditor summernoteEditor = new SummernoteEditor("summernote" + idSuffix, summernoteModel, summernoteConfig);
        form.add(summernoteEditor);
        form.add(new SummernoteAjaxButton("submit" + idSuffix, summernoteEditor) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                info("Submitted text: \n" + summernoteModel.getObject());
                target.add(feedback);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                if (isAirMode) {
                    super.updateAjaxAttributes(attributes);
                }
                // else act as a normal AjaxButton to prevent double submit in non-AirMode
            }
        });

        add(feedback, form);
    }

}
