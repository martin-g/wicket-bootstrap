package de.agilecoders.wicket.core.markup.html.bootstrap.common;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class AbstractConfigTest extends Assert {

    @Test
    public void toJson() {
        System.err.println("config: " + new TestConfig().toJsonString());
        System.err.println("config2: " + new TestConfig2().toJsonString());
    }

    private static class TestConfig extends AbstractConfig {
        private static final IKey<String> string = newKey("string", null);
        private static final IKey<Integer> integer = newKey("integer", null);

        private TestConfig() {
            put(string, "1");
            put(integer, 1);
        }
    }

    private static class TestConfig2 extends AbstractConfig {
        private static final IKey<String> string = newKey("string", "1");
        private static final IKey<TestConfig> testConfig = newKey("testConfig", new TestConfig());

        private TestConfig2() {
            put(string, "2");
            put(testConfig, new TestConfig());
        }
    }

}
