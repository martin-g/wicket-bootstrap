package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/basecss", alt = "/css")
public class BaseCssPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public BaseCssPage(PageParameters parameters) {
        super(parameters);

    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
