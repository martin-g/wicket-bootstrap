package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * To easily add affix behavior to any element, just add the {@code AffixBehavior} to the element you want to spy on.
 * Then use offsets to define when to toggle the pinning of an element on and off.
 * <p/>
 * <pre>
 *     add(new AffixBehavior("100")); // or
 *     add(new AffixBehavior("{x:100, y:50}")); // or
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class AffixBehavior extends Behavior {

    private final IModel<String> offset;

    /**
     * Constructor.
     *
     * @param offset when to toggle the pinning of an element on and off
     */
    public AffixBehavior(final String offset) {
        super();

        this.offset = Model.of(offset);
    }

    /**
     * Pixels to offset from screen when calculating position of scroll. If a single number is provide,
     * the offset will be applied in both top and left directions. To listen for a single direction,
     * or multiple unique offsets, just provided an object offset: { x: 10 }. Use a function when you need to
     * dynamically provide an offset (useful for some responsive designs).
     *
     * @param offset when to toggle the pinning of an element on and off
     * @return this
     */
    public AffixBehavior offset(final String offset) {
        this.offset.setObject(offset);
        return this;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);

        component.add(new AttributeModifier("data-offset-top", offset));
        component.add(new AttributeModifier("data-spy", "affix"));
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(OnDomReadyHeaderItem.forScript("$('#" + component.getMarkupId() + "').affix({ offset : " + offset.getObject() + " });"));
    }
}
