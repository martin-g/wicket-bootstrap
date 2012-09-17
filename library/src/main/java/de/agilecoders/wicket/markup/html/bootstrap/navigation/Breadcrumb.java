package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Breadcrumb extends BreadCrumbBar {

    public Breadcrumb(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BootstrapBaseBehavior(),
            new AssertTagNameBehavior("ul"),
            new CssClassNameAppender("breadcrumb"));
    }
}
