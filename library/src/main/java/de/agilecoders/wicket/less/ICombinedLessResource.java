package de.agilecoders.wicket.less;

import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.time.Time;

/**
 * Represents a less file and all its imported references.
 *
 * @author miha
 */
public interface ICombinedLessResource extends IClusterable {

    /**
     * @return last modification time
     */
    // TODO miha Rename to getModificationTime() ?
    Time getLastModificationTime();

    /**
     * @return this resource as text
     */
    String asText();

    /**
     * @return name of this resource
     */
    String getName();

}
