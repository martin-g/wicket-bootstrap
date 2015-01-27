package de.agilecoders.wicket.less;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.github.sommeri.less4j.LessCompiler;


public class SimpleLessCompilerConfigurationFactoryTest {

    @Test
    public void createsASimplePlainLessCompilerConfiguration() {
        SimpleLessCompilerConfigurationFactory factory = new SimpleLessCompilerConfigurationFactory();
        LessCompiler.Configuration configuration = factory.newConfiguration();
        
        assertThat(configuration, instanceOf(LessCompiler.Configuration.class));
        assertThat(configuration.getCustomFunctions(), empty());
    }
    
}
