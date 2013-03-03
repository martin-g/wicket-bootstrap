package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.lang.Args;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Wrap a series of buttons with .btn in .btn-group.
 *
 * @author miha
 */
public class ButtonGroup extends Panel {

    private final List<AbstractLink> buttonList;
    private final Buttons.Orientation orientation;

    /**
     * Construct.
     *
     * @param markupId The markup id.
     */
    public ButtonGroup(final String markupId) {
        this(markupId, Buttons.Orientation.Horizontal);
    }

    /**
     * Construct.
     *
     * @param markupId    The markup id.
     * @param orientation Make a set of buttons appear vertically stacked rather than horizontally if set to {@link de.agilecoders.wicket.markup.html.bootstrap.button.Buttons.Orientation#Vertical}.
     */
    public ButtonGroup(final String markupId, final Buttons.Orientation orientation) {
        super(markupId);

        Args.notNull(orientation, "orientation");

        this.buttonList = new ArrayList<AbstractLink>();
        this.orientation = orientation;

        add(newButtonList("buttons"));

        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, orientation.cssClassName(), "btn-group");
    }

    @Override
    public void detachModels() {
        super.detachModels();

        this.buttonList.clear();
    }

    public ButtonGroup addButton(AbstractLink button) {
        return addButtons(button);
    }

    public ButtonGroup addButtons(AbstractLink... buttons) {
        Collections.addAll(buttonList, buttons);
        return this;
    }

    protected Component newButtonList(final String markupId) {
        return new ListView<AbstractLink>(markupId, newButtonListModel()) {
            @Override
            protected void populateItem(ListItem<AbstractLink> item) {
                AbstractLink link = item.getModelObject();

                item.add(link);
            }
        }.setRenderBodyOnly(true)
                .setOutputMarkupId(true);
    }

    protected IModel<List<? extends AbstractLink>> newButtonListModel() {
        return new LoadableDetachableModel<List<? extends AbstractLink>>() {
            @Override
            protected List<? extends AbstractLink> load() {
                return buttonList;
            }
        };
    }

}
