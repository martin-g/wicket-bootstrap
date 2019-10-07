package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link Buttons} class
 *
 * @author miha
 */
class ButtonsTest {

    @Test
    void mediumSizeButtonHasntACssClass() {
        assertThat(Buttons.Size.Medium.cssClassName(), is(""));
    }

}
