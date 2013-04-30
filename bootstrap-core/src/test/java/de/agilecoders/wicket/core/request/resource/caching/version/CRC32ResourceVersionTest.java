package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.Test;

/**
 * Tests the {@link de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion}
 *
 * @author miha
 */
public class CRC32ResourceVersionTest extends ChecksumResourceVersionTest {

    @Test
    public void checkChecksum() {
        check("a", "e8b7be43");
        check("a1", "6ce14823");
        check("a1$", "4c0a9b66");
        check("a1$-", "33407d0e");
        check("a1$-&", "e784070a");
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new CRC32ResourceVersion();
    }
}
