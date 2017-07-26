package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code HomePage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/", alt = "/home")
public class HomePage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public HomePage(PageParameters parameters) {
        super(parameters);
    }
}
