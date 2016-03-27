package de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown;

import org.apache.wicket.request.resource.CssResourceReference;

public class MarkdownCssResourceReference extends CssResourceReference {

    public MarkdownCssResourceReference() {
        super(MarkdownCssResourceReference.class, "res/css/bootstrap-markdown.min.css");
    }
}
