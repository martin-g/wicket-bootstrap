package de.agilecoders.wicket.less;

import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Time;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Default implementation of {@link ICompiledResource}
 *
 * @author miha
 */
public abstract class AbstractCompiledResource implements ICompiledResource {

    private final ICombinedLessResource lessFile;

    private byte[] content;
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

    @Override
    public final Bytes length() {
        return length;
    }

    @Override
    public final InputStream getInputStream() {
        if (content == null) {
            content = compile(lessFile);
            length = Bytes.bytes(content.length);
        }

        return new ByteArrayInputStream(content);
    }

    /**
     * compiles and returns less content
     *
     * @return the compiled less content
     */
    protected abstract byte[] compile(final ICombinedLessResource lessFile);
}