package de.agilecoders.wicket.core.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.ComponentTag;

/**
 * A component that renders bread crumbs like {@link BreadCrumbBar} that is
 * styled with bootstrap.
 *
 * @author miha
 */
public class Breadcrumb extends BreadCrumbBar {

    /**
     * Construct.
     *
     * @param id The component id
     */
    public Breadcrumb(final String id) {
        super(id);
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
    
	/**
	 * Overrides the method in the super class to remove the default / separator since bootstrap adds the separators via CSS.
	 * 
	 * @see org.apache.wicket.extensions.breadcrumb.BreadCrumbBar#getSeparatorMarkup()
	 */
	@Override
	protected String getSeparatorMarkup() {

		return "";
	}
}
