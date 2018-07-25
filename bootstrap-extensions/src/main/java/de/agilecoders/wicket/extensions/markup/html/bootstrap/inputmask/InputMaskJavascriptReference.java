package de.agilecoders.wicket.extensions.markup.html.bootstrap.inputmask;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * Robin Herbots Input Mask Javascript resource reference.
 *
 * @author Jan Ferko
 */
public final class InputMaskJavascriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Singleton instance of this reference.
     */
    private static final class Holder {
        public static final InputMaskJavascriptReference INSTANCE = new InputMaskJavascriptReference();
    }

    /**
     * Construct resource reference.
     */
    private InputMaskJavascriptReference() {
        super("inputmask/current/dist/jquery.inputmask.bundle.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> dependencies = new ArrayList<>(super.getDependencies());
        dependencies.add(
                JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference())
        );
        return dependencies;

    }

    /**
     * @return the single instance of the resource reference
     */
    public static InputMaskJavascriptReference getInstance() {
        return Holder.INSTANCE;
    }
}
