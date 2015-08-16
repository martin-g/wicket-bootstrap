package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import java.io.InputStream;

/**
 * Used to provide different implementations to store images. See example implementation SummernoteFileStorage
 * 
 * @author Tobias Soloschenko
 * 
 * @see SummernoteFileStorage
 *
 */
public interface SummernoteStorage {

    /**
     * Writes the content of the image
     * 
     * @param imageName
     *            the image name
     * @param inputStream
     *            the input stream of the image
     */
    void writeContent(String imageName, InputStream inputStream);

    /**
     * Gets the content of an image
     * 
     * @param imageName
     *            the image name
     * @return the content of the image as byte[]
     */
    byte[] getContent(String imageName);

    /**
     * The storage id
     * 
     * @return the id of this storage
     */
    String getId();

}
