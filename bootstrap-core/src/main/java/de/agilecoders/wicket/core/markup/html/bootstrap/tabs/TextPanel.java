package de.agilecoders.wicket.core.markup.html.bootstrap.tabs;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * A simple {@link org.apache.wicket.markup.html.panel.Panel} implementation that shows a {@link Label}.
 *
 * @author miha
 * @version 1.0
 */
public class TextPanel extends GenericPanel<String> {
    public TextPanel(String id, IModel<String> model) {
        super(id, model);

        setRenderBodyOnly(true);
        add(new Label("content", model));
    }
}
