package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.Less4jException;
import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.LessSource;
import com.github.sommeri.less4j.core.ThreadUnsafeLessCompiler;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.core.util.resource.UrlResourceStream;
import org.apache.wicket.util.io.Connections;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.AbstractStringResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.time.Time;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
public class LessResourceStream extends AbstractStringResourceStream {

    private static final ConcurrentMap<URL, LessSource.URLSource> CACHE = new ConcurrentHashMap<URL, LessSource.URLSource>();

    private final LessSource.URLSource lessSource;

    public LessResourceStream(IResourceStream lessStream) {
        Args.notNull(lessStream, "lessStream");

        if (!(lessStream instanceof UrlResourceStream)) {
            throw new IllegalArgumentException("Only URS!");
        }

        URL lessUrl = ((UrlResourceStream) lessStream).getURL();

        LessSource.URLSource lessSource = new LessSource.URLSource(lessUrl);
        LessSource.URLSource oldValue = CACHE.putIfAbsent(lessUrl, lessSource);
        if (oldValue != null) {
            this.lessSource = oldValue;
        }
        else {
            this.lessSource = lessSource;
        }
    }

    @Override
    protected String getString() {

        String cssContent;
        ThreadUnsafeLessCompiler compiler = new ThreadUnsafeLessCompiler();

        try {
            LessCompiler.CompilationResult result = compiler.compile(lessSource);
            List<LessCompiler.Problem> warnings = result.getWarnings();

            for (LessCompiler.Problem warning : warnings) {
                System.err.println("WARNING " + warning.getLine() + ":" + warning.getCharacter() + " " + warning.getMessage());
            }

            cssContent = result.getCss();

        } catch (Less4jException e) {
            throw new WicketRuntimeException("");
        }

        return cssContent;
    }

    @Override
    public Time lastModifiedTime() {
        Time modified = Time.START_OF_UNIX_TIME;
        return findLastModified(lessSource, modified);
    }

    private Time findLastModified(LessSource.URLSource source, Time time) {
        Time max = time;
        try {
            Time lastModified = Connections.getLastModified(source.getInputURL());
            max = Time.maxNullSafe(time, lastModified);

            Collection<LessSource> importedSources = source.getImportedSources();
            if (importedSources != null) {
                for (LessSource importedSource : importedSources) {
                    max = findLastModified((LessSource.URLSource) importedSource, max);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return max;
    }

    @Override
    public String getContentType() {
        return "text/css";
    }
}
