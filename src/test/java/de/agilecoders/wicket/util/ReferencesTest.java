package de.agilecoders.wicket.util;

import de.agilecoders.wicket.WicketApplicationTest;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ReferencesTest extends WicketApplicationTest {

    @Test
    public void minificationIdentifierWasAppended() {
        getBootstrapSettings().minify(true);

        Assert.assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.min.ext")));
    }

    @Test
    public void minificationIdentifierWasntAppended() {
        getBootstrapSettings().minify(false);

        Assert.assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.ext")));
    }

    @Test
    public void nameWithoutDotReturnsSameName() {
        Assert.assertThat(References.appendMinificationIdentifier("file"), is(equalTo("file")));
    }

    @Test
    public void nullNameReturnsEmptyString() {
        Assert.assertThat(References.appendMinificationIdentifier(null), is(equalTo("")));
    }
}
