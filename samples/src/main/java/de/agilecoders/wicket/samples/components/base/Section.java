package de.agilecoders.wicket.samples.components.base;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Section<T> extends GenericPanel<T> {

    public Section(String id) {
        super(id);

        internalInit();
    }

    public Section(String id, IModel<T> model) {
        super(id, model);

        internalInit();
    }

    private void internalInit() {
        setOutputMarkupId(true);
        setMarkupId(getId());
    }
}
