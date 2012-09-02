package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.button.AssertValidButtonPredicate;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonSize;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.util.Iterables;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 * @version 1.0
 */
public class DropDownButton extends AbstractLink {

    public static String getButtonMarkupId() {
        return "button";
    }

    private ButtonSize buttonSize = ButtonSize.Medium;
    private ButtonType buttonType = ButtonType.Menu;
    private Component baseButton;
    private boolean dropUp = false;
    private List<AbstractLink> buttonList;

    /**
     * Construct.
     *
     * @param id    The markup id
     * @param model The label of the main button
     */
    public DropDownButton(String id, IModel<String> model) {
        super(id, model);

        buttonList = Lists.newArrayList();

        addBaseButton("btn");

        add(new CssClassNameAppender("btn-group"));
        add(new AssertTagNameBehavior("div"));
        add(new BootstrapResourcesBehavior());
        add(newButtonList("buttons"));
    }

    protected void addBaseButton(final String markupId) {
        add(baseButton = createButton(markupId));
    }

    protected Component createButton(final String markupId) {
        WebMarkupContainer baseButton = new WebMarkupContainer(markupId);
        baseButton.setOutputMarkupId(true);

        Label label = new Label("label", getDefaultModel());
        label.setRenderBodyOnly(true);

        baseButton.add(label);
        return baseButton;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript("$('.dropdown-toggle').dropdown()"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        updateButtonBehavior(buttonType, buttonSize);

        if (dropUp) {
            add(new CssClassNameAppender("dropup"));
        }
    }

    public DropDownButton setDropUp(boolean dropUp) {
        this.dropUp = dropUp;
        return this;
    }

    protected void updateButtonBehavior(ButtonType buttonType, ButtonSize buttonSize) {
        baseButton.add(new ButtonBehavior(buttonType, buttonSize));
    }

    public DropDownButton addButton(AbstractLink button) {
        return addButtons(button);
    }

    public DropDownButton addButtons(AbstractLink... buttons) {
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

    public DropDownButton setSize(ButtonSize buttonSize) {
        this.buttonSize = buttonSize;

        return this;
    }

    public DropDownButton setType(ButtonType buttonType) {
        this.buttonType = buttonType;

        return this;
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }
}
