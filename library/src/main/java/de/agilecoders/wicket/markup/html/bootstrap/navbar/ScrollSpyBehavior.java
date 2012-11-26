package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import static de.agilecoders.wicket.markup.html.bootstrap.navbar.ScrollSpyBehavior.ScrollspyJqueryFunction.scrollspy;
import static de.agilecoders.wicket.util.JQuery.$;
import static de.agilecoders.wicket.util.JQuery.EachJqueryFunction.each;

/**
 * A {@link ScrollSpyBehavior} updates the active state of a assigned
 * component according to the current scroll position.
 *
 * @author miha
 * @version 1.0
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

        headerResponse.render(OnDomReadyHeaderItem.forScript($(component).chain(scrollspy()).get()));
    }

    /**
     * A jquery function abstraction for scrollspy.
     */
    public static final class ScrollspyJqueryFunction extends JQuery.AbstractFunction {

        /**
         * @return new {@link ScrollspyJqueryFunction} instance.
         */
        public static ScrollspyJqueryFunction scrollspy() {
            return new ScrollspyJqueryFunction();
        }

        /**
         * Construct.
         */
        protected ScrollspyJqueryFunction() {
            super("scrollspy");
        }
    }
}
