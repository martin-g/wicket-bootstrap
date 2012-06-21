package de.agilecoders.wicket.markup.html.bootstrap.image;

import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * An Icon component displays a non localizable image resource.
 *
 * @author miha
 * @version 1.0
 */
public class Icon extends WebMarkupContainer {

    private final IconBehavior iconBehavior;

    /**
     * @param componentId The non-null id of a new component
     * @param type        The type of the icon, e.g. Search, Home, User,...
     */
    public Icon(final String componentId, final IconType type) {
        super(componentId);

        iconBehavior = new IconBehavior(type);
        add(iconBehavior);
    }

    /**
     * @return true, if the icon color is inverted
     */
    public boolean isInverted() {
        return iconBehavior.isInverted();
    }

    /**
     * marks the icon as inverted.
     *
     * @return the component's current instance
     */
    public Icon invert() {
        iconBehavior.invert();

        return this;
    }

}
