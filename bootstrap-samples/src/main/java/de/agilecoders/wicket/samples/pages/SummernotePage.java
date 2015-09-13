package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.editor.SummernoteEditor;
import de.agilecoders.wicket.samples.WicketApplication;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;


@MountPath(value = "/summernote")
public class SummernotePage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public SummernotePage(PageParameters parameters) {
        super(parameters);

        final NotificationPanel feedback = new NotificationPanel("feedback");
        feedback.setOutputMarkupId(true);

        Form<Void> form = new Form<>("form");

        SummernoteConfig summernoteConfig = new SummernoteConfig();
        summernoteConfig.useStorageId(WicketApplication.STORAGE_ID);
        
        final IModel<String> summernoteModel = new Model<String>("<span style='font-weight: bold;'>bold</span> <span>normal</span>");
		
        final SummernoteEditor summernoteEditor = new SummernoteEditor("summernote", summernoteModel, summernoteConfig);
        form.add(summernoteEditor);
        form.add(new AjaxButton("submit") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                info("Submitted text: \n" + summernoteModel.getObject());
                target.add(feedback);
            }
        });

        add(feedback, form);
    }

}
