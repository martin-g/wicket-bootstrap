package de.agilecoders.wicket.samples.assets.base;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.references.BootstrapJavaScriptReference;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ApplicationJavaScript extends JavaScriptResourceReference {

    public static final ApplicationJavaScript INSTANCE = new ApplicationJavaScript();

    private ApplicationJavaScript() {
        super(ApplicationJavaScript.class, "application.js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        final List<HeaderItem> dependencies = Lists.newArrayList(super.getDependencies());
        dependencies.add(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
        dependencies.add(JavaScriptHeaderItem.forReference(BootstrapJavaScriptReference.instance()));

        return dependencies;
    }
}
