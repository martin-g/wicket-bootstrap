package de.agilecoders.wicket.core.markup.html.bootstrap.image;

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

        add(iconBehavior = new IconBehavior(type));
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
     * sets a new icon type
     *
     * @return this instance for chaining
     */
    public final Icon setType(final IconType iconType) {
        iconBehavior.setType(iconType);
        return this;
    }

    /**
     * @return true, if an {@link IconType} is set and it's not equal to IconType.Null
     */
    public final boolean hasIconType() {
        return iconBehavior.hasIconType();
    }

    /**
     * @return current icon type
     */
    public IconType getType() {
        return iconBehavior.type();
    }

    /**
     * @deprecated please use {@link #getType()} instead.
     * @return current icon type
     */
    public IconType type() {
        return iconBehavior.type();
    }

}
