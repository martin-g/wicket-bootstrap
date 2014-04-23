package de.agilecoders.wicket.extensions.markup.html.bootstrap.button;

import com.google.common.collect.Lists;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.List;

/**
 * A {@link org.apache.wicket.request.resource.JavaScriptResourceReference} that includes the dropdown
 * auto open script.
 *
 * @author miha
 */
public class DropdownAutoOpenJavaScriptReference extends JavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference
     */
    private static final DropdownAutoOpenJavaScriptReference INSTANCE = new DropdownAutoOpenJavaScriptReference();


    /**
     * @return the single instance of the resource reference
     */
    public static DropdownAutoOpenJavaScriptReference instance() {
        return INSTANCE;
    }

    /**
     * Private constructor.
     */
    private DropdownAutoOpenJavaScriptReference() {
        super(DropdownAutoOpenJavaScriptReference.class, "dropdown-autoopen.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));

        return dependencies;
    }
}
