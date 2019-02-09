package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpinnerConfigTest {

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
