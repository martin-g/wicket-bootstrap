package de.agilecoders.wicket.markup.html.bootstrap.components;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.button.AssertValidButtonPredicate;
import de.agilecoders.wicket.util.Iterables;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

/**
 * Simple pagination inspired by Rdio, great for apps and search results.
 * The large block is hard to miss, easily scalable, and provides large click areas.
 *
 * @author miha
 * @version 1.0
 */
public class Pagination extends Panel {

    public static String getButtonMarkupId() {
        return "button";
    }

    /**
     * Add one of two optional classes to change the alignment of pagination links: .pagination-centered and .pagination-right.
     */
    public enum Alignment implements CssClassNameProvider {
        Centered, Right, Left;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(this);
        }
    }

    private List<AbstractLink> buttonList;

    public Pagination(final String markupId) {
        this(markupId, Alignment.Left);
    }

    public Pagination(final String markupId, final Alignment alignment) {
        super(markupId);

        buttonList = Lists.newArrayList();

        add(alignment.newCssClassNameAppender());
        add(new BootstrapBaseBehavior());
        add(new CssClassNameAppender("pagination"));
        add(newButtonList("buttons"));
    }

    public Pagination addButton(AbstractLink button) {
        return addButtons(button);
    }

    public Pagination addButtons(AbstractLink... buttons) {
        List<? extends AbstractLink> buttonsList = Iterables.forEach(buttons, new AssertValidButtonPredicate(getButtonMarkupId()));

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
        }.setOutputMarkupId(true);
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
