package de.agilecoders.wicket.themes.markup.html.italia;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * #### Description
 *
 * java representation of Bootstrap Italia.
 *
 * #### Usage
 *
 * ```java
 * settings.setThemeProvider(new SingleThemeProvider(new ItaliaTheme()));
 * ```
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
		List<HeaderItem> references = new ArrayList<HeaderItem>();
		references.add(
				CssHeaderItem.forReference(new WebjarsCssResourceReference("splide/current/css/splide-core.css")));
		references.add(CssHeaderItem
				.forReference(
						new WebjarsCssResourceReference("bootstrap-italia/current/css/bootstrap-italia.css"))
				.setId(BOOTSTRAP_THEME_MARKUP_ID));
		references.add(new PriorityHeaderItem(JavaScriptHeaderItem
				.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference())));
		references.add(JavaScriptHeaderItem.forReference(
				new WebjarsJavaScriptResourceReference("popper.js/current/umd/popper.js")));
		references.add(JavaScriptHeaderItem.forReference(
				new WebjarsJavaScriptResourceReference("splide/current/js/splide.js")));
		references.add(JavaScriptHeaderItem.forScript("window.__PUBLIC_PATH__ = '/wicket/resource/de.agilecoders.wicket.webjars.request.resource.IWebjarsResourceReference/webjars/bootstrap-italia/current/fonts'", "bootstrap-italia-fonts"));
		references.add(JavaScriptHeaderItem
				.forReference(
						new WebjarsJavaScriptResourceReference("bootstrap-italia/current/js/bootstrap-italia.js"))
				.setId("bootstrap-js"));
		return references;
    }

}
