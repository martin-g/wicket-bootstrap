package de.agilecoders.wicket.less;

import org.apache.wicket.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Base less compiler class.
 *
 * @author miha
 */
abstract class AbstractLessCompiler implements IBootstrapLessCompiler {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractLessCompiler.class);

    private final LessContentCollector collector;

    /**
     * Construct.
     */
    public AbstractLessCompiler() {
        collector = new LessContentCollector();
    }

    /**
     * collects and combines all imports of given less resource
     *
     * @param resource the base resource
     * @return combined less resource
     */
    protected final ICombinedLessResource collect(final ILessResource resource) {
        return collector.collect(resource);
    }

    /**
     * @return charset setting
     */
    protected final Charset getCharset() {
        if (Application.exists()) {
            return BootstrapLess.getSettings().getCharset();
        } else {
            LOG.warn("no application assigned to current thread, return default charset: UTF-8");

            return Charset.forName("UTF-8");
        }
    }
}
