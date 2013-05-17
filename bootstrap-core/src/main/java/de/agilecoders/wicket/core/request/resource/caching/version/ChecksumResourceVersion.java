package de.agilecoders.wicket.core.request.resource.caching.version;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.apache.wicket.Application;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.util.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.zip.Checksum;

/**
 * computes the checksum of a {@link org.apache.wicket.request.resource.caching.IStaticCacheableResource} using
 * a special {@link Checksum} implementation and uses it as a version string.
 * <p/>
 * Using a {@link Checksum} is faster than using a {@link java.security.MessageDigest} but it has
 * a higher collision rate.
 *
 * @author miha
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

    public byte[] computeDebugDigest(InputStream inputStream) throws IOException {
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

        long value = checksum.getValue();
        String hex = Long.toHexString(value);
        String hexWithoutNonPrintable = stripNonVisibleChars(hex);
        byte[] byteArray = hexWithoutNonPrintable.getBytes(charset());

        System.out.println("=======================");
        System.out.println(value);
        System.out.println(hex);
        System.out.println(hexWithoutNonPrintable);
        System.out.println(byteArray);
        System.out.println(charset());
        System.out.println("-----------------------");
        for (byte b : byteArray) {
            System.out.print(b);
            System.out.print(" = ");
            System.out.print(Byte.toString(b));
            System.out.println("");
        }
        System.out.println("=======================");


        return byteArray;
    }
}
