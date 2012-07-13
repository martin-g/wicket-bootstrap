package de.agilecoders.wicket.markup.html.references;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.util.References;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapResponsiveCssReference extends CssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    public static final CssResourceReference INSTANCE = new BootstrapResponsiveCssReference();

    /**
     * Private constructor.
     */
    private BootstrapResponsiveCssReference() {
        super(BootstrapResponsiveCssReference.class, References.appendMinificationIdentifier("bootstrap-responsive.css"));
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(CssHeaderItem.forReference(BootstrapCssReference.INSTANCE));

        return dependencies;
    }
}