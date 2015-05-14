package de.agilecoders.wicket.extensions.markup.html.bootstrap.fileUpload;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * dropzone.js javascript reference
 * 
 * @author Tobias Soloschenko
 *
 */
public class DropZoneFileUploadJavaScriptReference extends JavaScriptResourceReference
{

	private static final long serialVersionUID = 1L;

	/**
	 * Singleton instance of this reference
	 */
	private static final DropZoneFileUploadJavaScriptReference INSTANCE = new DropZoneFileUploadJavaScriptReference();


	/**
	 * @return the single instance of the resource reference
	 */
	public static DropZoneFileUploadJavaScriptReference instance()
	{
		return INSTANCE;
	}


	/**
	 * Private constructor.
	 */
	private DropZoneFileUploadJavaScriptReference()
	{
		super(DropZoneFileUploadJavaScriptReference.class, "js/dropzone.js");
	}
}
