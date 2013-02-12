package de.agilecoders.wicket.markup.html.bootstrap.button;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link ListView} of {@link AbstractLink}.
 *
 * @author miha
 * @version 1.0
 */
public class ButtonList extends ListView<AbstractLink> {

    public static String getButtonMarkupId() {
        return "button";
    }

    /**
     * Construct.
     *
     * @param markupId
     */
    public ButtonList(String markupId) {
        this(markupId, new ArrayList<AbstractLink>());
    }

    /**
     * Construct.
     *
     * @param id
     * @param list
     */
    public ButtonList(String id, List<? extends AbstractLink> list) {
        super(id, list);

        setOutputMarkupId(true);
    }

    /**
     * Construct.
     *
     * @param id
     * @param model
     */
    public ButtonList(String id, IModel<List<? extends AbstractLink>> model) {
        super(id, model);

        setOutputMarkupId(true);
    }

    public ButtonList addButton(AbstractLink button) {
        return addButtons(button);
    }

    public ButtonList addButtons(AbstractLink... buttons) {
        Collections.addAll(getModelObject(), buttons);

        return this;
    }

    @Override
    protected void populateItem(ListItem<AbstractLink> item) {
        item.add(item.getModelObject());
    }
}
