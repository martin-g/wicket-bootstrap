package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.Less4jException;
import com.google.common.base.Charsets;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.time.Time;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static com.google.common.base.Strings.nullToEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Less4JCompiler} class
 *
 * @author miha
 */
public class Less4JCompilerTest {

    private static final Less4JCompiler compiler = new Less4JCompiler();

    @Test
    public void variablesAreReplaced() {
        assertThat(compile("@var:1; .rule { zoom: @var; }"), is(equalTo(".rule { zoom: 1;}")));
    }

    @Test
    @Ignore("star prefix for properties is not allowed in current less4j version")
    public void starPrefixIsAllowed() {
        assertThat(compile(".rule { *zoom: 1; }"), is(equalTo(".rule { zoom: 1;}")));
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
            public Time lastModified() {
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
                return new ByteArrayInputStream(content.getBytes(Charsets.UTF_8));
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
            return removeLineBreaks(IOUtils.toString(compiler.compile(on(content)).getInputStream(), Charsets.UTF_8.toString()));
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
    private String removeLineBreaks(String value) {
        return nullToEmpty(value).replaceAll("\n", "").replaceAll("  ", " ");
    }

}
