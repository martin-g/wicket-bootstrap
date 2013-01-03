package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.core.ThreadUnsafeLessCompiler;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.io.IClusterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;

/**
 * less4j implementation of {@link IBootstrapLessCompiler}.
 * <p/>
 * CAUTION: less4j has no final release yet and there is still a lot of stuff to do. Please
 * use default less compiler if you don't know how to verify correct css output.
 *
 * Issues:
 *  > star prefix is not allowed for property keys: https://github.com/SomMeri/less4j/issues/55
 *  > color functions: https://github.com/SomMeri/less4j/issues/16
 *
 * @author miha
 */
@ThreadSafe
public class Less4JCompiler extends AbstractLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(Less4JCompiler.class);

    @Override
    public ICompiledResource compile(ILessResource lessResource) {
        ICombinedLessResource combinedLessResource = collect(lessResource);

        return new AbstractCompiledResource(combinedLessResource) {
            @Override
            protected byte[] compile(final ICombinedLessResource lessFile) {
                final long start = System.currentTimeMillis();
                ThreadUnsafeLessCompiler compiler = new ThreadUnsafeLessCompiler();

                try {
                    return compiler.compile(lessFile.asText()).getCss().getBytes(getCharset());
                } catch (Less4jException e) {
                    LOG.error("can't compile {}: {}", lessFile.getName(), new ErrorLogger(lessFile.getName(), e));

                    throw new WicketRuntimeException("can't compile " + lessFile.getName(), e);
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
         * @param name the base less file name
         * @param e    cause exception
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
