package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.core.ThreadUnsafeLessCompiler;
import com.google.common.base.Charsets;
import de.agilecoders.wicket.Bootstrap;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IClusterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;
import java.nio.charset.Charset;

/**
 * less4j implementation of {@link IBootstrapLessCompiler}.
 * <p/>
 * CAUTION: less4j has no final release yet and there is still a lot of stuff to do. Please
 * use default less compiler if you don't know how to verify correct css output.
 *
 * @author miha
 */
@ThreadSafe
public class Less4JCompiler implements IBootstrapLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(Less4JCompiler.class);

    private final LessContentCollector collector;

    /**
     * Construct.
     */
    public Less4JCompiler() {
        collector = new LessContentCollector();
    }

    /**
     * @return charset setting
     */
    private static Charset getCharset() {
        if (Application.exists()) {
            return Bootstrap.getSettings().getBootstrapLessCompilerSettings().getCharset();
        } else {
            LOG.error("no application assigned to current thread, return default charset: {}", Charsets.UTF_8);

            return Charsets.UTF_8;
        }
    }

    @Override
    public ICompiledResource compile(ILessResource lessResource) {
        ICombinedLessResource combinedLessResource = collector.collect(lessResource);

        return new AbstractCompiledResource(combinedLessResource) {
            @Override
            protected byte[] compile(final ICombinedLessResource lessFile) {
                final long start = System.currentTimeMillis();
                ThreadUnsafeLessCompiler compiler = new ThreadUnsafeLessCompiler();

                try {
                    return compiler.compile(lessFile.asText()).getCss().getBytes(getCharset());
                } catch (Less4jException e) {
                    LOG.error("can't compile {} from less to css: {}", lessFile.getName(), new ErrorLogger(lessFile.getName(), e));

                    throw new WicketRuntimeException("can't compile less to css, see log for error messages", e);
                } finally {
                    LOG.debug("duration of collect imports for {}: {} ms", lessFile.getName(), System.currentTimeMillis() - start);
                }
            }
        };
    }

    /**
     * simple error message logger for {@link Less4jException}s
     */
    protected static final class ErrorLogger implements IClusterable {

        private final String name;
        private final Less4jException e;

        /**
         * Construct.
         *
         * @param e cause exception
         */
        ErrorLogger(final String name, final Less4jException e) {
            this.name = name;
            this.e = e;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder("less4j errors while generating '" + name + "':\n");

            for (LessCompiler.Problem error : e.getErrors()) {
                builder.append("  > [").append(error.getType()).append("] ")
                        .append(error.getMessage())
                        .append(" in line ").append(error.getLine())
                        .append(":").append(error.getCharacter()).append("\n");
            }

            return builder.toString();
        }
    }

}
