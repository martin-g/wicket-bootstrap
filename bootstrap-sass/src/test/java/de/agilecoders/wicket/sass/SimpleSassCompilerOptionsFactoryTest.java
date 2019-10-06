package de.agilecoders.wicket.sass;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import io.bit3.jsass.Options;
import org.junit.jupiter.api.Test;

class SimpleSassCompilerOptionsFactoryTest {

    @Test
    void createsASimplePlainSassCompilerConfiguration() {
        SimpleSassCompilerOptionsFactory factory = new SimpleSassCompilerOptionsFactory();
        Options options = factory.newOptions();

        assertThat(options, instanceOf(Options.class));
        assertThat(options.getFunctionProviders(), empty());
    }

}
