package de.agilecoders.wicket.markup.html.bootstrap.extensions.contextmenu;

import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * A {@link ContextMenu} that contains a list of buttons.
 *
 * @author miha
 */
public class ButtonListContextMenu extends ContextMenu<List<? extends AbstractLink>> {

    /**
     * Construct.
     *
     * @param markupId The component id
     */
    public ButtonListContextMenu(final String markupId, final IModel<List<? extends AbstractLink>> model) {
        super(markupId, model);
    }

    @Override
    protected Component createContent(String markupId) {
        return new ButtonList(markupId, getModel()).add(new AttributeModifier("role", "menu"));
    }
}
