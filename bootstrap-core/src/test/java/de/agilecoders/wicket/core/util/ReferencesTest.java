package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.Application;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the references class
 *
 * @author miha
 */
public class ReferencesTest extends WicketApplicationTest {

    @Test
    public void minificationIdentifierWasAppended() {
        Application.get().getResourceSettings().setUseMinifiedResources(true);

        assertThat(References.appendMinificationIdentifier("file.ext"), is(equalTo("file.min.ext")));
    }

    @Test
    public void minificationIdentifierWasntAppended() {
        Application.get().getResourceSettings().setUseMinifiedResources(false);

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
