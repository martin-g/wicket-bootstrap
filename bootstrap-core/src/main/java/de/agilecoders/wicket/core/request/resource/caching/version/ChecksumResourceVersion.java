package de.agilecoders.wicket.core.request.resource.caching.version;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.apache.wicket.Application;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.util.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.zip.Checksum;

/**
 * computes the checksum of a {@link org.apache.wicket.request.resource.caching.IStaticCacheableResource} using
 * a special {@link Checksum} implementation and uses it as a version string.
 *
 * Using a {@link Checksum} is faster than using a {@link java.security.MessageDigest} but it has
 * a higher collision rate.
 *
 * @author miha
 */
public abstract class ChecksumResourceVersion extends MessageDigestResourceVersion {

    /**
     * @return a new {@link Checksum} instance
     */
    protected abstract Checksum newChecksumBuilder();

    /**
     * @return the default markup encoding or if no application is assigned: "UTF-8"
     */
    protected Charset charset() {
        if (Application.exists()) {
            final String charset = Application.get().getMarkupSettings().getDefaultMarkupEncoding();

            if (!Strings.isNullOrEmpty(charset)) {
                return Charset.forName(charset);
            }
        }

        return Charsets.UTF_8;
    }

    /**
     * @return the buffer size which is used to read the file content.
     */
    protected int bufferSize() {
        return 1024;
    }

    /**
     * compute checksum for resource stream
     *
     * @param inputStream input stream to compute checksum for
     * @return binary checksum
     * @throws IOException if there are any io problems
     */
    @Override
    protected byte[] computeDigest(final InputStream inputStream) throws IOException {
        final Checksum checksum = newChecksumBuilder();
        final byte[] bytes = new byte[bufferSize()];
        int len;

        try {
            while ((len = inputStream.read(bytes)) >= 0) {
                checksum.update(bytes, 0, len);
            }
        } catch (RuntimeException e) {
            checksum.reset();
            IOUtils.close(inputStream);

            throw e;
        }

        final String hash = new BigInteger(Long.toString(checksum.getValue())).toString(16);

        return hash.getBytes(charset());
    }
}
