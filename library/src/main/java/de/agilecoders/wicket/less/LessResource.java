package de.agilecoders.wicket.less;

import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.Connections;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

        try {
            this.lastModified = Connections.getLastModified(Thread.currentThread().getContextClassLoader().getResource(path));
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
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

            return IOUtils.toString(stream,
                                    Bootstrap.getSettings(Application.get()).getBootstrapLessCompilerSettings().getCharset().name());
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    @Override
    public boolean exists() {
        return scope.getResource(path) != null;
    }

    @Override
    public InputStream getInputStream() {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
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
        final int index = Math.max(base.lastIndexOf('/'), 0);

        return normalize(base.substring(0, index + 1) + path);
    }

    /**
     * normalizes a given path
     *
     * @param path The path to normalize
     * @return normalized path
     */
    private String normalize(String path) {
        while (path.contains("../")) {
            int index = path.indexOf("../");
            String start = findParentName(path.substring(0, index - 1), "");
            String end = path.substring(index + 3);

            path = start + end;
        }

        return path;
    }

    @Override
    public String getName() {
        final int index = path.lastIndexOf('/');

        return path.substring(index + 1);
    }

    @Override
    public File toFile() {
        return new File(scope.getResource(getPath()).toString());
    }
}
