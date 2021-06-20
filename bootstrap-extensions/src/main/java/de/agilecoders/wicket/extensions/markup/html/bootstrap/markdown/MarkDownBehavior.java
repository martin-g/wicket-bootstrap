package de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * Contributes Bootstrap-Markdown resources
 */
public class MarkDownBehavior extends Behavior {

    private final MarkdownConfig config;

    public MarkDownBehavior(MarkdownConfig config) {
        this.config = config;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(new MarkdownCssResourceReference()));
        if (config.contains(MarkdownConfig._IconLibrary)) {
            response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
        }

        response.render(JavaScriptHeaderItem.forReference(new MarkdownJsResourceReference()));
        response.render(OnDomReadyHeaderItem.forScript($(component).chain("markdown", config).get()));
    }
}
