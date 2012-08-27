package de.agilecoders.wicket.markup.html.themes.bootstrap;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.less.LessResourceReference;
import de.agilecoders.wicket.less.Resource;

import java.util.List;

/**
 * The default bootstrap css.
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapCssReference extends LessResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final BootstrapCssReference INSTANCE = new BootstrapCssReference();

    /**
     * Private constructor.
     */
    private BootstrapCssReference() {
        super(BootstrapCssReference.class, "css/bootstrap.css");
    }

    protected BootstrapCssReference(Class clazz, String name) {
        super(clazz, name);
    }

    @Override
    public List<Resource> getLessResources() {
        return Lists.newArrayList(
                resourceLocator().findResource(BootstrapCssReference.class, "less/bootstrap.less")
        );
    }
}