package de.agilecoders.wicket.themes.markup.html.italia;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import de.agilecoders.wicket.core.settings.Theme;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

/**
 * #### Description
 *
 * java representation of Bootstrap Italia.
 * 
 * Move 'fonts', 'assets' and 'svg' dirs in '/bootstrap-italia/dist'.
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
        references.add(CssHeaderItem.forReference(ItaliaCssReference.instance()).setId(BOOTSTRAP_THEME_MARKUP_ID));
        references.add(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(ItaliaTheme.class, "js/bootstrap-italia.min.js")));
        references.add(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("owl.carousel/current/dist/owl.carousel.js")));
        return references;
    }

}
