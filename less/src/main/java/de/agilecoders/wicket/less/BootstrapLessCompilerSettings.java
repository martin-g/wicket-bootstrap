package de.agilecoders.wicket.less;

import java.nio.charset.Charset;

/**
 * Default {@link IBootstrapLessCompilerSettings} implementation
 *
 * @author miha
 */
public class BootstrapLessCompilerSettings implements IBootstrapLessCompilerSettings {
    private boolean useLessCompiler = false;
    private Charset charset = Charset.forName("UTF-8");
    private IBootstrapLessCompiler lessCompiler = new NoOpLessCompiler();
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
