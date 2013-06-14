package de.agilecoders.wicket.core.markup.html.bootstrap.layout;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the layout enum.
 *
 * @author miha
 */
public class LayoutTest {

    @Test
    public void fluidLayout() {
        assertThat(Layout.Fluid.cssClassName(), is(equalTo("container-fluid")));
    }

    @Test
    public void fixedLayout() {
        assertThat(Layout.Fixed.cssClassName(), is(equalTo("container")));
    }

}
