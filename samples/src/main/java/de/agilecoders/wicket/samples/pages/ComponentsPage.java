package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/components")
public class ComponentsPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ComponentsPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
