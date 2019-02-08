package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Tests the {@link CRC32ResourceVersion}
 *
 * @author miha
 */
public class CRC32ResourceVersionTest extends ChecksumResourceVersionTest {

    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new AbstractMap.SimpleEntry<>("a", "e8b7be43") },
                { new AbstractMap.SimpleEntry<>("a1", "6ce14823") },
                { new AbstractMap.SimpleEntry<>("a1b", "d3b57fc3") },
                { new AbstractMap.SimpleEntry<>("a1bX", "b50c103e") },
                { new AbstractMap.SimpleEntry<>("a1bXl", "57d2d345") }
        };
        return Arrays.asList(data);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void checkChecksum(final Map.Entry<String, String> content) {
        check(content.getKey(), content.getValue());
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new CRC32ResourceVersion();
    }
}
