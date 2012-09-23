package de.agilecoders.wicket.markup.html.bootstrap.image;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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
    public Icon(final String componentId, final IModel<IconType> type) {
        super(componentId);

        add(iconBehavior = new IconBehavior(type, false));
    }

    public Icon(final IModel<IconType> type) {
        this("icon", type);
    }

    public Icon(final String id, final IconType type) {
        this(id, Model.of(type));
    }

    public Icon(final IconType type) {
        this("icon", Model.of(type));
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

    public IconType type() {
        return iconBehavior.type();
    }

}
