package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.Less4jException;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Tests the {@link Less4JCompiler} class
 *
 * @author miha
 */
public class Less4JCompilerTest {

    private static final Less4JCompiler compiler = new Less4JCompiler();

    @Test
    public void compileWicketLessWithoutErrors() {
        final String content = compileLessFile("wicket.less");

        MatcherAssert.assertThat(content.length(), Matchers.greaterThan(100));
    }

    @Test
    public void compileResponsiveLessWithoutErrors() throws IOException {
        final String content = compileLessFile("responsive.less");

        MatcherAssert.assertThat(content.length(), Matchers.greaterThan(100));
    }

    @Test
    public void variablesAreReplaced() {
        MatcherAssert.assertThat(compile("@var:1; .rule { zoom: @var; }"), Matchers.is(Matchers.equalTo(".rule { zoom: 1;}")));
    }

    @Test
    public void starPrefixIsAllowed() {
        MatcherAssert.assertThat(compile(".rule { *zoom: 1; }"), Matchers.is(Matchers.equalTo(".rule { *zoom: 1;}")));
    }

    /**
     * compiles a given less file
     *
     * @param fileName The file name (relative to this class)
     * @return compiled content
     */
    private String compileLessFile(final String fileName) {
        final String content;
        try {
            content = IOUtils.toString(Less4JCompilerTest.class.getResourceAsStream("responsive.less"));
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }

        return compile(content);
    }

    /**
     * transforms content into a {@link ILessResource}
     *
     * @param content to transform
     * @return new {@link ILessResource} instance
     */
    private ILessResource on(final String content) {
        return new ILessResource() {
            @Override
            public String getPath() {
                return null;
            }

            @Override
            public Time getLastModificationTime() {
                return Time.now();
            }

            @Override
            public String asText() {
                return content;
            }

            @Override
            public boolean exists() {
                return true;
            }

            @Override
            public InputStream getInputStream() {
                return new ByteArrayInputStream(content.getBytes(Charset.forName("UTF-8")));
            }

            @Override
            public LessResource getRelative(String subPath) {
                return new LessResource(getClass(), subPath);
            }

            @Override
            public String getName() {
                return "test";
            }

            @Override
            public File toFile() {
                return new File("");
            }
        };
    }

    /**
     * compiles given content with {@link Less4JCompiler}
     *
     * @param content to compile
     * @return compiled content as string
     */
    private String compile(final String content) {
        try {
            return removeLineBreaks(IOUtils.toString(compiler.compile(on(content)).getInputStream(), Charset.forName("UTF-8").toString()));
        } catch (Exception e) {
            if (e instanceof Less4jException) {
                return new Less4JCompiler.ErrorLogger("test", (Less4jException) e).toString();
            }

            throw new RuntimeException(e);
        }
    }

    /**
     * removes all line breaks
     *
     * @param value The value to clean
     * @return cleaned value
     */
    private static String removeLineBreaks(String value) {
        if (value == null) {
            return "";
        }
        return value.replaceAll("\n", "").replaceAll("  ", " ");
    }

}
