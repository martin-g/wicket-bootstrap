package de.agilecoders.wicket.ui.bootstrap.button.dropdown;

import de.agilecoders.wicket.ui.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Menu extends WebMarkupContainer {
    
    private List<Component> buttonList = new ArrayList<>();

    /**
     * TODO document
     *
     * @param componentId
     */
    public Menu(final String componentId) {
        this(componentId, Model.of());
    }

    /**
     * TODO document
     *
     * @param componentId
     * @param model
     */
    public Menu(final String componentId, final IModel<?> model) {
        super(componentId, model);

        add(newMenuList("menuList", buttonList));
    }

    /**
     * TODO document
     *
     * @param buttons
     * @return
     */
    final Menu addMenuButton(final Component... buttons) {
        buttonList.addAll(Arrays.asList(buttons));

        return this;
    }

    /**
     * TODO document
     *
     * @param componentId
     * @param listModel
     * @return
     */
    private Component newMenuList(final String componentId, final List<Component> listModel) {
        return new ListView<Component>(componentId, listModel) {
            @Override
            protected void populateItem(ListItem<Component> components) {
                if(components.getModelObject() instanceof MenuDividerButton) {
                    components.add(new CssClassNameAppender("divider"));
                }

                components.add(components.getModelObject());
            }
        };
    }

}
