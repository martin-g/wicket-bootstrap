package de.agilecoders.wicket.markup.html.bootstrap.layout.row;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.panel.DefaultMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class HeadingRow extends AbstractRow {

    public HeadingRow() {
    }

    public HeadingRow(IModel<?> model) {
        super(model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (tag.isOpenClose()) {
            // always transform the tag to <h2></h2> so even labels defined as <h2/> render
            tag.setType(XmlTag.TagType.OPEN);
        }

        tag.setName("h2");
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return DefaultMarkupSourcingStrategy.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        replaceComponentTagBody(markupStream, openTag, getDefaultModelObjectAsString());
    }

    @Override
    protected String newCssClassName() {
        return "";
    }
}
