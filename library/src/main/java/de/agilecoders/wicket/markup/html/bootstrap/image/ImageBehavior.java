package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ImageBehavior extends BootstrapBaseBehavior {

    /**
     * TODOC
     */
    public enum Type implements ICssClassNameProvider {
        Rounded, Circle, Polaroid, Default;

        @Override
        public String cssClassName() {
            return equals(Default) ? "" : "img-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameModifier() {
            return new CssClassNameAppender(cssClassName());
        }

    }

    private final Type borderType;

    /**
     * Construct.
     *
     * @param borderType The {@link Type} of the border
     */
    public ImageBehavior(Type borderType) {
        this.borderType = borderType;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(borderType.newCssClassNameModifier());
        component.add(new AssertTagNameBehavior("img"));
    }
}
