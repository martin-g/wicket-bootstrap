package de.agilecoders.wicket.markup.html.bootstrap.image;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import org.apache.wicket.Component;

/**
 * An Icon component displays a non localizable image resource.
 *
 * @author miha
 * @version 1.0
 */
public class IconBehavior extends AssertTagNameBehavior {

    private final IconType type;
    private boolean invert = false;

    /**
     * @param type The type of the icon, e.g. Search, Home, User,...
     */
    public IconBehavior(final IconType type) {
        super("i");

        this.type = type;
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        if (type != null && !type.equals(IconType.NULL)) {
            final String invertPostfix = isInverted() ? "icon-white" : "";

            component.add(new CssClassNameAppender(Lists.newArrayList(type.cssClassName(), invertPostfix)));
        } else {
            component.setVisible(false);
        }
    }

    /**
     * @return true, if the icon color is inverted
     */
    public boolean isInverted() {
        return this.invert;
    }

    /**
     * marks the icon as inverted.
     *
     * @return the component's current instance
     */
    public IconBehavior invert() {
        this.invert = true;

        return this;
    }

    public IconType type() {
        return type;
    }
}
