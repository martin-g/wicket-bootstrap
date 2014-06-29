package de.agilecoders.wicket.less;

import java.net.URL;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.resource.UrlResourceStream;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.AbstractStringResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamWrapper;
import org.apache.wicket.util.time.Time;

import com.github.sommeri.less4j.LessSource;

/**
 * A IResourceStream that loads the generated CSS content for Less resources
 */
public class LessResourceStream extends AbstractStringResourceStream {

    /**
     * The LessSource for the root Less resource.
     * Any LessSource can have children resources - imported resources
     */
    private final LessSource.URLSource lessSource;

    private final String scopeClass;

    /**
     * Constructor.
     *
     * @param lessStream The resource stream that loads the Less content. Only UrlResourceStream is supported at the moment!
     * @param scopeClass The name of the class used as a scope
     */
    public LessResourceStream(IResourceStream lessStream, String scopeClass) {
        Args.notNull(lessStream, "lessStream");

        this.scopeClass = scopeClass;

        while (lessStream instanceof ResourceStreamWrapper) {
            ResourceStreamWrapper wrapper = (ResourceStreamWrapper) lessStream;
            try {
                lessStream = wrapper.getDelegate();
            } catch (Exception x) {
                throw new WicketRuntimeException(x);
            }
        }

        if (!(lessStream instanceof UrlResourceStream)) {
            throw new IllegalArgumentException(String.format("%s can work only with %s",
                LessResourceStream.class.getSimpleName(), UrlResourceStream.class.getName()));
        }

        URL lessUrl = ((UrlResourceStream) lessStream).getURL();

        LessCacheManager cacheManager = LessCacheManager.get();

        this.lessSource = cacheManager.getLessSource(lessUrl, scopeClass);
    }

    @Override
    protected String getString() {
        LessCacheManager cacheManager = LessCacheManager.get();
        return cacheManager.getCss(lessSource);
    }

    @Override
    public Time lastModifiedTime() {
        LessCacheManager cacheManager = LessCacheManager.get();
        return cacheManager.getLastModifiedTime(lessSource);
    }

    @Override
    public String getContentType() {
        return "text/css";
    }
}
