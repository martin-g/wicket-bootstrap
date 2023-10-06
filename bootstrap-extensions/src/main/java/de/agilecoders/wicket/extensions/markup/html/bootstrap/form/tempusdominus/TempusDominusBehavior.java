package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import java.util.List;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.TempusDominusCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.TempusDominusJsReference;
import de.agilecoders.wicket.jquery.function.Function;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * TempusDominus behavior for Eonasdan tempus-dominus picker plugin.
 *
 */
public class TempusDominusBehavior extends BootstrapJavascriptBehavior {
    private static final long serialVersionUID = 1L;

    private final TempusDominusConfig config;

    /**
     * Construct instance
     *
     * @param config config
     */
    public TempusDominusBehavior(TempusDominusConfig config) {
        this.config = config;
    }

    private void addResourceIfExists(final IHeaderResponse response, String path) {
        WebjarsJavaScriptResourceReference ref = new WebjarsJavaScriptResourceReference(path);
        if (ref.getResource().getResourceStream() != null) {
            response.render(JavaScriptHeaderItem.forReference(ref));
        }
    }

    @Override
    public void renderHead(Component component, final IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(TempusDominusCssReference.asHeaderItem());
        response.render(TempusDominusJsReference.asHeaderItem());
        Locale locale = config.getLocale();
        if (!Locale.ENGLISH.equals(locale)) {
            if (!Strings.isEmpty(locale.getCountry())) {
                addResourceIfExists(response, "eonasdan__tempus-dominus/current/dist/locales/" + locale.toLanguageTag() + ".js");
            }
            if (!Locale.ENGLISH.getLanguage().equals(locale.getLanguage())) {
                addResourceIfExists(response, "eonasdan__tempus-dominus/current/dist/locales/" + locale.getLanguage() + ".js");
            }
        }
        response.render(OnDomReadyHeaderItem.forScript(new Function(
            "createTempusDominus"
            , component.getMarkupId()
            , config
            , config.getLocalization()
            , locale.getLanguage()
            ).build()
        ));
    }

    @Override
    public void onEvent(Component component, IEvent<?> event) {
        super.onEvent(component, event);
        if (event.getPayload() instanceof IPartialPageRequestHandler) {
            IPartialPageRequestHandler target = (IPartialPageRequestHandler) event.getPayload();
            if (target.getComponents().contains(component)) {
                // if this component is being repainted by ajax, directly, we must destroy tempus-dominus so it removes
                // its elements from DOM
                target.prependJavaScript(new Function("destroyTempusDominus", component.getMarkupId()).build());
            }
        }
    }

}
