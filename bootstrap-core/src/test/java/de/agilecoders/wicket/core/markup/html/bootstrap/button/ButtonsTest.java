package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link Buttons} class
 *
 * @author miha
 */
public class ButtonsTest {

    @Test
    public void mediumSizeButtonHasntACssClass() {
        assertThat(Buttons.Size.Medium.cssClassName(), is(""));
    }

}
