package de.agilecoders.wicket.less;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.IBootstrapLessCompilerSettings;
import de.agilecoders.wicket.util.BootstrapLessCompiler;
import de.agilecoders.wicket.util.IBootstrapLessCompiler;
import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.CssPackageResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Package resource for css files. It strips comments and whitespace from css.
 *
 * @author miha
 * @version 1.0
 */
public class LessPackageResource extends CssPackageResource {
    private static final long serialVersionUID = 1L;

    private final LessCompilable lessCompilable;
    private boolean compile;
    private Time lastModifiedTime;

    /**
     * Construct.
     *
     * @param scope     This argument will be used to get the class loader for loading the package
     *                  resource, and to determine what package it is in
     * @param name      The relative path to the resource
     * @param locale    The locale of the resource
     * @param style     The style of the resource
     * @param variation The component's variation (of the style)
     */
    public LessPackageResource(Class<?> scope, String name, Locale locale, String style,
                               String variation, LessCompilable lessCompilable) {
        super(scope, name, locale, style, variation);

        this.lessCompilable = lessCompilable;
        this.compile = settings().useLessCompiler();
    }

    public boolean getCompile() {
        return compile && settings().useLessCompiler();
    }

    public LessPackageResource setCompile(final boolean compile) {
        this.compile = compile;
        return this;
    }

    @Override
    protected byte[] processResponse(final Attributes attributes, final byte[] bytes) {
        IBootstrapLessCompiler compiler = getCompiler();

        if (compiler != null && getCompile()) {
            final byte[] content = super.processResponse(attributes, compiler.generate(lessCompilable));

            if (settings().storeChanges() && wasModified()) {
                storeChanges(content);
            }

            return content;
        } else {
            return super.processResponse(attributes, bytes);
        }
    }

    protected boolean wasModified() {
        return lastModifiedTime != null &&
               lastModifiedTime.after(Time.now().subtract(Duration.seconds(10)));
    }

    protected void storeChanges(byte[] content) {
        try {
            lessCompilable.writeTo(content);
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
    }

    /**
     * Gets the {@link BootstrapLessCompiler} to be used. By default returns the configured compiler on
     * application level, but can be overriden by the user application to provide compiler
     * specific to the resource.
     *
     * @return the configured application level less compiler. May be {@code null}.
     */
    protected IBootstrapLessCompiler getCompiler() {
        IBootstrapLessCompiler compiler = null;
        if (Application.exists()) {
            compiler = settings().getLessCompiler();
        }
        return compiler;
    }

    /**
     * @return the bootstrap less compiler settings.
     */
    private IBootstrapLessCompilerSettings settings() {
        return Bootstrap.getSettings(Application.get()).getBootstrapLessCompilerSettings();
    }

    @Override
    public IResourceStream getResourceStream() {
        IResourceStream delegate = super.getResourceStream();

        if (delegate != null) {
            ResourceStreamDecorator stream = new ResourceStreamDecorator(delegate);

            lastModifiedTime = detectLastModifiedTime();
            stream.setLastModifiedTime(lastModifiedTime);

            return stream;
        }

        return delegate;
    }

    /**
     * detects the last modified timestamp of all less files and their imports.
     *
     * @return last modified time
     */
    private Time detectLastModifiedTime() {
        switch (settings().getCacheStrategy()) {
            case Never: return Time.now().add(Duration.days(365));
            case Forever: return Time.START_OF_UNIX_TIME;
        }
        return getCompiler().lastModifiedRecursive(lessCompilable);
    }

    private static final class ResourceStreamDecorator implements IResourceStream {

        private final IResourceStream resourceStream;
        private Time lastModifiedTime;

        private ResourceStreamDecorator(IResourceStream resourceStream) {
            this.resourceStream = resourceStream;
        }

        @Override
        public String getContentType() {
            return resourceStream.getContentType();
        }

        @Override
        public Bytes length() {
            return resourceStream.length();
        }

        @Override
        public InputStream getInputStream() throws ResourceStreamNotFoundException {
            return resourceStream.getInputStream();
        }

        @Override
        public void close() throws IOException {
            resourceStream.close();
        }

        @Override
        public Locale getLocale() {
            return resourceStream.getLocale();
        }

        @Override
        public void setLocale(Locale locale) {
            resourceStream.setLocale(locale);
        }

        @Override
        public String getStyle() {
            return resourceStream.getStyle();
        }

        @Override
        public void setStyle(String style) {
            resourceStream.setStyle(style);
        }

        @Override
        public String getVariation() {
            return resourceStream.getVariation();
        }

        @Override
        public void setVariation(String variation) {
            resourceStream.setVariation(variation);
        }

        @Override
        public Time lastModifiedTime() {
            return lastModifiedTime;
        }

        private void setLastModifiedTime(Time time) {
            lastModifiedTime = time;
        }
    }
}

