package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * This class was added to make migration a bit easier.
 *
 * @author miha
 */
@Deprecated
public class TypedBookmarkablePageLink<T> extends BootstrapBookmarkablePageLink<T> {

    /**
     * {@inheritDoc}
     */
    public <T1 extends Page> TypedBookmarkablePageLink(String componentId, Class<T1> pageClass, Buttons.Type type) {
        super(componentId, pageClass, type);
    }

    /**
     * {@inheritDoc}
     */
    public <T1 extends Page> TypedBookmarkablePageLink(String componentId, Class<T1> pageClass, PageParameters parameters, Buttons.Type type) {
        super(componentId, pageClass, parameters, type);
    }
}
