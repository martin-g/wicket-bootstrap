package de.agilecoders.wicket.themes.markup.html.material_design;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link org.apache.wicket.request.resource.CssResourceReference} for
 * <a href="http://fezvrasta.github.io/bootstrap-material-design/">Bootstrap Material Design</a>
 * theme.
 */
public class MaterialDesignCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Private constructor.
     */
    public MaterialDesignCssReference() {
        super(MaterialDesignCssReference.class, "css/material-wfont.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
        dependencies.add(CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
        dependencies.add(CssHeaderItem.forReference(new CssResourceReference(MaterialDesignCssReference.class, "css/ripples.min.css")));
        return dependencies;
    }
}
