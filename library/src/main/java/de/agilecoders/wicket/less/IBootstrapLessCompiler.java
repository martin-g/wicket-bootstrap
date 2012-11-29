package de.agilecoders.wicket.less;

import org.apache.wicket.util.time.Time;

/**
 * The {@code IBootstrapLessCompiler} interface.
 *
 * @author miha
 */
public interface IBootstrapLessCompiler {

    /**
     * generates a css file according to the given {@link LessCompilable}.
     *
     * @param lessCompilable The {@link LessCompilable}
     * @return the generated css file as byte array.
     */
    byte[] generate(LessCompilable lessCompilable);

    /**
     * returns the last modification time of all less files and their imports.
     *
     *
     * @param lessCompilable The {@link de.agilecoders.wicket.less.LessCompilable}
     * @return the last modification time
     */
    Time lastModifiedRecursive(LessCompilable lessCompilable);

}
