package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import org.apache.wicket.markup.head.IHeaderResponse;

import de.agilecoders.wicket.core.settings.IBootstrapSettings;

/**
 * #### Description
 *
 * just includes all bootstrap resource references. If {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#autoAppendResources()}
 * is true, there's no need to add it manually.
 *
 * #### Usage
 *
 * ```java
 * component.add(BootstrapResourceBehavior.instance());
 * ```
 */
public class BootstrapResourcesBehavior extends BootstrapJavascriptBehavior {
    private static final long serialVersionUID = 1L;

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

    @Override
    public void renderHead(final IBootstrapSettings settings, final IHeaderResponse headerResponse) {
        super.renderHead(settings, headerResponse);

        // just includes all bootstrap resource references.
    }

}
