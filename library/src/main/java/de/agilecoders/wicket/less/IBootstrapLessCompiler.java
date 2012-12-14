package de.agilecoders.wicket.less;

/**
 * The {@code IBootstrapLessCompiler} interface.
 *
 * @author miha
 */
public interface IBootstrapLessCompiler {

    /**
     * generates a css file according to the given {@link ILessResource}.
     *
     * @param lessResource The {@link ILessResource}
     * @return the generated css file as {@link ICompiledResource}.
     */
    ICompiledResource compile(ILessResource lessResource);

}
