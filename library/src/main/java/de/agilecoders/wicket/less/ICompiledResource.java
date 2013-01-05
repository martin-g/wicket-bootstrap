package de.agilecoders.wicket.less;

import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Time;

import java.io.Closeable;
import java.io.InputStream;

/**
 * Simple return value of {@link IBootstrapLessCompiler}
 *
 * @author miha
 */
public interface ICompiledResource extends Closeable {

    /**
     * @return the last modification time (including all children)
     */
    // TODO miha Rename to getModificationTime() ?
    Time getLastModificationTime();

    /**
     * @return compiled less content
     */
    InputStream getInputStream();

    /**
     * @return content length
     */
    Bytes length();

}
