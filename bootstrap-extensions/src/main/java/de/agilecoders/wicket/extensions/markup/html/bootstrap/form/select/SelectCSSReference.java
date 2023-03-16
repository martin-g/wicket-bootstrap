package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * Bootstrap select css reference
 *
 * @author Alexey Volkov
 * @since 02.11.14
 */
public class SelectCSSReference extends WebjarsCssResourceReference {

    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final SelectCSSReference INSTANCE = new SelectCSSReference();

    /**
     * @return the single instance of the resource reference
     */
    public static SelectCSSReference instance() {
        return INSTANCE;
    }

    private SelectCSSReference() {
        super("bootstrap-select/current/dist/css/bootstrap-select.min.css");
    }
}
