package de.agilecoders.wicket.javascript;

import org.apache.wicket.util.io.IOUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the {@link YuiCssCompressor} class.
 *
 * @author miha
 */
public class YuiCssCompressorTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    private static String content;
    private static YuiCssCompressor compressor;

    @BeforeClass
    public static void before() throws IOException {
        content = IOUtils.toString(YuiCssCompressorTest.class.getResourceAsStream("test.css"));
        compressor = new YuiCssCompressor();
    }

    @Test
    @PerfTest(threads = 2, duration = 10000, rampUp = 1000, warmUp = 5000)
    @Required(max = 600, average = 500)
    @Ignore
    public void test1() throws Exception {
        assertNotNull(compressor.compress(content));
    }

    @AfterClass
    public static void after() {
        content = "";
        compressor = null;
    }
}
