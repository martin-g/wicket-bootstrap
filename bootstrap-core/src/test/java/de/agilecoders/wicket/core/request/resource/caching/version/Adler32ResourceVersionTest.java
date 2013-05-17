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
        check("a1b", "1ea00f5");
        check("a1bX", "337014d");
        check("a1bXl", "4f001b9");
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new Adler32ResourceVersion();
    }
}
