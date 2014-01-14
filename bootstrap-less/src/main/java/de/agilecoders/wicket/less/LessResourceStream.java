package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessSource;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.resource.UrlResourceStream;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.AbstractStringResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamWrapper;
import org.apache.wicket.util.time.Time;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * A IResourceStream that loads the generated CSS content for Less resources
 */
public class LessResourceStream extends AbstractStringResourceStream {

    // TODO mgrigorov Remove once Wicket 6.13.1+ is released
    private static final Method GET_DELEGATE;
    static {
        try {
            GET_DELEGATE = ResourceStreamWrapper.class.getDeclaredMethod("getDelegate", new Class[] {});
            GET_DELEGATE.setAccessible(true);
        } catch (NoSuchMethodException nsmx) {
            throw new RuntimeException(nsmx);
        }
    }

    /**
     * The LessSource for the root Less resource.
     * Any LessSource can have children resources - imported resources
     */
    private final LessSource.URLSource lessSource;

    /**
     * Constructor.
     *
     * @param lessStream The resource stream that loads the Less content. Only UrlResourceStream is supported at the moment!
     */
    public LessResourceStream(IResourceStream lessStream) {
        Args.notNull(lessStream, "lessStream");

        while (lessStream instanceof ResourceStreamWrapper) {
            ResourceStreamWrapper wrapper = (ResourceStreamWrapper) lessStream;
            try {
                lessStream = (IResourceStream) GET_DELEGATE.invoke(wrapper);
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

        this.lessSource = cacheManager.getLessSource(lessUrl);
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
