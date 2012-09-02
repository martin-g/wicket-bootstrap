package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Behaviors;
import de.agilecoders.wicket.util.Iterables;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Wrap a series of buttons with .btn in .btn-group.
 *
 * @author miha
 * @version 1.0
 */
public class ButtonGroup extends Panel {

    public static String getButtonMarkupId() {
        return "button";
    }

    private List<AbstractLink> buttonList;

    public ButtonGroup(final String markupId) {
        super(markupId);

        buttonList = Lists.newArrayList();

        add(new BootstrapBaseBehavior());
        add(new CssClassNameAppender("btn-group"));
        add(newButtonList("buttons"));
    }

    public ButtonGroup addButton(AbstractLink button) {
        return addButtons(button);
    }

    public ButtonGroup addButtons(AbstractLink... buttons) {
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

    private static class AssertValidButtonPredicate implements Predicate<AbstractLink> {
        private static final Logger LOG = LoggerFactory.getLogger(AssertValidButtonPredicate.class);

        private final String buttonMarkupId;

        public AssertValidButtonPredicate(final String buttonMarkupId) {
            this.buttonMarkupId = buttonMarkupId;
        }

        @Override
        public boolean apply(@Nullable AbstractLink button) {
            if (button == null) {
                throw new IllegalArgumentException("invalid button object");
            }

            if (!buttonMarkupId.equals(button.getId())) {
                throw new IllegalArgumentException("button id must be equal to '" + buttonMarkupId + "'");
            }

            if (!Behaviors.contains(button, ButtonBehavior.class)) {
                LOG.warn("button {}#{} has no ButtonBehavior. This will lead to an invalid style!", button.getPath(), button.getId());
            }

            return true;
        }
    }
}
