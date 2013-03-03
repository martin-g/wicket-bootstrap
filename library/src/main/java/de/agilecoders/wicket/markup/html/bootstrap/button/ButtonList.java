package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * A {@link ListView} of {@link AbstractLink}.
 *
 * @author miha
 */
public class ButtonList extends ListView<AbstractLink> {

    /**
     * @return the markup id that is used for buttons in the list
     */
    public static String getButtonMarkupId() {
        return "button";
    }

    /**
     * Construct.
     *
     * @param id   the component' id
     * @param list list of all buttons inside this button list
     */
    public ButtonList(final String id, final List<? extends AbstractLink> list) {
        super(id, list);

        setOutputMarkupId(true);
    }

    /**
     * Construct.
     *
     * @param id    the component' id
     * @param model list model of all buttons inside this button list
     */
    public ButtonList(final String id, final IModel<List<? extends AbstractLink>> model) {
        super(id, model);

        setOutputMarkupId(true);
    }

    /**
     * checks whether there is a button that is active or not
     *
     * @param activeButton the current active button
     * @return true, if at least one button of button list is active
     */
    public final boolean hasActiveButton(final Component activeButton) {
        final Class<? extends Page> pageClass = activeButton.getPage().getPageClass();

        for (final AbstractLink link : getList()) {
            if (link instanceof Activatable) {
                return ((Activatable) link).isActive(activeButton);
            } else if (link instanceof BookmarkablePageLink) {
                if (((BookmarkablePageLink) link).getPageClass().equals(pageClass)) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    protected void populateItem(ListItem<AbstractLink> item) {
        item.add(item.getModelObject());
    }
}
