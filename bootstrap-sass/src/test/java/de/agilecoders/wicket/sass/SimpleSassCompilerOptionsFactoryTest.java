package de.agilecoders.wicket.sass;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.bit3.jsass.Options;


public class SimpleSassCompilerOptionsFactoryTest {

    @Test
    public void createsASimplePlainSassCompilerConfiguration() {
        SimpleSassCompilerOptionsFactory factory = new SimpleSassCompilerOptionsFactory();
        Options options = factory.newOptions();

        assertThat(options, instanceOf(Options.class));
        assertThat(options.getFunctionProviders(), empty());
    }

}
