package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class FormBehavior extends BootstrapBaseBehavior {

    public enum Type implements CssClassNameProvider {
        Default, Vertical, Inline, Search, Horizontal;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "form-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(cssClassName());
        }

    }

    private Type type;

    public FormBehavior() {
        this(Type.Default);
    }

    public FormBehavior(Type type) {
        this.type = type;
    }

    public FormBehavior type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        component.add(type.newCssClassNameAppender());
    }
}
