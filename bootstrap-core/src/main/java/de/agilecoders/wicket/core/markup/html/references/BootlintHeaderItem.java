package de.agilecoders.wicket.core.markup.html.references;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

import java.util.Arrays;
import java.util.List;

/**
 * A JavaScript header item that could be used to check whether the markup of the page
 * uses Bootstrap the best way
 *
 * @see <a href="https://github.com/twbs/bootlint">bootlint</a>
 */
public class BootlintHeaderItem extends JavaScriptContentHeaderItem {

    public static final BootlintHeaderItem INSTANCE = new BootlintHeaderItem();

    /**
     * Constructor
     *
     * Configures checking for the current DOM document and generating a report
     * without disabled checks
     */
    private BootlintHeaderItem() {
        this("bootlint.showLintReportForCurrentDocument([], {'problemFree': false});");
    }

    /**
     * Constructor.
     *
     * @param javascript The JavaScript to execute once bootlint.js is loaded
     */
    public BootlintHeaderItem(String javascript) {
        super(javascript, "bootlint");
    }

    @Override
    public List<HeaderItem> getDependencies() {

        return Arrays.asList(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(BootlintHeaderItem.class, "js/bootlint.js")));
    }
}
