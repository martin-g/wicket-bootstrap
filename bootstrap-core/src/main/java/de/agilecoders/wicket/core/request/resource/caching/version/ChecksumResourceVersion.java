package de.agilecoders.wicket.core.request.resource.caching.version;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Application;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.util.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.zip.Checksum;

/**
 * #### Description
 *
 * computes the checksum of a {@link org.apache.wicket.request.resource.caching.IStaticCacheableResource} using
 * a special {@link Checksum} implementation and uses it as a version string.
 * <p/>
 * Using a {@link Checksum} is faster than using a {@link java.security.MessageDigest} but it has
 * a higher collision rate.
 */
public abstract class ChecksumResourceVersion extends MessageDigestResourceVersion {
    private static final Pattern NON_PRINTABLE = Pattern.compile("[\\x00\\x08\\x0B\\x0C\\x0E-\\x1F]");

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

            if (StringUtils.isNotBlank(charset)) {
                return Charset.forName(charset);
            }
        }

        return StandardCharsets.UTF_8;
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

            throw e;
        } finally {
            IOUtils.close(inputStream);
        }

        return stripNonVisibleChars(Long.toHexString(checksum.getValue())).getBytes(charset());
    }

    /**
     * strips all non-printable characters from given hex checksum
     *
     * @param checksum the checksum to clean up
     * @return checksum without non-printable characters
     */
    private String stripNonVisibleChars(String checksum) {
        return NON_PRINTABLE.matcher(checksum).replaceAll("");
    }
}
