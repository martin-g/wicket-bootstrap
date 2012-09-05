package de.agilecoders.wicket.markup.html.themes.bootstrap;

import java.util.List;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.less.LessResourceReference;
import de.agilecoders.wicket.less.Resource;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResponsiveCssReference extends LessResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapResponsiveCssReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super(BootstrapResponsiveCssReference.class, "css/bootstrap-responsive.css");
    }

    
    protected BootstrapResponsiveCssReference(Class<?> scope, String name) {
        super(scope, name);
    }

    @Override
    public List<Resource> getLessResources() {
        return Lists.newArrayList(
                resourceLocator().findResource(BootstrapCssReference.class, "less/responsive.less")
        );
    }

}