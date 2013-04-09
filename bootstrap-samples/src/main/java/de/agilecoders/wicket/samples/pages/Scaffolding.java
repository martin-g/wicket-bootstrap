package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.samples.components.scaffolding.FluidGrid;
import de.agilecoders.wicket.samples.components.scaffolding.Global;
import de.agilecoders.wicket.samples.components.scaffolding.Grid;
import de.agilecoders.wicket.samples.components.scaffolding.Layouts;
import de.agilecoders.wicket.samples.components.scaffolding.Responsive;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code Scaffolding} page
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/scaffolding")
public class Scaffolding extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public Scaffolding(PageParameters parameters) {
        super(parameters);

        add(new Global("global"));
        add(new Grid("grid"));
        add(new FluidGrid("fluidGridSystem"));
        add(new Layouts("layouts"));
        add(new Responsive("responsive"));
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
