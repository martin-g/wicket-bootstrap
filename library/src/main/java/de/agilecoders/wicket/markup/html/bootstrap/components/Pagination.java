package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Simple pagination inspired by Rdio, great for apps and search results.
 * The large block is hard to miss, easily scalable, and provides large click areas.
 *
 * @author miha
 * @version 1.0
 */
public class Pagination extends Panel {

    private final Alignment alignment;

    /**
     * Add one of two optional classes to change the alignment of pagination links: .pagination-centered and .pagination-right.
     */
    public enum Alignment implements ICssClassNameProvider {
        Centered, Right, Left;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameModifier() {
            return new CssClassNameAppender(this);
        }
    }

    private final ButtonList buttonList;

    /**
     * Construct.
     *
     * @param markupId The markup id.
     */
    public Pagination(final String markupId) {
        this(markupId, Alignment.Left);
    }

    /**
     * Construct.
     *
     * @param markupId  The markup id.
     * @param alignment The alignment of the buttons
     */
    public Pagination(final String markupId, final Alignment alignment) {
        super(markupId);

        this.alignment = alignment;

        add(buttonList = newButtonList("buttons"));
        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, "pagination", alignment.cssClassName());
    }

    /**
     * adds a new set of buttons to the button list
     *
     * @param buttons The buttons to add
     * @return this instance for chaining
     */
    public Pagination addButton(AbstractLink... buttons) {
        buttonList.addButtons(buttons);
        return this;
    }

    /**
     * creates a new button list instance
     *
     * @param markupId The component id
     * @return new button list instance
     */
    protected ButtonList newButtonList(final String markupId) {
        return new ButtonList(markupId);
    }

}
