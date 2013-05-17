package de.agilecoders.wicket.core.request.resource.caching.version;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Tests the {@link Adler32ResourceVersion}
 *
 * @author miha
 */
@RunWith(value = Parameterized.class)
public class Adler32ResourceVersionTest extends ChecksumResourceVersionTest {

    private final Map.Entry<String, String> content;

    public Adler32ResourceVersionTest(final Map.Entry<String, String> content) {
        this.content = content;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { new AbstractMap.SimpleEntry<String, String>("a", "620062") },
                { new AbstractMap.SimpleEntry<String, String>("a1", "f50093") },
                { new AbstractMap.SimpleEntry<String, String>("a1b", "1ea00f5") },
                { new AbstractMap.SimpleEntry<String, String>("a1bX", "337014d") },
                { new AbstractMap.SimpleEntry<String, String>("a1bXl", "4f001b9") }
        };
        return Arrays.asList(data);
    }

    @Test
    public void checkChecksum() {
        check(content.getKey(), content.getValue());
    }

    @Override
    protected ChecksumResourceVersion newChecksumResourceVersion() {
        return new Adler32ResourceVersion();
    }
}
