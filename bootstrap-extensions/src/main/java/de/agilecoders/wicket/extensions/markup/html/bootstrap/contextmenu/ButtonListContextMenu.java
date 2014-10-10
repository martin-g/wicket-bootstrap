package de.agilecoders.wicket.extensions.markup.html.bootstrap.contextmenu;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * A {@link ContextMenu} that contains a list of buttons.
 *
 * @author miha
 */
public class ButtonListContextMenu extends ContextMenu<List<AbstractLink>> {

    /**
     * Construct.
     *
     * @param markupId The component id
     */
    public ButtonListContextMenu(final String markupId, final IModel<List<AbstractLink>> model) {
        super(markupId, model);

        add(new CssClassNameAppender("dropdown"));
    }

    @Override
    protected Component createContent(String markupId) {
        return new ButtonList(markupId, getModel());
    }
}
