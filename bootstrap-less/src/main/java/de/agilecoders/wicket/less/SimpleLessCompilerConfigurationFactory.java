package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessCompiler;
import com.github.sommeri.less4j.LessCompiler.Configuration;


/**
 * Returns just a plain {@link LessCompiler.Configuration} without any additional configuration.
 */
public class SimpleLessCompilerConfigurationFactory implements LessCompilerConfigurationFactory {

    @Override
    public Configuration newConfiguration() {
        return new Configuration();
    }

}
