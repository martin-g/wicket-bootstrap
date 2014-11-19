package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListView;

/**
 * A helper class for {@link org.apache.wicket.markup.html.list.ListItem}s that need to
 * add CSS classes to them. Depending on their {@link org.apache.wicket.markup.html.list.ListView parent}
 * configuration the item should add the {@link de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender}
 * or assume it is already added
 */
public class ListItemCssClassHelper {

    /**
     * The logic that should be executed at {@link org.apache.wicket.Component#onInitialize()}
     *
     * @param component The list item
     * @param cssClasses The CSS classes to add to the component
     */
    public static void onInitialize(Component component, String... cssClasses) {
        // add the divider if the parent is not a ListView
        // or a ListView that reuses its items
        ListView listView = component.findParent(ListView.class);
        if (listView != null) {
            if (listView.getReuseItems()) {
                component.getParent().add(new CssClassNameAppender(cssClasses));
            }
        } else {
            component.getParent().add(new CssClassNameAppender(cssClasses));
        }
    }

    /**
     * The logic that should be executed at {@link org.apache.wicket.Component#onConfigure()}
     *
     * @param component The list item
     * @param cssClasses The CSS classes to add to the component
     */
    public static void onConfigure(Component component, String... cssClasses) {
        // re-add the divider if the parent is a ListView
        // that doesn't reuse its items
        ListView listView = component.findParent(ListView.class);
        if (listView != null && !listView.getReuseItems()) {
            component.getParent().add(new CssClassNameAppender(cssClasses));
        }
    }
}
