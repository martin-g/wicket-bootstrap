package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import de.agilecoders.wicket.core.util.Json;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for serializing AbstractConfig to JSON
 */
public class AbstractConfigTest extends Assert {

    @Test
    public void simpleConfig() {
        assertEquals("{\"integer\":1,\"string\":\"1\"}", new SimpleConfig().toJsonString());
    }

    @Test
    public void nestedConfigs() {
        assertEquals("{\"testConfig\":{\"integer\":1,\"string\":\"1\"},\"string\":\"2\"}", new NestedConfig().toJsonString());
    }

    @Test
    public void rawValue() {
        assertEquals("{\"raw\":Hogan}", new RawValueConfig().toJsonString());
    }

    private static class RawValueConfig extends AbstractConfig {
        private static final IKey<Json.RawValue> raw = newKey("raw", null);

        private RawValueConfig() {
            put(raw, new Json.RawValue("Hogan"));
        }
    }

    private static class SimpleConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", null);
        private static final IKey<Integer> integer = newKey("integer", null);

        private SimpleConfig() {
            put(string, "1");
            put(integer, 1);
        }
    }

    private static class NestedConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", "1");
        private static final IKey<SimpleConfig> testConfig = newKey("testConfig", new SimpleConfig());

        private NestedConfig() {
            put(string, "2");
            put(testConfig, new SimpleConfig());
        }
    }

}
