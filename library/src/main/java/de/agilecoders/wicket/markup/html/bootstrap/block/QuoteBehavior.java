package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Block-level element for quoting content from another source.
 * <p/>
 * <pre>
 *     <blockquote wicket:id="componentId">
 *          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante venenatis.</p>
 *          <small>Someone famous</small>
 *     </blockquote>
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class QuoteBehavior extends AssertTagNameBehavior {

    private final IModel<String> pullRight;
    private final CssClassNameAppender cssClassNameAppender;

    /**
     * Construct.
     */
    public QuoteBehavior() {
        super("blockquote");

        this.pullRight = Model.of("");
        this.cssClassNameAppender = new CssClassNameAppender(pullRight);
    }

    /**
     * sets the floating of the quote to "right".
     *
     * @return this component's instance
     */
    public final QuoteBehavior pullRight() {
        pullRight.setObject("pull-right");

        return this;
    }

    /**
     * sets the floating of the quote to "left".
     *
     * @return this component's instance
     */
    public final QuoteBehavior pullLeft() {
        pullRight.setObject("");

        return this;
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
        component.add(cssClassNameAppender);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
        component.remove(cssClassNameAppender);
    }
}
