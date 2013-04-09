package de.agilecoders.wicket.extensions.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import static de.agilecoders.wicket.core.util.JQuery.$;

/**
 * The {@link DropDownAutoOpen} behavior opens a {@link de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton}
 * when it is hovered.
 *
 * @author miha
 */
public class DropDownAutoOpen extends BootstrapBaseBehavior {

    private final DropDownAutoOpenConfig config;
    private final AttributeModifier behavior;

    /**
     * Construct.
     *
     * @param config the configuration
     */
    public DropDownAutoOpen(final DropDownAutoOpenConfig config) {
        super();

        this.config = config;
        this.behavior = new AttributeModifier("data-hover", "dropdown");
    }

    /**
     * Construct.
     */
    public DropDownAutoOpen() {
        this(new DropDownAutoOpenConfig());
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(JavaScriptHeaderItem.forReference(DropdownAutoOpenJavaScriptReference.instance()));
        headerResponse.render($(((DropDownButton)component).getBaseButton()).chain("dropdownHover", config).asDomReadyScript());
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        if (!(component instanceof DropDownButton)) {
            throw new IllegalArgumentException("behavior can't be added to any other component than DropDownButton; got " + component.getClass().getName());
        }

        ((DropDownButton)component).addToButton(behavior);
    }

    @Override
    public void unbind(Component component) {
        super.unbind(component);

        component.remove(behavior);
    }
}
