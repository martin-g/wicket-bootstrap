package de.agilecoders.wicket.sass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.io.Connections;
import java.time.Instant;

import io.bit3.jsass.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


@SuppressWarnings("ResultOfMethodCallIgnored")
class SassCacheManagerTest {

    private int invocationOfAddImportedSources;
    private int invocationOfNewConfiguration;

    @BeforeEach
    void setUp() {
        invocationOfAddImportedSources = 0;
        invocationOfNewConfiguration = 0;
    }

    /**
     * Create a URLSource that keeps track of "addImportedSources" invocations.
     * Imported sources are added only if Sass resource was compiled, so we can use it to track
     * number of times resource was compiled.
     */
    SassSource createSampleURLSource() {
        return new SassSource(getClass().getResource("resources/root.scss").toExternalForm(), SassCacheManagerTest.class.getName()) {
            @Override
            public void addImportedSources(Collection<SassSource> sources) {
                invocationOfAddImportedSources++;
                super.addImportedSources(sources);
            }
        };
    }

    @Test
    void cachesCssResult() {
        SassCacheManager cacheManager = new SassCacheManager();

        SassSource urlSource = createSampleURLSource();

        // SassSource.getUrl() ist only necessary, when compiling the .scss file.
        // Otherwise the result would be in the cache.

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfAddImportedSources);

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfAddImportedSources);
    }

    @Test
    void clearCacheForcesRecompile() {
        SassCacheManager cacheManager = new SassCacheManager();

        SassSource urlSource = createSampleURLSource();

        // Context.getInputPath() ist only necessary, when compiling the .sass file.
        // Otherwise the result would be in the cache.

        cacheManager.getCss(urlSource);
        assertEquals(1, invocationOfAddImportedSources);

        cacheManager.clearCache();

        cacheManager.getCss(urlSource);
        assertEquals(2, invocationOfAddImportedSources);
    }

    @Test
    void usesSassCompilerOptionsFactoryProvidedToCreateANewSassCompilerOptions() {
        SassCacheManager cacheManager = new SassCacheManager(() -> {
            invocationOfNewConfiguration++;
            return new Options();
        });

        SassSource sassContext = createSampleURLSource();

        cacheManager.getCss(sassContext);
        assertEquals(1, invocationOfNewConfiguration);

        cacheManager.clearCache();

        cacheManager.getCss(sassContext);
        assertEquals(2, invocationOfNewConfiguration);
    }

    @Test
    void usesDefaultOptionsFactoryWhenProvidingNull() {
        SassCacheManager cacheManager = new SassCacheManager(null);

        SassSource urlSource = createSampleURLSource();

        cacheManager.getCss(urlSource);

        // no NullPointerException
    }

    @Test
    void timestampBeforeAndAfterCompile() throws IOException
    {
        long currentTimeMillis = System.currentTimeMillis();
        SassCacheManager cacheManager = new SassCacheManager();

        URL parentUrl = getClass().getResource("resources/timeParent.scss");
        File parentFile = Files.getLocalFileFromUrl(parentUrl);
        parentFile.setLastModified(currentTimeMillis);
        Instant expectedTimeBeforeCompile = Connections.getLastModified(parentUrl);
        SassSource urlSource = new SassSource(parentUrl.toExternalForm(), null);

        // Make sure that the imported file time stamp is newer than timeRoots
        URL childUrl = getClass().getResource("resources/timeChild.scss");
        File childFile = Files.getLocalFileFromUrl(childUrl);
        childFile.setLastModified(currentTimeMillis + 60000);
        Instant expectedTimeAfterCompile = Connections.getLastModified(childUrl);

        assertEquals(expectedTimeBeforeCompile, cacheManager.getLastModifiedTime(urlSource));

        cacheManager.getCss(urlSource);
        assertEquals(expectedTimeAfterCompile, cacheManager.getLastModifiedTime(urlSource));
    }

    @Test
    void clearCacheCreatesNewUrlSource()
    {
        SassCacheManager cacheManager = new SassCacheManager();

        URL resourceUrl = getClass().getResource("resources/root.scss");
        String scopeClass = getClass().getName();
        SassSource urlSourceBefore = cacheManager.getSassContext(resourceUrl, scopeClass);
        cacheManager.clearCache();
        SassSource urlSourceAfter = cacheManager.getSassContext(resourceUrl, scopeClass);

        assertNotSame("We should get different instances of URLSource", urlSourceBefore, urlSourceAfter);
    }
}
