package de.agilecoders.wicket.util;

import de.agilecoders.wicket.WicketApplicationTest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ReferencesTest extends WicketApplicationTest {

    @Test
    public void minificationIdentifierWasAppended() {
        application().getBootstrapSettings().minify(true);

        assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.min.ext")));
    }

    @Test
    public void minificationIdentifierWasntAppended() {
        application().getBootstrapSettings().minify(false);

        assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.ext")));
    }

    @Test
    public void nameWithoutDotReturnsSameName() {
        assertThat(References.appendMinificationIdentifier("file"), is(equalTo("file")));
    }

    @Test
    public void nullNameReturnsEmptyString() {
        assertThat(References.appendMinificationIdentifier(null), is(equalTo("")));
    }
}
