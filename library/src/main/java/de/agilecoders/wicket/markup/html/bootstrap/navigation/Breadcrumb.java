package de.agilecoders.wicket.markup.html.bootstrap.navigation;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public abstract class Breadcrumb<T> extends ListView<T> {
    public Breadcrumb(String id) {
        super(id);
    }

    public Breadcrumb(String id, IModel<List<? extends T>> model) {
        super(id, model);
    }

    public Breadcrumb(String id, List<T> list) {
        super(id, list);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BootstrapBaseBehavior(),
            new AssertTagNameBehavior("ul"),
            new CssClassNameAppender("breadcrumb"));
    }
}
