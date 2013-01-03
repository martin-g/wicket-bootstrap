package de.agilecoders.wicket.less;

import org.apache.wicket.util.io.IOUtils;
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

    private ByteArrayInputStream inputStream;
    private Bytes length;

    /**
     * Construct.
     *
     * @param lessFile the last modification time
     */
    public AbstractCompiledResource(final ICombinedLessResource lessFile) {
        this.lessFile = lessFile;
    }

    @Override
    public final Time getLastModificationTime() {
        return lessFile.getLastModificationTime();
    }

    /**
     * compile {@link ICombinedLessResource} and store result internally.
     */
    private synchronized void initialize() {
        if (length == null) {
            final byte[] data = compile(lessFile);

            inputStream = new ByteArrayInputStream(data);
            length = Bytes.bytes(data.length);
        }
    }

    @Override
    public final Bytes length() {
        initialize();

        return length;
    }

    @Override
    public final InputStream getInputStream() {
        initialize();

        return inputStream;
    }

    @Override
    public void close() throws IOException {
        IOUtils.close(inputStream);
    }

    /**
     * compiles and returns less content
     *
     * @return the compiled less content
     */
    protected abstract byte[] compile(final ICombinedLessResource lessFile);
}