package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.util.Iterables;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

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
        this(markupId, Lists.<AbstractLink>newArrayList());
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
        List<AbstractLink> buttonsList = Iterables.forEach(buttons, new AssertValidButtonPredicate(getButtonMarkupId()));
        getModelObject().addAll(buttonsList);

        return this;
    }

    @Override
    protected void populateItem(ListItem<AbstractLink> item) {
        item.add(item.getModelObject());
    }
}
