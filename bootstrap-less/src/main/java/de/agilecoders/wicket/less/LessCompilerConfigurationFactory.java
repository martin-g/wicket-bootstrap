package de.agilecoders.wicket.less;

import com.github.sommeri.less4j.LessCompiler;

/**
 * Factory that creates a {@link LessCompiler.Configuration}. This enables the users to set application
 * specific configurations to the {@link LessCompiler}.
 */
public interface LessCompilerConfigurationFactory {
    LessCompiler.Configuration newConfiguration();
}
