package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ButtonJqueryFunctionTest {

    @Test
    void initializeFunction() throws Exception {
        assertThat(ButtonJqueryFunction.button("customAction").build(), is("button('customAction')"));
    }

}
