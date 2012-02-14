package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Span extends Panel {

    private int width = 1;
    private int offset = 0;

    private Span(String id) {
        this();
    }

    public Span(IModel<?> model) {
        super("span", model);
    }

    public Span() {
        super("span");
    }

    public int getWidth() {
        return width;
    }

    public Span width(int width) {
        this.width = width;

        return this;
    }

    public int getOffset() {
        return offset;
    }

    public Span offset(int offset) {
        this.offset = offset;

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (width < 0 || offset < 0) {
            throw new IllegalArgumentException("negative values for width/offset are not allowed");
        }

        if (width + offset > AbstractLayout.MAX_WIDTH) {
            throw new IllegalArgumentException("width + offset is larger than max width");
        }

        if (width > 0) {
            add(new CssClassNameAppender("span" + width));
        }

        if (offset > 0) {
            add(new CssClassNameAppender("offset" + offset));
        }

        setVisible(width > 0);
    }
}
