package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import de.agilecoders.wicket.util.Iterables;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.lang.Args;

import java.util.List;

/**
 * Wrap a series of buttons with .btn in .btn-group.
 *
 * @author miha
 */
public class ButtonGroup extends Panel {

    private final List<AbstractLink> buttonList;
    private final Orientation orientation;

    /**
     * Construct.
     *
     * @param markupId The markup id.
     */
    public ButtonGroup(final String markupId) {
        this(markupId, Orientation.Horizontal);
    }

    /**
     * Construct.
     *
     * @param markupId    The markup id.
     * @param orientation Make a set of buttons appear vertically stacked rather than horizontally if set to {@link Orientation#Vertical}.
     */
    public ButtonGroup(final String markupId, final Orientation orientation) {
        super(markupId);

        Args.notNull(orientation, "orientation");

        this.buttonList = Lists.newArrayList();
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
        List<? extends AbstractLink> buttonsList = Iterables.forEach(buttons, new AssertValidButtonPredicate(ButtonList.getButtonMarkupId()));

        buttonList.addAll(buttonsList);
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
