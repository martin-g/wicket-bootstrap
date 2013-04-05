package de.agilecoders.wicket.less;

<<<<<<< HEAD
=======
import de.agilecoders.wicket.BootstrapLess;
import org.apache.wicket.Application;
>>>>>>> 7b7904945bf891c04f5053f26f7f2b1f0976e735
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.Connections;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Represents a less resource.
 *
 * @author miha
 */
public class LessResource implements ILessResource {
    private static final Logger LOG = LoggerFactory.getLogger(LessResource.class);

    private final Class<?> scope;
    private final String path;
    private final Time lastModified;

    /**
     * Construct.
     *
     * @param scope The scope class to detect the resource
     * @param path  absolute path to resource
     */
    public LessResource(final Class<?> scope, final String path) {
        this.scope = scope;
        this.path = path;
        this.lastModified = detectLastModificationTime();
    }

    /**
     * @return last modification time of representing resource, if resource can't be found
     *         {@link Time#START_OF_UNIX_TIME} will be used.
     */
    private Time detectLastModificationTime() {
        Time modified;
        try {
            modified = Connections.getLastModified(getUrl());
        } catch (IOException e) {
            LOG.warn("can't detect last modification time: {}", path, e);

            modified = Time.START_OF_UNIX_TIME;
        } catch (RuntimeException e) {
            LOG.warn("can't detect last modification time: {}", path, e);

            modified = Time.START_OF_UNIX_TIME;
        }

        return modified;
    }

    /**
     * @return {@link URL} instance to given resource
     */
    private URL getUrl() {
        final URL url = ClasspathResourceScope.class.equals(scope) ? null : scope.getResource(path);

        return url != null ? url : Thread.currentThread().getContextClassLoader().getResource(path);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Time getLastModificationTime() {
        return lastModified;
    }

    @Override
    public String asText() {
        InputStream stream = null;

        try {
            stream = getInputStream();

            return IOUtils.toString(stream, charset());
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * @return resource charset (charset is used from LessSettings)
     */
    private String charset() {
        if (Application.exists()) {
            return BootstrapLess.getSettings().getCharset().name();
        } else {
            return Charset.defaultCharset().name();
        }
    }

    @Override
    public boolean exists() {
        return scope.getResource(path) != null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new BufferedInputStream(getUrl().openStream());
    }

    @Override
    public LessResource getRelative(String subPath) {
        final String parentPath = findParentName(path, subPath);

        try {
            return new LessResource(scope, parentPath);
        } catch (RuntimeException e) {
            LOG.error("can't load: {}; {}", parentPath, e.getMessage());

            throw new WicketRuntimeException("error loading: " + parentPath, e);
        }
    }

    /**
     * extracts the upper folder name
     *
     * @param base The base path
     * @param path the path
     * @return highest folder name
     */
    private String findParentName(String base, String path) {
        final int index = base.lastIndexOf('/');

        if (index >= 0) {
            return normalize(base.substring(0, index + 1) + path);
        } else {
            return path;
        }
    }

    /**
     * normalizes a given path
     * <p/>
     * TODO miha: replace this code with java7 Path class
     *
     * @param uri The path to normalize
     * @return normalized path
     */
    private String normalize(String uri) {
        try {
            return new URI(uri).normalize().getPath();
        } catch (URISyntaxException e) {
            throw new WicketRuntimeException(e);
        }
    }

    @Override
    public String getName() {
        final int index = path.lastIndexOf('/');

        return path.substring(index + 1);
    }

    @Override
    public File toFile() {
        return new File(getUrl().toString());
    }
}
