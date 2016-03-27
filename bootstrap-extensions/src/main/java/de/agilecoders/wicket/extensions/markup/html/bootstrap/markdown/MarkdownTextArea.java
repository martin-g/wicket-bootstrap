package de.agilecoders.wicket.extensions.markup.html.bootstrap.markdown;


import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;

/**
 * A {@link TextArea} for editing Markdown documents.
 * Uses <a href="http://www.codingdrama.com/bootstrap-markdown/">Bootstrap Markdown</a>
 */
public class MarkdownTextArea extends TextArea {

    private final MarkdownConfig config;

    public MarkdownTextArea(String id) {
        this(id, null);
    }

    public MarkdownTextArea(String id, IModel model) {
        super(id, model);

        this.config = new MarkdownConfig();

        add(new MarkDownBehavior(config));
    }

    public MarkdownConfig getConfig() {
        return config;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

//        tag.put("data-provide", "markdown");
    }
}
