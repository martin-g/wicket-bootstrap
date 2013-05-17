package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Tests the {@link CRC32ResourceVersion}
 *
 * @author miha
 */
@RunWith(value = Parameterized.class)
public class CRC32ResourceVersionTest extends ChecksumResourceVersionTest {

    private final Map.Entry<String, String> content;

    public CRC32ResourceVersionTest(final Map.Entry<String, String> content) {
        this.content = content;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new AbstractMap.SimpleEntry<String, String>("a", "e8b7be43") },
                { new AbstractMap.SimpleEntry<String, String>("a1", "6ce14823") },
                { new AbstractMap.SimpleEntry<String, String>("a1b", "d3b57fc3") },
                { new AbstractMap.SimpleEntry<String, String>("a1bX", "b50c103e") },
                { new AbstractMap.SimpleEntry<String, String>("a1bXl", "57d2d345") }
        };
        return Arrays.asList(data);
    }

    @Test
    public void checkChecksum() {
        check(content.getKey(), content.getValue());
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new CRC32ResourceVersion();
    }
}
