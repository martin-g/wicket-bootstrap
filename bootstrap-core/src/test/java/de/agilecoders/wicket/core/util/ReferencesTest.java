package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.test.IntegrationTest;
import de.agilecoders.wicket.core.util.References;
import org.apache.wicket.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the references class
 *
 * @author miha
 */
@Category(IntegrationTest.class)
public class ReferencesTest extends WicketApplicationTest {

    @Test
    public void minificationIdentifierWasAppended() {
        Application.get().getResourceSettings().setUseMinifiedResources(true);

        Assert.assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.min.ext")));
    }

    @Test
    public void minificationIdentifierWasntAppended() {
        Application.get().getResourceSettings().setUseMinifiedResources(false);

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
