package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;

import static de.agilecoders.wicket.util.JQuery.$;
import static de.agilecoders.wicket.util.JQuery.EachJqueryFunction.each;

/**
 * A {@link ScrollSpyBehavior} updates the active state of a assigned
 * component according to the current scroll position.
 *
 * @author miha
 */
public class ScrollSpyBehavior extends BootstrapBaseBehavior {

    /**
     * updates the ui after new dom elements were added or removed.
     *
     * @param target The current active {@link org.apache.wicket.ajax.AjaxRequestTarget}
     */
    public static void refresh(final AjaxRequestTarget target) {
        target.appendJavaScript($("[data-spy=\"scroll\"]")
                                        .chain(each(new JQuery.JavaScriptInlineFunction("var $spy = $(this).scrollspy('refresh');"))).get());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render($(component).chain("scrollspy").asDomReadyScript());
    }
}
