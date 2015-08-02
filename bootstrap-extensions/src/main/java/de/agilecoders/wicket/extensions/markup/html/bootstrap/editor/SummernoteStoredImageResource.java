package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.util.io.IOUtils;

public class SummernoteStoredImageResource extends DynamicImageResource {

    private static final long serialVersionUID = 1L;
    private File image;

    public SummernoteStoredImageResource(File image) {
	this.image = image;
    }

    @Override
    protected byte[] getImageData(Attributes attributes) {
	FileInputStream fileInputStream = null;
	try {
	    fileInputStream = new FileInputStream(image);
	    return IOUtils.toByteArray(fileInputStream);
	} catch (IOException e) {
	    throw new WicketRuntimeException("Error while reading the file for the response", e);
	} finally {
	    IOUtils.closeQuietly(fileInputStream);
	}
    }
}
