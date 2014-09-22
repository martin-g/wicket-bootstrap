package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * A plain text navigation bar item
 *
 * @see <a href="http://getbootstrap.com/components/#navbar-text">Navbar Text</a>
 */
public class NavbarText extends Label {

    private Navbar.ComponentPosition position = Navbar.ComponentPosition.LEFT;

    public NavbarText(String id) {
        super(id);
    }

    public NavbarText(String id, String label) {
        super(id, label);
    }

    public NavbarText(String id, Serializable label) {
        super(id, label);
    }

    public NavbarText(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.setName("p");
        Attributes.addClass(tag, "navbar-text");

        if (position == Navbar.ComponentPosition.RIGHT) {
            Attributes.addClass(tag, "navbar-right");
        }
    }

    public NavbarText position(Navbar.ComponentPosition position) {
        this.position = position;
        return this;
    }
}
