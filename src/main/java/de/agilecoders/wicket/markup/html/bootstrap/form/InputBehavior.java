package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.layout.SpanType;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class InputBehavior extends BootstrapBaseBehavior {

    public enum Size implements CssClassNameProvider {
        Mini, Small, Medium, Large, XLarge, XXLarge;

        @Override
        public String cssClassName() {
            return "input-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(cssClassName());
        }

    }

    private CssClassNameProvider size;

    public InputBehavior() {
        this(Size.Medium);
    }

    public InputBehavior(SpanType size) {
        this((CssClassNameProvider)size);
    }

    public InputBehavior(Size size) {
        this((CssClassNameProvider)size);
    }

    private InputBehavior(CssClassNameProvider size) {
        this.size = size;
    }

    public InputBehavior size(SpanType size) {
        this.size = size;
        return this;
    }

    public InputBehavior size(Size size) {
        this.size = size;
        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(size.newCssClassNameAppender());
    }
}
