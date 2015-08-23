package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IOUtils;

/**
 * A File storage for the summer note editor.<br>
 * <br>
 * Example:
 *
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
 *
 * <b>Result: Images are stored into
 * /User/Someone/storageId/&lt;imageName&gt;</b><br>
 * <br>
 *
 * @author Tobias Soloschenko
 *
 */
public class SummernoteFileStorage implements SummernoteStorage {

    private final File folder;

    private final String id;

    /**
     * Creates a file storage which stores the images into the given folder and
     * subfolder of the given id
     *
     * @param id
     *            the id of the file storage which also is used as subfolder to
     *            store the images
     * @param folder
     *            the folder to store images
     */
    public SummernoteFileStorage(String id, File folder) {
        this.folder = folder;
        this.id = id;
    }

    /**
     * Gets image data form the file system
     *
     * @param imageName
     *            the image name
     * @return the image bytes
     */
    @Override
    public byte[] getContent(String imageName) {
        File file = new File(folder, id);
        // Create directories if not found
        if(!file.exists()){
            file.mkdirs();
        }
        File image = new File(file, imageName);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(image);
            return IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            throw new WicketRuntimeException("Error while reading file: " + image.getPath(), e);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
    }

    /**
     * Writes image data to the file system
     *
     * @param imageName
     *            the name of the image
     * @param inputStream
     *            the stream of the image
     */
    @Override
    public void writeContent(String imageName, InputStream inputStream) {
        File file = new File(folder, id);
        // Create directories if not found
        if(!file.exists()){
            file.mkdirs();
        }
        File image = new File(file, imageName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(image);
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            throw new WicketRuntimeException("Error while writing file: " + image.getPath(), e);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    /**
     * Gets the id of the storage
     *
     * @return the id of the storage
     */
    @Override
    public String getId() {
    return id;
    }
}
