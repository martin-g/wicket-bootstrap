package de.agilecoders.wicket.themes.markup.html.material_design;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.settings.Theme;

/**
 * A {@link de.agilecoders.wicket.core.settings.ITheme theme} for
 * <a href="http://fezvrasta.github.io/bootstrap-material-design/">Bootstrap Material Design</a>.
 */
public class MaterialDesignTheme extends Theme {

    /**
     * Constructor.
     */
    public MaterialDesignTheme() {
        super("material-design");
    }

    @Override
    public List<ResourceReference> getResourceReferences() {
        return Collections.<ResourceReference>singletonList(new MaterialDesignCssReference());
    }
}
