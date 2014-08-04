package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ButtonJqueryFunctionTest {

    @Test
    public void initializeFunction() throws Exception {
        assertThat(ButtonJqueryFunction.button("customAction").build(), is("button('customAction')"));
    }

}