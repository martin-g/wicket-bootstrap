package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * Simple pagination inspired by Rdio, great for apps and search results.
 * The large block is hard to miss, easily scalable, and provides large click areas.
 *
 * @author miha
 */
public abstract class Pagination extends Panel {

    private final WebMarkupContainer paginationUl;

    /**
     * Add one of two optional classes to change the alignment of pagination links: .pagination-centered and .pagination-right.
     * @deprecated Leftover from Bootstrap 2.x. It is ignored by Bootstrap 3.x
     */
    @Deprecated
    public enum Alignment implements ICssClassNameProvider {
        Centered,
        Right,
        Left;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }
    }

    public enum Size implements ICssClassNameProvider {
        Large("lg"),
        Small("sm"),
        Default("");

        private final String size;

        Size(String size) {this.size = size;}


        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "pagination-" + size;
        }
    }

    /**
     * Construct.
     *
     * @param markupId The component id.
     */
    public Pagination(final String markupId) {
        this(markupId, Alignment.Left, Size.Default);
    }

    /**
     * Construct.
     *
     * @param markupId  The component id.
     * @param alignment The alignment of the buttons
     */
    public Pagination(final String markupId, final Alignment alignment) {
        this(markupId, alignment, Size.Default);
    }

    /**
     * Construct.
     *
     * @param markupId  The component id.
     * @param size The size of the pagination
     */
    public Pagination(final String markupId, final Size size) {
        this(markupId, Alignment.Left, size);
    }

    public Pagination(String markupId, Alignment ignored, final Size size) {
        super(markupId);

        paginationUl = new WebMarkupContainer("paginationUl") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Attributes.addClass(tag, "pagination", size.cssClassName());
            }
        };

        add(paginationUl);

        paginationUl.add(newButtonList("buttons"));
        BootstrapBaseBehavior.addTo(this);
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
