package de.agilecoders.wicket.settings;

import com.asual.lesscss.LessOptions;
import de.agilecoders.wicket.util.IBootstrapLessCompiler;

import java.nio.charset.Charset;

/**
 * The {@link IBootstrapLessCompilerSettings} interface.
 *
 * @author miha
 * @version 1.0
 */
public interface IBootstrapLessCompilerSettings {

    public enum CacheStrategy {
        Never, Forever, Modified
    }

    /**
     * Turns on the less compiler.
     *
     * @param value true, if less compiler should be used
     */
    IBootstrapLessCompilerSettings setUseLessCompiler(boolean value);

    /**
     * @return true, if less compiler should be used
     */
    boolean useLessCompiler();

    /**
     * Sets the charset to use when reading and writing less/css files.
     * Default: UTF-8
     *
     * @param charset the charset to use.
     */
    IBootstrapLessCompilerSettings setCharset(Charset charset);

    /**
     * @return the charset to use.
     */
    Charset getCharset();

    /**
     * The less compiler implementation that generates the css files.
     *
     * @param lessCompiler The less compiler to use
     */
    IBootstrapLessCompilerSettings setLessCompiler(IBootstrapLessCompiler lessCompiler);

    /**
     * @return The less compiler to use
     */
    IBootstrapLessCompiler getLessCompiler();

    /**
     * sets the less compiler options
     *
     * @param lessOptions the less compiler options
     */
    IBootstrapLessCompilerSettings setLessOptions(LessOptions lessOptions);

    /**
     * @return the less compiler options
     */
    LessOptions getLessOptions();

    /**
     * @return the cache strategy
     */
    CacheStrategy getCacheStrategy();

    /**
     * sets the {@link CacheStrategy} to use.
     *
     * @param strategy the cache strategy
     */
    IBootstrapLessCompilerSettings setCacheStrategy(CacheStrategy strategy);

    /**
     * whether to store all less file changes to the css file or not
     *
     * @param value true, if file should stored.
     */
    IBootstrapLessCompilerSettings storeChanges(boolean value);

    /**
     * @return true, if less file changes should stored in css file.
     */
    boolean storeChanges();
}
