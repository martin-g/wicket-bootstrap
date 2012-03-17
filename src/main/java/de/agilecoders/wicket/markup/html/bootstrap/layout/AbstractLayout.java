package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.layout.row.AbstractRow;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class AbstractLayout extends Panel {
    public static final int MAX_WIDTH = 12;
    public static final int MAX_ROWS = 9999;

    List<AbstractRow> rowList = new ArrayList<>();
    private Layout layout;

    public AbstractLayout(String id) {
        super(id);

        commonInit();
    }

    public AbstractLayout(String id, IModel<?> model) {
        super(id, model);

        commonInit();
    }

    private void commonInit() {
        this.layout = newLayout();
    }

    protected abstract Layout newLayout();

    @Override
    public AbstractLayout add(Component... childs) {
        if (childs != null) {
            for (Component component : childs) {
                if (component instanceof AbstractRow) {
                    addRow((AbstractRow) component);
                } else {
                    throw new IllegalArgumentException("child component must be subclass of AbstractRow.");
                }
            }
        } else {
            throw new IllegalArgumentException("childs must be set.");
        }

        return this;
    }

    public void addRow(AbstractRow... rows) {
        if (rows != null) {
            for (AbstractRow row : rows) {
                if (rowList.size() <= AbstractLayout.MAX_ROWS) {
                    rowList.add(row);
                } else {
                    throw new IllegalArgumentException("max number of rows reached");
                }
            }
        } else {
            throw new IllegalArgumentException("row array must contain at least one element");
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new ListView<AbstractRow>("rows", rowList) {
            @Override
            protected void populateItem(ListItem<AbstractRow> components) {
                components.add(components.getModelObject());
            }
        }.setVisible(rowList.size() > 0));

        add(layout.newCssClassNameAppender());
    }
}
