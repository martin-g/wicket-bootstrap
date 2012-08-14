package de.agilecoders.wicket.settings;

import com.asual.lesscss.LessOptions;
import de.agilecoders.wicket.util.BootstrapLessCompiler;

import java.nio.charset.Charset;

/**
 * TODO: document
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
    void setUseLessCompiler(boolean value);

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
    void setCharset(Charset charset);

    /**
     * @return the charset to use.
     */
    Charset getCharset();

    /**
     * The less compiler implementation that generates the css files.
     *
     * @param lessCompiler The less compiler to use
     */
    void setLessCompiler(BootstrapLessCompiler lessCompiler);

    /**
     * @return The less compiler to use
     */
    BootstrapLessCompiler getLessCompiler();

    /**
     * sets the less compiler options
     *
     * @param lessOptions the less compiler options
     */
    void setLessOptions(LessOptions lessOptions);

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
    void setCacheStrategy(CacheStrategy strategy);

    /**
     * whether to store all less file changes to the css file or not
     *
     * @param value true, if file should stored.
     */
    void storeChanges(boolean value);

    /**
     * @return true, if less file changes should stored in css file.
     */
    boolean storeChanges();
}
