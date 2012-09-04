package de.agilecoders.wicket.settings;

import com.asual.lesscss.LessOptions;
import com.google.common.base.Charsets;
import de.agilecoders.wicket.util.BootstrapLessCompiler;
import de.agilecoders.wicket.util.IBootstrapLessCompiler;

import java.nio.charset.Charset;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapLessCompilerSettings implements IBootstrapLessCompilerSettings {
    private boolean useLessCompiler = false;
    private Charset charset = Charsets.UTF_8;
    private IBootstrapLessCompiler lessCompiler = new BootstrapLessCompiler();
    private LessOptions lessOptions = new LessOptions();
    private CacheStrategy cacheStrategy = CacheStrategy.Modified;
    private boolean storeChanges = false;

    @Override
    public void setUseLessCompiler(boolean value) {
        useLessCompiler = value;
    }

    @Override
    public boolean useLessCompiler() {
        return useLessCompiler;
    }

    @Override
    public void setCharset(Charset charset) {
        this.charset = charset;

        if (lessOptions != null) {
            lessOptions.setCharset(charset.name());
        }
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setLessCompiler(IBootstrapLessCompiler lessCompiler) {
        this.lessCompiler = lessCompiler;
    }

    @Override
    public IBootstrapLessCompiler getLessCompiler() {
        return lessCompiler;
    }

    @Override
    public void setLessOptions(LessOptions lessOptions) {
        this.lessOptions = lessOptions;
    }

    @Override
    public LessOptions getLessOptions() {
        return lessOptions;
    }

    @Override
    public CacheStrategy getCacheStrategy() {
        return cacheStrategy;
    }

    @Override
    public void setCacheStrategy(CacheStrategy strategy) {
        cacheStrategy = strategy;
    }

    @Override
    public void storeChanges(boolean value) {
        this.storeChanges = value;
    }

    @Override
    public boolean storeChanges() {
        return storeChanges;
    }
}
