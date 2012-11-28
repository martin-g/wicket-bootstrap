package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;

/**
 * A component that renders bread crumbs like {@link BreadCrumbBar} that is
 * styled with twitter-bootstrap.
 *
 * @author miha
 */
public class Breadcrumb extends BreadCrumbBar {

    /**
     * Construct.
     *
     * @param markupId
     */
    public Breadcrumb(final String markupId) {
        super(markupId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        BootstrapBaseBehavior.addTo(this);

        add(new AssertTagNameBehavior("ul"),
            new CssClassNameAppender("breadcrumb"));
    }
}
