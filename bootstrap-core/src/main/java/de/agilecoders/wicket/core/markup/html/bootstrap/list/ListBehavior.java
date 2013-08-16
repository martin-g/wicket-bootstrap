package de.agilecoders.wicket.core.markup.html.bootstrap.list;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

/**
 * A bootstrap styled list of elements. (ol/ul/dl tag)
 *
 * @author miha
 */
public class ListBehavior extends BootstrapBaseBehavior {

    /**
     * @return a new description list behavior
     */
    public static ListBehavior description() {
        return new ListBehavior(Type.DL);
    }

    /**
     * @return a new unordered list behavior
     */
    public static ListBehavior unordered() {
        return new ListBehavior(Type.UL);
    }

    /**
     * @return a new ordered list behavior
     */
    public static ListBehavior ordered() {
        return new ListBehavior(Type.OL);
    }

    /**
     * all possible list tags.
     */
    private enum Type {
        DYNAMIC, OL, UL, DL
    }

    private boolean unstyled = false;
    private boolean horizontal;
    private final Type type;

    /**
     * Constructor using {@link Type#DYNAMIC}.
     */
    public ListBehavior() {
        this(Type.DYNAMIC);
    }

    /**
     * Construct.
     *
     * @param type the type of the list.
     */
    private ListBehavior(final Type type) {
        super();

        this.type = type;
    }

    /**
     * sets a special css class so that this list will be rendered horizontally.
     *
     * @return this instance for chaining.
     */
    public ListBehavior horizontal() {
        horizontal = true;

        return this;
    }

    /**
     * whether to us styles or not for ul tags.
     *
     * @return this instance for chaining
     */
    public ListBehavior unstyled() {
        unstyled = true;

        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (!Type.DYNAMIC.equals(type)) {
            tag.setName(type.name().toLowerCase());
        }

        Components.assertTag(component, tag, "ul", "ol", "dl");

        if (unstyled && Type.UL.equals(type)) {
            Attributes.addClass(tag, "list-unstyled");
        }

        if (horizontal && Type.DL.equals(type)) {
            Attributes.addClass(tag, "dl-horizontal");
        }
    }

}
