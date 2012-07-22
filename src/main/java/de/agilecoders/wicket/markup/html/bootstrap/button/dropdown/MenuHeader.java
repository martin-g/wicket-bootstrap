package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MenuHeader extends MenuPageButton<Page> {
    public MenuHeader(IModel<String> label) {
        super(Page.class);

        setRenderBodyOnly(true);
        setLabel(label);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        getParent().add(new CssClassNameAppender("nav-header"));
    }
}
