package de.agilecoders.wicket.themes.markup.html.wicket;

import de.agilecoders.wicket.core.markup.html.themes.bootstrap.BootstrapCssReference;
import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Wicket theme.
 *
 * @author miha
 */
public class WicketTheme extends Theme {

    /**
     * Construct.
     */
    public WicketTheme() {
        super("wicket");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> references = new ArrayList<HeaderItem>();
        references.addAll(super.getDependencies());
        references.add(CssHeaderItem.forReference(BootstrapCssReference.instance()));
        references.add(CssHeaderItem.forReference(WicketThemeCssResourceReference.INSTANCE));
        return references;
    }
}
