package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpinnerConfigTest extends Assert{

    @Test
    public void empty() {
        SpinnerConfig config = new SpinnerConfig();
        assertThat(config.toJsonString(), is(equalTo("{}")));
    }

    @Test
    public void initval() {
        SpinnerConfig config = new SpinnerConfig();
        config.withInitVal(10);
        assertThat(config.toJsonString(), is(equalTo("{\"initval\":\"10\"}")));
    }
}
