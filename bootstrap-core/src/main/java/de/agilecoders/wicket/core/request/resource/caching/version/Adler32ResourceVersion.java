package de.agilecoders.wicket.core.request.resource.caching.version;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * A checksum resource version that uses {@link Adler32}.
 *
 * @author miha
 */
public class Adler32ResourceVersion extends ChecksumResourceVersion {
    @Override
    protected Checksum newChecksumBuilder() {
        return new Adler32();
    }
}
