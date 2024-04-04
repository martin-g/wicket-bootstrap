package de.agilecoders.wicket.core.request.resource.caching.version;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * #### Description
 *
 * A checksum resource version that uses {@link CRC32}.
 */
public class CRC32ResourceVersion extends ChecksumResourceVersion {
    @Override
    protected Checksum newChecksumBuilder() {
        return new CRC32();
    }
}
