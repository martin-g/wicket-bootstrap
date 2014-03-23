package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;

import org.apache.wicket.markup.head.IHeaderResponse;

/**
 * just includes all bootstrap resource references.
 *
 * {@inheritDoc}
 *
 * @author miha
 */
public class BootstrapResourcesBehavior extends BootstrapJavascriptBehavior {

    /**
     * holder for singleton instance of {@link BootstrapResourcesBehavior}
     */
    private static final class Holder {
        private static final BootstrapResourcesBehavior INSTANCE = new BootstrapResourcesBehavior();
    }

    /**
     * @return default instance of {@link BootstrapResourcesBehavior}
     */
    public static BootstrapResourcesBehavior instance() {
        return Holder.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(final IBootstrapSettings settings, final IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        // just includes all bootstrap resource references.
        //adds the ccss header reference item. Makes sure that the CSS has been added if specified by CDN 
        final CssReferenceHeaderItem cssReferenceHeaderItem = CssReferenceHeaderItem.forReference(settings.getCssResourceReference(), "screen");
        headerResponse.render(cssReferenceHeaderItem);
    }

}
