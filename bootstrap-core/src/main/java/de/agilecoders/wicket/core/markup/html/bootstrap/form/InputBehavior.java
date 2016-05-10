package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.SpanType;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * An {@link InputBehavior} controls the size of an input tag.
 *
 * @author miha
 */
public class InputBehavior extends BootstrapBaseBehavior {

    /**
     * Holder class for all possible input element height sizes
     */
    public enum Size implements ICssClassNameProvider {
        Small("sm"), Medium("md"), Large("lg");

        private final String cssName;

        Size(String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String cssClassName() {
            return this == Medium ? "" : "input-" + cssName;
        }

    }

    /**
     * Specifies the height of the input field
     */
    private ICssClassNameProvider heightSize;

    /**
     * Specifies the width of the input field by
     * surrounding it with a &lt;div class="col-xx-yy"&gt;
     */
    private SpanType columnSize;

    /**
     * Construct. Uses {@link Size#Medium} as default size.
     */
    public InputBehavior() {
        this(null, null);
    }

    /**
     * Construct.
     *
     * @param columnSize the column size of input tag.
     */
    public InputBehavior(final SpanType columnSize) {
        this(null, columnSize);
    }

    /**
     * Construct.
     *
     * @param heightSize the height size of input tag.
     */
    public InputBehavior(final Size heightSize) {
        this(heightSize, null);
    }

    public InputBehavior(final Size heightSize, final SpanType columnSize) {
        this.heightSize = heightSize;
        this.columnSize = columnSize;
    }

    /**
     * sets the size of input tag
     *
     * @param spanType the column size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final SpanType spanType) {
        this.columnSize = spanType;
        return this;
    }

    /**
     * sets the size of input tag
     *
     * @param heightSize the height size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final Size heightSize) {
        this.heightSize = heightSize;
        return this;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (heightSize != null ) {
            Attributes.addClass(tag, heightSize.cssClassName());
        }

        Attributes.addClass(tag, "form-control");
    }

    @Override
    public void beforeRender(Component component) {
        super.beforeRender(component);

        if (columnSize != null ) {
            component.getResponse().write("<div class=\"" + columnSize.cssClassName() + "\">");
        }
    }

    @Override
    public void afterRender(Component component) {
        super.afterRender(component);

        if (columnSize != null ) {
            component.getResponse().write("</div>");
        }
    }
}
