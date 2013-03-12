package de.agilecoders.wicket.less;

import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Time;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * An {@link AbstractLessCompiler} that does nothing except returning the current
 * less content without compilation.
 *
 * @author miha
 */
public class NoOpLessCompiler extends AbstractLessCompiler {

    @Override
    public ICompiledResource compile(final ILessResource lessResource) {
        return new CompiledResource(lessResource, getCharset());
    }

    /**
     * A simple {@link ICompiledResource} implementation
     */
    public static final class CompiledResource implements ICompiledResource {

        private final byte[] bytes;
        private final Time modificationTime;

        /**
         * Construct.
         *
         * @param lessResource the less resource that holds the less content
         * @param charset
         */
        public CompiledResource(final ILessResource lessResource, final Charset charset) {
            this.bytes = lessResource.asText().getBytes(charset);
            this.modificationTime = lessResource.getLastModificationTime();
        }

        @Override
        public Time getModificationTime() {
            return modificationTime;
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(bytes);
        }

        @Override
        public Bytes length() {
            return Bytes.bytes(bytes.length);
        }

        @Override
        public void close() throws IOException {
            // nothing to do here
        }
    }
}
