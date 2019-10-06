package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Tests the {@link Adler32ResourceVersion}
 *
 * @author miha
 */
public class Adler32ResourceVersionTest extends ChecksumResourceVersionTest {

    static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new AbstractMap.SimpleEntry<>("a", "620062") },
                { new AbstractMap.SimpleEntry<>("a1", "f50093") },
                { new AbstractMap.SimpleEntry<>("a1b", "1ea00f5") },
                { new AbstractMap.SimpleEntry<>("a1bX", "337014d") },
                { new AbstractMap.SimpleEntry<>("a1bXl", "4f001b9") }
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    void checkChecksum(final Map.Entry<String, String> content) {
        check(content.getKey(), content.getValue());
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new Adler32ResourceVersion();
    }
}
