package de.agilecoders.wicket.less;

import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Time;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Default implementation of {@link ICompiledResource}
 *
 * @author miha
 */
public abstract class AbstractCompiledResource implements ICompiledResource {

    private final ICombinedLessResource lessFile;

    private byte[] data = null;

    /**
     * Construct.
     *
     * @param lessFile the last modification time
     */
    public AbstractCompiledResource(final ICombinedLessResource lessFile) {
        this.lessFile = Args.notNull(lessFile, "lessFile");
    }

    @Override
    public final Time getLastModificationTime() {
        return lessFile.getLastModificationTime();
    }

    /**
     * compile {@link ICombinedLessResource} and store result internally.
     */
    private synchronized void initialize() {
        if (data == null) {
            data = compile(lessFile);
        }
    }

    @Override
    public final Bytes length() {
        initialize();

        return Bytes.bytes(data.length);
    }

    @Override
    public final InputStream getInputStream() {
        initialize();

        return new ByteArrayInputStream(data);
    }

    @Override
    public void close() throws IOException {
        // nothing to close here because of {@link java.io.ByteArrayInputStream#close()}
    }

    /**
     * compiles and returns less content
     *
     * @return the compiled less content
     */
    protected abstract byte[] compile(final ICombinedLessResource lessFile);
}