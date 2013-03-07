package de.agilecoders.wicket.markup.html.bootstrap.components;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

/**
 * Simple pagination inspired by Rdio, great for apps and search results.
 * The large block is hard to miss, easily scalable, and provides large click areas.
 *
 * @author miha
 */
public abstract class Pagination extends Panel {

    /**
     * Add one of two optional classes to change the alignment of pagination links: .pagination-centered and .pagination-right.
     */
    public static enum Alignment implements ICssClassNameProvider {
        Centered,
        Right,
        Left;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }
    }

    private final Alignment alignment;

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

        add(newButtonList("buttons"));
        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Attributes.addClass(tag, "pagination", alignment.cssClassName());
    }

    /**
     * creates a new button list instance
     *
     * @param markupId The component id
     * @return new button list instance
     */
    protected ButtonList newButtonList(final String markupId) {
        return new ButtonList(markupId, newPaginationButtons(ButtonList.getButtonMarkupId()));
    }

    /**
     * creates a list of pagination buttons
     *
     * @param buttonMarkupId the markup id that must be used by all buttons
     * @return a list of buttons
     */
    protected abstract List<AbstractLink> newPaginationButtons(String buttonMarkupId);

}
