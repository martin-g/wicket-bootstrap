package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
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

    private ButtonList buttonList;

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

        add(alignment.newCssClassNameAppender());
        add(new BootstrapBaseBehavior());
        add(new CssClassNameAppender("pagination"));
        add(buttonList = newButtonList("buttons"));
    }

    public Pagination addButton(AbstractLink button) {
        return addButtons(button);
    }

    public Pagination addButtons(AbstractLink... buttons) {
        buttonList.addButtons(buttons);
        return this;
    }

    protected ButtonList newButtonList(final String markupId) {
        return new ButtonList(markupId);
    }

}
