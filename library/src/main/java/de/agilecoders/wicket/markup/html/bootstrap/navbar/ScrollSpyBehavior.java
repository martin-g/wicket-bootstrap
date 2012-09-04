package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

/**
 * A {@link ScrollSpyBehavior} updates the active state of a assigned
 * component according to the current scroll position.
 *
 * @author miha
 * @version 1.0
 */
public class ScrollSpyBehavior extends BootstrapBaseBehavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(OnDomReadyHeaderItem.forScript("$('#" + component.getMarkupId() + "').scrollspy();"));
    }
}
