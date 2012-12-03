package de.agilecoders.wicket.settings;

import com.asual.lesscss.LessOptions;
import com.google.common.base.Charsets;
import de.agilecoders.wicket.less.BootstrapLessCompiler;
import de.agilecoders.wicket.less.IBootstrapLessCompiler;

import java.nio.charset.Charset;

/**
 * Default {@link IBootstrapLessCompilerSettings} implementation
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
    public BootstrapLessCompilerSettings setUseLessCompiler(boolean value) {
        useLessCompiler = value;
        return this;
    }

    @Override
    public boolean useLessCompiler() {
        return useLessCompiler;
    }

    @Override
    public BootstrapLessCompilerSettings setCharset(Charset charset) {
        this.charset = charset;

        if (lessOptions != null) {
            lessOptions.setCharset(charset.name());
        }
        return this;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public BootstrapLessCompilerSettings setLessCompiler(IBootstrapLessCompiler lessCompiler) {
        this.lessCompiler = lessCompiler;
        return this;
    }

    @Override
    public IBootstrapLessCompiler getLessCompiler() {
        return lessCompiler;
    }

    @Override
    public BootstrapLessCompilerSettings setLessOptions(LessOptions lessOptions) {
        this.lessOptions = lessOptions;
        return this;
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
    public BootstrapLessCompilerSettings setCacheStrategy(CacheStrategy strategy) {
        cacheStrategy = strategy;
        return this;
    }

    @Override
    public BootstrapLessCompilerSettings storeChanges(boolean value) {
        this.storeChanges = value;
        return this;
    }

    @Override
    public boolean storeChanges() {
        return storeChanges;
    }
}
