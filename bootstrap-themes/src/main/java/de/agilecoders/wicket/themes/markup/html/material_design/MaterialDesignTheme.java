package de.agilecoders.wicket.themes.markup.html.material_design;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

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
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> references = new ArrayList<>();
        references.add(CssHeaderItem.forReference(new MaterialDesignCssReference()).setId(BOOTSTRAP_THEME_MARKUP_ID));
        references.add(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(MaterialDesignTheme.class, "js/bootstrap-material-design.js")));
        references.add(OnDomReadyHeaderItem.forScript("$('body').bootstrapMaterialDesign();"));
        return references;
    }
}
