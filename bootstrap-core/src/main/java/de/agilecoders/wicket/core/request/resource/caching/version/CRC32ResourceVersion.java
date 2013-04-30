package de.agilecoders.wicket.core.request.resource.caching.version;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * A checksum resource version that uses {@link CRC32}.
 *
 * @author miha
 */
public class CRC32ResourceVersion extends ChecksumResourceVersion {
    @Override
    protected Checksum newChecksumBuilder() {
        return new CRC32();
    }
}
