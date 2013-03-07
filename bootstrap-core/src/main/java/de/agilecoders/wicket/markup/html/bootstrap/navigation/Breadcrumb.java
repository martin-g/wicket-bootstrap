package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Attributes;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.ComponentTag;

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
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "ul");
        Attributes.addClass(tag, "breadcrumb");
    }
}
