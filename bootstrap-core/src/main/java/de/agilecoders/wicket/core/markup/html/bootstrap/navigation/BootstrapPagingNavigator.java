package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * A Wicket panel component to draw and maintain a complete page navigator, meant to be easily added
 * to any PageableListView. A navigation which contains links to the first and last page, the
 * current page +- some increment and which supports paged navigation bars (@see
 * PageableListViewNavigationWithMargin).
 *
 * @author miha
 */
public class BootstrapPagingNavigator extends PagingNavigator {

    public static enum Size {
        Small("sm"), Default(""), Large("lg");

        private final String size;

        private Size(String size) {
            this.size = size;
        }

        public String cssClass() {
            return equals(Default) ? "" : "pagination-" + size;
        }
    }

    private Size size;

    /**
     * Construct.
     *
     * @param markupId The components markup id
     * @param pageable The pageable component the page links are referring to.
     */
    public BootstrapPagingNavigator(final String markupId, final IPageable pageable) {
        this(markupId, pageable, null);
    }

    /**
     * Construct.
     *
     * @param markupId      The components markup id
     * @param pageable      The pageable component the page links are referring to.
     * @param labelProvider The label provider for the link text.
     */
    public BootstrapPagingNavigator(final String markupId, final IPageable pageable, final IPagingLabelProvider labelProvider) {
        super(markupId, pageable, labelProvider);

        BootstrapBaseBehavior.addTo(this);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new TransparentWebMarkupContainer("firstItem")
                .add(new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return get("first").isEnabled() ? null : "disabled";
                    }
                })));
        add(new TransparentWebMarkupContainer("prevItem")
                .add(new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return get("prev").isEnabled() ? null : "disabled";
                    }
                })));
        add(new TransparentWebMarkupContainer("nextItem")
                .add(new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return get("next").isEnabled() ? null : "disabled";
                    }
                })));
        add(new TransparentWebMarkupContainer("lastItem")
                .add(new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return get("last").isEnabled() ? null : "disabled";
                    }
                })));
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "ul");

        Attributes.addClass(tag, "pagination");

        if (size != null && !size.equals(Size.Default)) {
            Attributes.addClass(tag, size.cssClass());
        }
    }

    /**
     * Create a new PagingNavigation. May be subclassed to make us of specialized PagingNavigation.
     * 
     * @param id
     *            The id of the navigation component
     * @param pageable
     *            the pageable component
     * @param labelProvider
     *            The label provider for the link text.
     * @return the navigation object
     */
    
    @Override
    protected PagingNavigation newNavigation(final String id, final IPageable pageable, final IPagingLabelProvider labelProvider) {
        return new PagingNavigation(id, pageable, labelProvider) {
            /** Attribute for active state */
            private final AttributeModifier activeAttribute = AttributeModifier.append("class", "active");

            @Override
            protected void populateItem(final LoopItem loopItem) {
                super.populateItem(loopItem);
                if ((getStartIndex() + loopItem.getIndex()) == pageable.getCurrentPage()) {
                    loopItem.add(activeAttribute);
                }
            }
        };
    }

    /**
     * Sets the size of the pager - small, default or large
     *
     * @param size The new size for the pager.
     * @return {@code this} instance, for chaining
     */
    public BootstrapPagingNavigator setSize(Size size) {
        this.size = Size.Default.equals(size) ? null : size;
        return this;
    }
}
