package de.agilecoders.wicket.extensions.markup.html.bootstrap.inputmask;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * Robin Herbots Input Mask CSS resource reference.
 *
 * @author Jan Ferko
 */
public final class InputMaskCssReference extends WebjarsCssResourceReference {

    /**
     * Singleton instance of this reference.
     */
    private static final class Holder {
        private static final InputMaskCssReference INSTANCE = new InputMaskCssReference();
    }

    /**
     * Construct resource reference.
     */
    private InputMaskCssReference() {
        super("inputmask/current/css/inputmask.css");
    }

    /**
     * @return the single instance of the resource reference
     */
    public static InputMaskCssReference getInstance() {
        return Holder.INSTANCE;
    }
}
