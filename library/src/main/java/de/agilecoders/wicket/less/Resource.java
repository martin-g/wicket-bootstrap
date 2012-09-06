package de.agilecoders.wicket.less;

import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.Connections;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO: document
 * TODO: Can this reuse Wicket's PackageResourceStream ?!
 *
 * @author miha
 * @version 1.0
 */
public class Resource {

    private final Class scope;
    private final String path;
    private final Time lastModified;

    /**
     * Construct.
     *
     * @param scope The scope class to detect the resource
     * @param path  absolute path to resource
     */
    public Resource(final Class scope, final String path) {
        this.scope = scope;
        this.path = path;

        try {
            this.lastModified = Connections.getLastModified(scope.getResource(path));
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
    }

    /**
     * @return the absolute path as string.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the last modified date (including all children)
     */
    public Time lastModified() {
        return lastModified;
    }

    /**
     * @return the resource as text (including all children)
     */
    public String asText() {
        InputStream stream = null;

        try {
            stream = getInputStream();

            return IOUtils.toString(stream,
                                    Bootstrap.getSettings(Application.get()).getBootstrapLessCompilerSettings().getCharset().name());
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * @return true, if resource exists on filesystem
     */
    public boolean exists() {
        return scope.getResource(path) != null;
    }

    /**
     * @return the content stream of this resource
     */
    public InputStream getInputStream() {
        return scope.getResourceAsStream(path);
    }

    public Resource getRelative(String subPath) {
        return new Resource(scope, findParentName(path, subPath));
    }

    private String findParentName(String base, String path) {
        final int index = Math.max(base.lastIndexOf('/'), 0);

        return resolve(base.substring(0, index + 1) + path);
    }

    private String resolve(String path) {
        while (path.contains("../")) {
            int index = path.indexOf("../");
            String start = findParentName(path.substring(0, index - 1), "");
            String end = path.substring(index + 3);

            path = start + end;
        }

        return path;
    }


    /**
     * @return the file name of this resource
     */
    public String getName() {
        final int index = path.lastIndexOf('/');

        return path.substring(index + 1);
    }

    public File toFile() {
        return new File(scope.getResource(getPath()).toString());
    }
}
