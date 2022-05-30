package de.agilecoders.wicket.themes.markup.html.italia;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * A {@link de.agilecoders.wicket.core.settings.ITheme theme} for Bootstrap
 * provided by <a href="https://developers.italia.it/">developers.italia.it</a>.
 *
 * <h2>Usage</h2>
 * <pre>settings.setThemeProvider(new SingleThemeProvider(new ItaliaTheme()));</pre>
 *
 * @author Erik Geletti
 */
public class ItaliaTheme extends Theme {

	/**
     * Construct.
     */
    private ItaliaTheme(final String name) {
    	super(name);
    }

    /**
     * Construct.
     */
    public ItaliaTheme() {
        this("italia");
    }

    @Override
    public List<HeaderItem> getDependencies() {
		List<HeaderItem> references = new ArrayList<>();
		references.add(
				CssHeaderItem.forReference(new WebjarsCssResourceReference("splide/current/css/splide-core.min.css")));
		references.add(CssHeaderItem
				.forReference(
						new WebjarsCssResourceReference("bootstrap-italia/current/css/bootstrap-italia.min.css"))
				.setId(BOOTSTRAP_THEME_MARKUP_ID));
		references.add(new PriorityHeaderItem(JavaScriptHeaderItem
				.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference())));
		references.add(JavaScriptHeaderItem.forReference(
				new WebjarsJavaScriptResourceReference("popper.js/current/umd/popper.js")));
		references.add(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("splide/current/js/splide.js")));

		WebjarsJavaScriptResourceReference italiaJsReference = new WebjarsJavaScriptResourceReference("bootstrap-italia/current/js/bootstrap-italia.min.js");

		CharSequence urlForJs = RequestCycle.get().urlFor(italiaJsReference, null);

		Url fontsUrl = Url.parse(urlForJs);
		fontsUrl.resolveRelative(Url.parse("../fonts"));

		references.add(JavaScriptHeaderItem.forScript("window.__PUBLIC_PATH__ = '"+fontsUrl+"'", "bootstrap-italia-fonts"));
		references.add(JavaScriptHeaderItem.forReference(italiaJsReference).setId("bootstrap-js"));

		return references;
	}

}
