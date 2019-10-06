package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.jquery.IKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoteTest extends Assertions {

    @Test
    void wildcardIsAutomaticallyAdded() {
        TestRemote remote = new TestRemote();
        remote.withUrl("someUrl");

        assertEquals("someUrl&term=%QUERY", remote.getValue(Remote.Url));
    }

    /**
     * Simple extension just to make #get(IKey) visible for the tests
     */
    private static class TestRemote extends Remote {
        private <T> T getValue(final IKey<T> key) {
            return get(key);
        }
    }
}
