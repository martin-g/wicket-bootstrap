package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.samples.components.basecss.ButtonGroups;
import de.agilecoders.wicket.samples.components.basecss.Dropdowns;
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

        add(new Dropdowns("dropdowns"));
        add(new ButtonGroups("buttonGroups"));
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
