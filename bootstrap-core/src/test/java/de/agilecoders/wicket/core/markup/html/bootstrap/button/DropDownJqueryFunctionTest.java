package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DropDownJqueryFunctionTest {

    @Test
    public void initializeFunction() throws Exception {
        assertThat(DropDownJqueryFunction.dropdown().build(), is("dropdown()"));
        assertThat(DropDownJqueryFunction.dropdown("customAction").build(), is("dropdown('customAction')"));
    }
}
