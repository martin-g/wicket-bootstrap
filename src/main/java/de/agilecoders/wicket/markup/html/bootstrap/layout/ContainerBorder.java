package de.agilecoders.wicket.markup.html.bootstrap.layout;

import de.agilecoders.wicket.markup.html.bootstrap.border.CssClassNameBorder;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ContainerBorder extends CssClassNameBorder {

    public ContainerBorder(String id) {
        super(id);

        commonInit();
    }

    public ContainerBorder(String id, IModel<?> model) {
        super(id, model);

        commonInit();
    }

    private void commonInit() {
        withLayout(Layout.Fixed);
    }

    public Layout layout() {
        return (Layout) cssClassNameProvider();
    }

    public ContainerBorder withLayout(Layout layout) {
        withCssClassNameProvider(layout);

        return this;
    }

}
