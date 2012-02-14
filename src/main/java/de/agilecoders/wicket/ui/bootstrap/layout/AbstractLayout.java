package de.agilecoders.wicket.ui.bootstrap.layout;

import de.agilecoders.wicket.ui.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.ui.bootstrap.layout.row.AbstractRow;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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

    public AbstractLayout(String id) {
        super(id);
    }

    public AbstractLayout(String id, IModel<?> model) {
        super(id, model);
    }

    public void addRow(AbstractRow... rows) {
        if (rows != null) {
            for (AbstractRow row : rows) {
                if (rowList.size() <= AbstractLayout.MAX_ROWS) {
                    rowList.add(row);
                } else {
                    throw new IllegalArgumentException("max number reached");
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

        add(new CssClassNameAppender(newCssClassName()));
    }

    protected abstract String newCssClassName();
}
