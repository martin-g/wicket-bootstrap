package de.agilecoders.wicket.core.request.resource.caching.version;

import com.google.common.base.Charsets;
import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link ChecksumResourceVersion} class
 *
 * @author miha
 */
public class ChecksumResourceVersionWTest extends WicketApplicationTest {

    @Test
    public void bufferSizeIsEditable() {
        ChecksumResourceVersion version = new Adler32ResourceVersion() {
            @Override
            protected int bufferSize() {
                return 2048;
            }
        };

        assertThat(version.bufferSize(), is(equalTo(2048)));
    }

    @Test
    public void defaultMarkupEncodingIsUsed() {
        application().getMarkupSettings().setDefaultMarkupEncoding(Charsets.UTF_16.name());

        assertThat(new Adler32ResourceVersion().charset(), is(equalTo(Charsets.UTF_16)));
    }

    @Test
    public void fallbackIsUsedIfThereIsNoDefaultMarkupEncoding() {
        application().getMarkupSettings().setDefaultMarkupEncoding(null);

        assertThat(new Adler32ResourceVersion().charset(), is(equalTo(Charsets.UTF_8)));
    }
}
