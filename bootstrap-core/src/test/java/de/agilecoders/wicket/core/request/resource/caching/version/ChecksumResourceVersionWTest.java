package de.agilecoders.wicket.core.request.resource.caching.version;

import de.agilecoders.wicket.core.WicketApplicationTest;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link ChecksumResourceVersion} class
 *
 * @author miha
 */
class ChecksumResourceVersionWTest extends WicketApplicationTest {

    @Test
    void bufferSizeIsEditable() {
        ChecksumResourceVersion version = new Adler32ResourceVersion() {
            @Override
            protected int bufferSize() {
                return 2048;
            }
        };

        assertThat(version.bufferSize(), is(equalTo(2048)));
    }

    @Test
    void defaultMarkupEncodingIsUsed() {
        application().getMarkupSettings().setDefaultMarkupEncoding(StandardCharsets.UTF_16.name());

        assertThat(new Adler32ResourceVersion().charset(), is(equalTo(StandardCharsets.UTF_16)));
    }

    @Test
    void fallbackIsUsedIfThereIsNoDefaultMarkupEncoding() {
        application().getMarkupSettings().setDefaultMarkupEncoding(null);

        assertThat(new Adler32ResourceVersion().charset(), is(equalTo(StandardCharsets.UTF_8)));
    }

}
