package de.agilecoders.wicket.markup.html.themes.cerulean;

import de.agilecoders.wicket.less.Resource;
import de.agilecoders.wicket.markup.html.themes.bootstrap.BootstrapCssReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CeruleanCssReference extends BootstrapCssReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final CeruleanCssReference INSTANCE = new CeruleanCssReference();

    /**
     * Private constructor.
     */
    public CeruleanCssReference() {
        super(CeruleanCssReference.class, "css/bootstrap.cerulean.css");
    }

    @Override
    public List<Resource> files() {
        List<Resource> resources = super.files();
        resources.add(resourceLocator().findResource(CeruleanCssReference.class, "less/variables.less"));
        resources.add(resourceLocator().findResource(CeruleanCssReference.class, "less/cerulean.less"));

        return resources;
    }
}