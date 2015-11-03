package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

/**
 * A simple close button for the {@link Modal} component.
 *
 * @author miha
 */
public class ModalCloseButton extends AjaxLink<String> {

    private final ButtonBehavior buttonBehavior;

    private Modal<?> anchor;

    /**
     * Construct.
     */
    public ModalCloseButton() {
        this(Model.of("Close"));
    }

    /**
     * Construct.
     *
     * @param label The button label
     */
    public ModalCloseButton(final IModel<String> label) {
        this("button", label);
    }

    /**
     * Construct
     *
     * @param id
     * @param label
     *            The button label
     */
    public ModalCloseButton(final String id, final IModel<String> label) {

        super(id, label);

        setBody(getDefaultModel());
        buttonBehavior = new ButtonBehavior(Buttons.Type.Default);

        add(new AttributeModifier("data-dismiss", "modal"));
        add(buttonBehavior);
    }

    /**
     * sets the button type
     *
     * @param type The button type to use
     * @return this instance for chaining
     */
    public ModalCloseButton type(final Buttons.Type type) {
        buttonBehavior.setType(type);
        return this;
    }

    /**
     * sets the button size
     *
     * @param size The button size
     * @return this instance for chaining
     */
    public ModalCloseButton size(final Buttons.Size size) {
        buttonBehavior.setSize(size);
        return this;
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        Component _anchor = this.anchor;
        if (_anchor == null) {
            _anchor = findParent(Modal.class);
        }
        if (_anchor != null) {
            String anchorMarkupId = _anchor.getMarkupId();
            if (!Strings.isEmpty(anchorMarkupId)) {
                AjaxCallListener listener = new AjaxCallListener();
                listener.onBeforeSend(String.format("document.location.hash='%s';", anchorMarkupId));
                listener.onBeforeSend(String.format("$('#%s').modal('hide');", anchorMarkupId));
                attributes.getAjaxCallListeners().add(listener);
            }
        }
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
    }

    public ModalCloseButton setAnchor(Modal<?> anchor) {
        this.anchor = anchor;
        return this;
    }
}
