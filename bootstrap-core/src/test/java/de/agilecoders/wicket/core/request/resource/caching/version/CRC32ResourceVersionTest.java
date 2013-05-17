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
        check("a1b", "d3b57fc3");
        check("a1bX", "b50c103e");
        check("a1bXl", "57d2d345");
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new CRC32ResourceVersion();
    }
}
