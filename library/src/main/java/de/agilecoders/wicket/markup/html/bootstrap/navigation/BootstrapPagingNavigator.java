package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;

/**
 * A Wicket panel component to draw and maintain a complete page navigator, meant to be easily added
 * to any PageableListView. A navigation which contains links to the first and last page, the
 * current page +- some increment and which supports paged navigation bars (@see
 * PageableListViewNavigationWithMargin).
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapPagingNavigator extends PagingNavigator {

    public enum Position implements CssClassNameProvider {
        Left, Centered, Right;

        @Override
        public String cssClassName() {
            return equals(Left) ? "" : "pagination-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameModifier() {
            return new CssClassNameAppender(this);
        }

    }

    private Model<String> positionModel;

    public BootstrapPagingNavigator(String id, IPageable pageable) {
        super(id, pageable);

        commonInit();
    }

    public BootstrapPagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        super(id, pageable, labelProvider);

        commonInit();
    }

    public BootstrapPagingNavigator setPosition(Position position) {
        positionModel.setObject(position.cssClassName());
        return this;
    }

    private void commonInit() {
        positionModel = Model.of("");

        add(new AssertTagNameBehavior("div"),
            new BootstrapBaseBehavior(),
            new CssClassNameAppender("pagination"),
            new CssClassNameAppender(positionModel));
    }
}
