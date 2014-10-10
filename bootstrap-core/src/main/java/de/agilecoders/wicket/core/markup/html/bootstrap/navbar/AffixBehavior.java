package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.util.lang.Args;

/**
 * To easily add affix behavior to any element, just add the {@code AffixBehavior} to the element you want to spy on.
 * Then use offsets to define when to toggle the pinning of an element on and off.
 * <p/>
 * <pre>
 *     add(new AffixBehavior("100")); // or
 *     add(new AffixBehavior("{x:100, y:50}"));
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

        Args.notEmpty(offset, "offset");
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
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Attributes.set(tag, "data-offset-top", offset.getObject());
        Attributes.set(tag, "data-spy", "affix");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(OnDomReadyHeaderItem.forScript("$('#" + Strings2.escapeMarkupId(component.getMarkupId()) +
                                                       "').affix({ offset : " + offset.getObject() + " });"));
    }
}
