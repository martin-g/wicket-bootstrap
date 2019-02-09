package de.agilecoders.wicket.extensions.javascript;

import org.apache.wicket.util.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the {@link YuiCssCompressor} class.
 *
 * @author miha
 */
public class YuiCssCompressorTest {

//    @Rule
//    public ContiPerfRule i = new ContiPerfRule();

    private static String content;
    private static YuiCssCompressor compressor;

    @BeforeAll
    public static void before() throws IOException {
        content = IOUtils.toString(YuiCssCompressorTest.class.getResourceAsStream("test.css"));
        compressor = new YuiCssCompressor();
    }

    @Test
//    @PerfTest(threads = 2, duration = 10000, rampUp = 1000, warmUp = 5000)
//    @Required(max = 600, average = 500)
    @Disabled
    public void test1() {
        assertNotNull(compressor.compress(content));
    }

    @AfterAll
    public static void after() {
        content = "";
        compressor = null;
    }
}
