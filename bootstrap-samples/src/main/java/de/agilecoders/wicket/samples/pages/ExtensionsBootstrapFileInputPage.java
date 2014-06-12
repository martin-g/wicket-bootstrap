package de.agilecoders.wicket.samples.pages;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.fileinput.BootstrapFileUploadField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;

@MountPath(value = "/extensionsBootstrapFileInput")
public class ExtensionsBootstrapFileInputPage extends BasePage {

	public ExtensionsBootstrapFileInputPage(PageParameters parameters) {
		super(parameters);

		addBootstrapFileUploadDemo();
	}
	private void addBootstrapFileUploadDemo() {
		Form<Void> bootstrapFileUploadForm = new Form<Void>(
				"bootstrapFileUploadForm");
		bootstrapFileUploadForm.setOutputMarkupId(true);
		add(bootstrapFileUploadForm);

		IModel<List<FileUpload>> model = new IModel<List<FileUpload>>() {

			@Override
			public void detach() {
				// ignore
			}

			@Override
			public List<FileUpload> getObject() {
				// ignore
				return null;
			}

			@Override
			public void setObject(List<FileUpload> object) {
				// ignore
			}
		};
		BootstrapFileUploadField bootstrapFileInput = new BootstrapFileUploadField(
				"bootstrapFileinput", model);
		bootstrapFileUploadForm.add(bootstrapFileInput);
	}
	
	@Override
	protected boolean hasNavigation() {
		return false;
	}
}
