package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.jquery.IKey;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class RemoteTest extends Assert {

    @Test
    public void wildcardIsAutomaticallyAdded() {
        TestRemote remote = new TestRemote();
        remote.withUrl("someUrl");

        assertEquals("someUrl&term=%QUERY", remote.getValue(Remote.Url));
    }

    /**
     * Simple extension just to make #get(IKey) visible for the tests
     */
    private static class TestRemote extends Remote {
        @SuppressWarnings("unchecked")
        private final <T> T getValue(final IKey<T> key) {
            return get(key);
        }
    }
}
