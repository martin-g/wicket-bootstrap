package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
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

    private IModel<String> pullRight = Model.of("");

    /**
     * Construct.
     */
    public QuoteBehavior() {
        super("blockquote");
    }

    /**
     * sets the floating of the quote to "right".
     *
     * @return this component's instance
     */
    public QuoteBehavior pullRight() {
        pullRight.setObject("pull-right");

        return this;
    }

    /**
     * sets the floating of the quote to "left".
     *
     * @return this component's instance
     */
    public QuoteBehavior pullLeft() {
        pullRight.setObject("");

        return this;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new CssClassNameAppender(pullRight));
    }
}
