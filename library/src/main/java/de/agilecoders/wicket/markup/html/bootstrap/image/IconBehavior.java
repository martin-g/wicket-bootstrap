package de.agilecoders.wicket.markup.html.bootstrap.image;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * An Icon component displays a non localizable image resource.
 *
 * @author miha
 * @version 1.0
 */
public class IconBehavior extends AssertTagNameBehavior implements Invertible {

    private final IModel<IconType> type;
    private final IModel<String> value;
    private final IModel<Boolean> invert;

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     * @param invert whether to invert the icon or not
     */
    public IconBehavior(final IconType type, final boolean invert) {
        this(Model.of(type), invert);
    }

    /**
     * Construct.
     *
     * @param type   The type of the icon, e.g. Search, Home, User,...
     * @param invert whether to invert the icon or not
     */
    public IconBehavior(final IModel<IconType> type, final boolean invert) {
        super("i");

        this.type = type;
        this.value = Model.of("");
        this.invert = Model.of(invert);
    }

    /**
     * Construct.
     *
     * @param type The type of the icon, e.g. Search, Home, User,...
     */
    public IconBehavior(final IconType type) {
        this(Model.of(type), false);
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new CssClassNameAppender(value));
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        if (hasIconType()) {
            final String invertPostfix = isInverted() ? " icon-white" : "";

            value.setObject(type.getObject().cssClassName() + invertPostfix);
        } else {
            value.setObject("");
            component.setVisible(false);
        }
    }

    /**
     * @return true, if an {@link IconType} is set
     */
    private boolean hasIconType() {
        return type != null && type.getObject() != null && !type.getObject().equals(IconType.NULL);
    }

    /**
     * @return true, if the icon color is inverted
     */
    public boolean isInverted() {
        return this.invert.getObject();
    }

    /**
     * marks the icon as inverted.
     *
     * @return the component's current instance
     */
    public IconBehavior invert() {
        setInverted(true);

        return this;
    }

    /**
     * @return the current icon type
     */
    public IconType type() {
        return type.getObject();
    }

    public void setInverted(final boolean value) {
        this.invert.setObject(value);
    }
}
