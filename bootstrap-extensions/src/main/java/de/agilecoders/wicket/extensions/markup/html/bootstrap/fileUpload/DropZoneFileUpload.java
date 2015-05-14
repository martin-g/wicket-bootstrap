package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileUpload;

import java.io.IOException;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IOUtils;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.References;

/**
 * Provides a modern file upload by using dropzone.js
 * 
 * Important: DropZoneFileUpload has to be placed into a bootstrap container!
 * 
 * @author Tobias Soloschenko
 */
public abstract class DropZoneFileUpload extends Panel
{

	private static final long serialVersionUID = 1L;

	private DropZoneFileUploadAjaxEventBehavior dropZoneFileUploadAjaxEventBehavior;

	private int maxFileSize = 5;

	private int parallelUploads = 1;

	private String acceptedFiles;

	private class DropZoneFileUploadAjaxEventBehavior extends AbstractDefaultAjaxBehavior
	{

		private static final long serialVersionUID = 1L;

		@Override
		protected void respond(AjaxRequestTarget target)
		{
			onUpload(target);
		}

		@Override
		protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
		{
			attributes.setMultipart(true);
		}
	}

	/**
	 * Creates a DropZoneFileUpload with the given id
	 * 
	 * @param id
	 *            the wicket id where the component is going to be rendered
	 */
	public DropZoneFileUpload(String id)
	{
		this(id, null);
	}

	/**
	 * Creates a DropZoneFileUpload with the given id
	 * 
	 * @param id
	 *            the wicket id where the component is going to be rendered
	 * @param model
	 *            the model of this component
	 */
	public DropZoneFileUpload(String id, IModel<?> model)
	{
		super(id, model);
		add(dropZoneFileUploadAjaxEventBehavior = new DropZoneFileUploadAjaxEventBehavior());
	}

	/**
	 * Initializes the drop zone with the required scripts
	 */
	@Override
	public void renderHead(IHeaderResponse response)
	{
		try
		{
			References.renderWithFilter(
				Bootstrap.getSettings(),
				response,
				JavaScriptReferenceHeaderItem.forReference(DropZoneFileUploadJavaScriptReference.instance()));
			String dropZoneScript = IOUtils.toString(DropZoneFileUploadJavaScriptReference.class.getResourceAsStream("js/dropzone_init.js"));
			dropZoneScript = dropZoneScript.replaceAll("%\\(callbackurl\\)",
				dropZoneFileUploadAjaxEventBehavior.getCallbackUrl().toString());
			dropZoneScript = dropZoneScript.replaceAll("%\\(maxfilesize\\)",
				Integer.toString(maxFileSize));
			dropZoneScript = dropZoneScript.replaceAll("%\\(paralleluploads\\)",
				Integer.toString(parallelUploads));
			dropZoneScript = dropZoneScript.replaceAll("%\\(acceptedfiles\\)",
				acceptedFiles != null ? "acceptedFiles: \"" + acceptedFiles + "\"," : "");
			response.render(JavaScriptHeaderItem.forScript(dropZoneScript, "dropzone" +
				getMarkupId()));
		}
		catch (IOException e)
		{
			throw new WicketRuntimeException("Error occured while loading the drop zone scripts", e);
		}
	}

	/**
	 * Gets the default max file size to be used for file uploads
	 * 
	 * @return the max file size in mb
	 */
	public int getMaxFileSize()
	{
		return maxFileSize;
	}

	/**
	 * Sets the default max file size to be used for file upload
	 * 
	 * @param maxFileSize
	 *            the max file size in mb to be used
	 */
	public void setMaxFileSize(int maxFileSize)
	{
		this.maxFileSize = maxFileSize;
	}

	/**
	 * Gets the number how many files can be uploaded parallel
	 * 
	 * @return the number of how many files can be uploaded parallel
	 */
	public int getParallelUploads()
	{
		return parallelUploads;
	}

	/**
	 * Sets the number how many files can be uploaded parallel
	 * 
	 * @param parallelUploads
	 *            the number of how many files can be uploaded parallel
	 */
	public void setParallelUploads(int parallelUploads)
	{
		this.parallelUploads = parallelUploads;
	}

	/**
	 * The file types that are accepted for this upload
	 * 
	 * @return the file types which are accepted for this upload
	 */
	public String getAcceptedFiles()
	{
		return acceptedFiles;
	}

	/**
	 * Sets the types that are accepted for this upload.<br/>
	 * <br/>
	 * e.g.: "image/*,application/pdf,.psd"<br/>
	 * or simply: "*"
	 * 
	 * @param acceptedFiles
	 *            the file types which are accepted for this upload
	 */
	public void setAcceptedFiles(String acceptedFiles)
	{
		this.acceptedFiles = acceptedFiles;
	}

	/**
	 * Needs to be overridden to handle file upload
	 * 
	 * @param target
	 *            the target to get the upload from
	 */
	protected abstract void onUpload(AjaxRequestTarget target);
}
