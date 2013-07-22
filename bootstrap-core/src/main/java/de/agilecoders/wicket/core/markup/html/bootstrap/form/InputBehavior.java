package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.SpanType;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * An {@link InputBehavior} controls the size of an input tag.
 *
 * @author miha
 */
public class InputBehavior extends BootstrapBaseBehavior {

    /**
     * Holder class for all possible input element sizes
     */
    public enum Size implements ICssClassNameProvider {
        Mini, Small, Medium, Large, XLarge, XXLarge;

        @Override
        public String cssClassName() {
            return "input-" + name().toLowerCase();
        }

    }

    private final IModel<ICssClassNameProvider> size;

    /**
     * Construct. Uses {@link Size#Medium} as default size.
     */
    public InputBehavior() {
        this((ICssClassNameProvider)null);
    }

    /**
     * Construct.
     *
     * @param size size of input tag.
     */
    public InputBehavior(final SpanType size) {
        this((ICssClassNameProvider)size);
    }

    /**
     * Construct.
     *
     * @param size size of input tag.
     */
    public InputBehavior(final Size size) {
        this((ICssClassNameProvider)size);
    }

    /**
     * Construct.
     *
     * @param size size of input tag.
     */
    private InputBehavior(final ICssClassNameProvider size) {
        this.size = Model.of(size);
    }

    /**
     * sets the size of input tag
     *
     * @param size the size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final SpanType size) {
        this.size.setObject(size);
        return this;
    }

    /**
     * sets the size of input tag
     *
     * @param size the size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final Size size) {
        this.size.setObject(size);
        return this;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);
        if (size != null) {
            Attributes.addClass(tag, size.getObject().cssClassName());
        }
    }

}
