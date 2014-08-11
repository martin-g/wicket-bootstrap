package de.agilecoders.wicket.samples.panels.pagination;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.InfiniteScrollingBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A demo panel for {@link de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator}
 */
public class InfinitePaginationPanel extends AbstractPaginationPanel {

    private BootstrapPagingNavigator pager;
    private final Model<String> callbackUrl;

	/**
     * Construct.
     *
     * @param markupId component id
     */
    public InfinitePaginationPanel(final String markupId) {
        super(markupId);

        setOutputMarkupId(true);

        callbackUrl = new Model<>();

        Component nextLink;
        add(nextLink = new Label("next-page").add(new AttributeModifier("href", callbackUrl)));
        add(newInfiniteScrollingBehavior(pager, nextLink));
    }

    /**
     * @return new infinite scrolling behavior
     */
    protected InfiniteScrollingBehavior newInfiniteScrollingBehavior(final Component pager, final Component nextLink) {
        final InfiniteScrollingBehavior scrollingBehavior = new InfiniteScrollingBehavior();
        scrollingBehavior.setNavSelector(pager);
        scrollingBehavior.setItemSelector(this, ".item");
        scrollingBehavior.setNextSelector(nextLink);
        scrollingBehavior.loadingMsgText("loading...");
        scrollingBehavior.loadingImg("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAABHNCSVQICAgIfAhkiAAAAA1JREFUCJlj+P///38ACfsD/QjR6B4AAAAASUVORK5CYII=");
        scrollingBehavior.loadingFinishedMsg("completed!");

        return scrollingBehavior;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        pager.add(new AttributeModifier("style", "display:none;"));

        for (Behavior behavior : pager.get("next").getBehaviors()) {
             if (behavior instanceof AjaxPagingNavigationBehavior) {
                 callbackUrl.setObject(((AjaxPagingNavigationBehavior) behavior).getCallbackUrl().toString());
                 break;
             }
        }
    }

    @Override
    protected Component createPager(String id) {
        pager = new BootstrapAjaxPagingNavigator(id, pageable);

        return pager;
    }

    @Override
    protected List<String> createData() {
        List<String> elements = new ArrayList<>(1000);
        for (int i = 0, l = 1000; i < l; ++i) {
            elements.add("element #" + i);
        }
        return elements;
    }

    @Override
    protected int pageSize() {
        return 100;
    }
}
