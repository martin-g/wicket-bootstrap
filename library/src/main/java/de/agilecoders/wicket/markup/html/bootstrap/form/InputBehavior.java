package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.markup.html.bootstrap.layout.SpanType;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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
        public CssClassNameAppender newCssClassNameModifier() {
            return new CssClassNameAppender(cssClassName());
        }

    }

    private final IModel<CssClassNameProvider> size;

    public InputBehavior() {
        this(Size.Medium);
    }

    public InputBehavior(final SpanType size) {
        this((CssClassNameProvider)size);
    }

    public InputBehavior(final Size size) {
        this((CssClassNameProvider)size);
    }

    private InputBehavior(final CssClassNameProvider size) {
        this.size = Model.of(size);
    }

    public InputBehavior size(final SpanType size) {
        this.size.setObject(size);
        return this;
    }

    public InputBehavior size(final Size size) {
        this.size.setObject(size);
        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(size.getObject().newCssClassNameModifier());
    }
}
