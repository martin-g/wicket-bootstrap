package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Args;

import java.util.List;

/**
 * Wrap a series of buttons with .btn in .btn-group.
 *
 * @author miha
 */
public abstract class ButtonGroup extends Panel {

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
     * @param orientation Make a set of buttons appear vertically stacked rather than horizontally if set to {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Orientation#Vertical}.
     */
    public ButtonGroup(final String markupId, final Buttons.Orientation orientation) {
        super(markupId);

        Args.notNull(orientation, "orientation");

        this.orientation = orientation;

        add(newButtonList("buttons"));
        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, orientation.cssClassName(), "btn-group");
    }

    /**
     * creates a new {@link ButtonList} that contains all buttons from {@link #newButtons(String)}
     *
     * @param markupId the markup id of {@link ButtonList}
     * @return new {@link ButtonList} instance
     */
    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId, newButtons(ButtonList.getButtonMarkupId()));
        buttonList.setRenderBodyOnly(true);

        return buttonList;
    }

    /**
     * creates a list of sub menu buttons which will be shown as group.
     *
     * @param buttonMarkupId the markup id that all buttons must be use.
     * @return list of buttons
     */
    protected abstract List<AbstractLink> newButtons(final String buttonMarkupId);

}
