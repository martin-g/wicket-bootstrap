package de.agilecoders.wicket.less;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;

import com.github.sommeri.less4j.LessCompiler;
import org.junit.jupiter.api.Test;


class SimpleLessCompilerConfigurationFactoryTest {

    @Test
    void createsASimplePlainLessCompilerConfiguration() {
        SimpleLessCompilerConfigurationFactory factory = new SimpleLessCompilerConfigurationFactory();
        LessCompiler.Configuration configuration = factory.newConfiguration();

        assertThat(configuration, instanceOf(LessCompiler.Configuration.class));
        assertThat(configuration.getCustomFunctions(), empty());
    }

}
