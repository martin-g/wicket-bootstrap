package de.agilecoders.wicket.themes.markup.html.metro;

import de.agilecoders.wicket.core.settings.Theme;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Metro theme.
 *
 * @author miha
 * @version 1.0
 */
public class MetroTheme extends Theme {

    /**
     * Construct.
     */
    public MetroTheme(final String name) {
        super(name);
    }

    /**
     * Construct.
     */
    public MetroTheme() {
        this("metro");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> references = new ArrayList<HeaderItem>();
        references.addAll(super.getDependencies());
        references.add(CssHeaderItem.forReference(MetroCssReference.instance()).setId(BOOTSTRAP_THEME_MARKUP_ID));
        return references;
    }
}
