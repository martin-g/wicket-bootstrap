package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IOUtils;

/**
 * A File storage for the summer note editor.<br>
 * <br>
 * Example:
 * <pre>
 * <b>SummernoteStoredImageResourceReference:</b>
 * mountResource(SummernoteStoredImageResourceReference.SUMMERNOTE_MOUNT_PATH, new SummernoteStoredImageResourceReference("storageId"));
 * 
 * <b>Storage Initialization:</b>
 * SummernoteConfig.addStorage(new SummernoteFileStorage("storageId", new File("/Users/Someone")));
 * 
 * <b>Editor Config:</b>
 * SummernoteConfig summernoteConfig = new SummernoteConfig();
 * summernoteConfig.useStorageId("storageId");
 * </pre>
 * <b>Result: Images are stored into /User/Someone/storageId/&lt;imageName&gt;</b><br><br>
 * 
 * @author Tobias Soloschenko
 *
 */
public class SummernoteFileStorage implements SummernoteStorage {

    private File folder;

    private String id;

    public SummernoteFileStorage(String id, File folder) {
	this.folder = folder;
	this.id = id;
    }

    @Override
    public byte[] getContent(String imageName) {
	File file = new File(folder, id);
	File image = new File(file, imageName);
	FileInputStream fileInputStream = null;
	try {
	    fileInputStream = new FileInputStream(image);
	    return IOUtils.toByteArray(fileInputStream);
	} catch (FileNotFoundException e) {
	    throw new WicketRuntimeException("Error while reading file: " + image.getPath());
	} catch (IOException e) {
	    throw new WicketRuntimeException("Error while reading file: " + image.getPath());
	} finally {
	    IOUtils.closeQuietly(fileInputStream);
	}
    }

    @Override
    public void writeContent(String imageName, InputStream inputStream) {
	File file = new File(folder, id);
	File image = new File(file, imageName);
	FileOutputStream fileOutputStream = null;
	try {
	    fileOutputStream = new FileOutputStream(image);
	    IOUtils.copy(inputStream, fileOutputStream);
	} catch (FileNotFoundException e) {
	    throw new WicketRuntimeException("Error while writing file: " + image.getPath());
	} catch (IOException e) {
	    throw new WicketRuntimeException("Error while writing file: " + image.getPath());
	} finally {
	    IOUtils.closeQuietly(fileOutputStream);
	}
    }

    @Override
    public String getId() {
	return id;
    }
}
