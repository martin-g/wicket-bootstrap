package de.agilecoders.wicket.less;

import java.io.IOException;
import java.util.List;

/**
 * Interface to identify all less compatible {@link org.apache.wicket.request.resource.ResourceReference} classes.
 *
 * @author miha
 */
public interface LessCompilable {

    /**
     * @return a list of less/css resources that should be compiled.
     */
    List<Resource> getLessResources();

    /**
     * This method is called after the less/css content was generated. But only if
     * {@link de.agilecoders.wicket.settings.BootstrapLessCompilerSettings#storeChanges()} is set
     * to true and there are changes inside the less/css files.
     *
     * @param content The css content as byte array.
     * @throws IOException if content can't be stored.
     */
    void storeCompiledLess(byte[] content) throws IOException;
}
