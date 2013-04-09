package de.agilecoders.wicket.extensions.javascript;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;
import org.apache.wicket.javascript.IJavaScriptCompressor;

/**
 * Google Closure compiler version of {@link IJavaScriptCompressor}
 *
 * @author miha
 */
public class GoogleClosureJavaScriptCompressor implements IJavaScriptCompressor {

    private final CompilationLevel level;

    /**
     * Construct. Uses {@link CompilationLevel#ADVANCED_OPTIMIZATIONS} as compression
     * level.
     */
    public GoogleClosureJavaScriptCompressor() {
        this(CompilationLevel.ADVANCED_OPTIMIZATIONS);
    }

    /**
     * Construct.
     *
     * @param level The compilation level to use
     */
    public GoogleClosureJavaScriptCompressor(final CompilationLevel level) {
        this.level = level;
    }

    @Override
    public String compress(final String original) {
        final Compiler compiler = new Compiler();

        final CompilerOptions options = new CompilerOptions();
        // Advanced mode is used here, but additional options could be set, too.
        level.setOptionsForCompilationLevel(options);

        // To get the complete set of externs, the logic in
        // CompilerRunner.getDefaultExterns() should be used here.
        final SourceFile extern = SourceFile.fromCode("externs.js", "function alert(x) {}");

        // The dummy input name "input.js" is used here so that any warnings or
        // errors will cite line numbers in terms of input.js.
        final SourceFile input = SourceFile.fromCode("input.js", original);

        // compile() returns a Result, but it is not needed here.
        compiler.compile(extern, input, options);

        // The compiler is responsible for generating the compiled code; it is not
        // accessible via the Result.
        return compiler.toSource();
    }
}
