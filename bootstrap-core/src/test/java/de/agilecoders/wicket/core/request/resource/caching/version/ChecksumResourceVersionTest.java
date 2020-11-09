package de.agilecoders.wicket.core.request.resource.caching.version;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * helper class for {@link ChecksumResourceVersion} tests.
 *
 * @author miha
 */
abstract class ChecksumResourceVersionTest {

    protected abstract ChecksumResourceVersion newChecksumResourceVersion();

    void check(final String input, final String expected) {
        final ChecksumResourceVersion version = newChecksumResourceVersion();

        try {
            final byte[] versionAsByteArray = version.computeDigest(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

            assertThat("checksum(" + input + "): " + expected + "; is: " + new String(versionAsByteArray),
                       versionAsByteArray,
                       is(equalTo(expected.getBytes(StandardCharsets.UTF_8))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
