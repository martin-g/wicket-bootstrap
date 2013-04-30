package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.Test;

/**
 * Tests the {@link Adler32ResourceVersion}
 *
 * @author miha
 */
public class Adler32ResourceVersionTest extends ChecksumResourceVersionTest {

    @Test
    public void checkChecksum() {
        check("a", "620062");
        check("a1", "f50093");
        check("a1$", "1ac00b7");
        check("a1$-", "29000e4");
        check("a1$-&", "39a010a");
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new Adler32ResourceVersion();
    }
}
