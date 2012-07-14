package de.agilecoders.wicket.markup.html.bootstrap.layout.row;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.layout.AbstractLayout;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class AbstractRow extends Panel {

    List<Span> spanList = Lists.newArrayList();

    private AbstractRow(String id) {
        this();
    }

    public AbstractRow() {
        super("row");
    }

    public AbstractRow(IModel<?> model) {
        super("row", model);
    }

    public void addSpan(Span... spans) {
        if (spans != null) {
            for (Span span : spans) {
                if (spanList.size() <= AbstractLayout.MAX_WIDTH) {
                    spanList.add(span);
                } else {
                    throw new IllegalArgumentException("max number reached");
                }
            }
        } else {
            throw new IllegalArgumentException("array must contain at least one element");
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new ListView<Span>("elements", spanList) {
            @Override
            protected void populateItem(ListItem<Span> components) {
                components.add(components.getModelObject());
            }
        }.setVisible(spanList.size() > 0));

        final String cssClassName = newCssClassName();
        if (!Strings.isEmpty(cssClassName)) {
            add(new CssClassNameAppender(cssClassName));
        }
    }

    protected abstract String newCssClassName();
}
