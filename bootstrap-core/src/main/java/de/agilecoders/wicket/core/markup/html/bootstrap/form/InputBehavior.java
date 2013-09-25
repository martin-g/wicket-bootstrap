package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.SpanType;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * An {@link InputBehavior} controls the size of an input tag.
 *
 * @author miha
 */
public class InputBehavior extends BootstrapBaseBehavior {

    /**
     * Holder class for all possible input element sizes
     */
    public static enum Size implements ICssClassNameProvider {
        Small("sm"), Medium("md"), Large("lg");

        private final String cssName;

        private Size(String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String cssClassName() {
            return this == Medium ? "" : "input-" + cssName;
        }

    }

    private IModel<ICssClassNameProvider> size;

    /**
     * Construct. Uses {@link Size#Medium} as default size.
     */
    public InputBehavior() {
        this((Size) null);
    }

    /**
     * Construct.
     *
     * @param size size of input tag.
     */
    public InputBehavior(final SpanType size) {
        this((ICssClassNameProvider) size);
    }

    /**
     * Construct.
     *
     * @param size size of input tag.
     */
    public InputBehavior(final Size size) {
        this((ICssClassNameProvider) size);
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
        this.size = Model.<ICssClassNameProvider>of(size);
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

        if (size != null && size.getObject() != null) {
            Attributes.addClass(tag, size.getObject().cssClassName());
        }
    }

}
