package de.agilecoders.wicket.less;

import org.apache.wicket.util.time.Time;

import java.io.File;
import java.io.InputStream;

/**
 * Representation of a less resource
 *
 * @author miha
 */
public interface ILessResource {

    /**
     * @return the absolute path as string.
     */
    String getPath();

    /**
     * @return the last modified date (including all children)
     */
    // TODO miha Sync the name of this method with
    // de.agilecoders.wicket.less.ICombinedLessResource.getLastModificationTime()
    Time lastModified();

    /**
     * @return the resource as text (including all children)
     */
    String asText();

    /**
     * @return true, if resource exists on filesystem
     */
    boolean exists();

    /**
     * @return the content stream of this resource
     */
    InputStream getInputStream();

    /**
     * creates a path relative to current that points to a new resource
     *
     * @param subPath the sub path relative to current
     * @return new resource
     */
    LessResource getRelative(String subPath);

    /**
     * @return the file name of this resource
     */
    String getName();

    /**
     * @return this resource as file
     */
    File toFile();

    String LESS_EXTENSION = ".less";
    String LESSCSS_EXTENSION = ".less.css";
    String LESSCSSMIN_EXTENSION = ".less.min.css";
}
