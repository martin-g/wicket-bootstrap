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
    private static final long serialVersionUID = 1L;
    private final WebMarkupContainer paginationUl;

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
        this(markupId, Size.Default);
    }

    /**
     * Construct.
     *
     * @param markupId  The component id.
     * @param size The size of the pagination
     */
    public Pagination(String markupId, final Size size) {
        super(markupId);

        paginationUl = new WebMarkupContainer("paginationUl") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Attributes.addClass(tag, "pagination", size.cssClassName());
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

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
