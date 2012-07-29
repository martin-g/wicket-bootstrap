package de.agilecoders.wicket.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Modal extends Panel {

    private WebMarkupContainer header;
    private boolean showAfterInitialize = false;
    private Component body;
    private boolean fadein = true;
    private boolean keyboard = true;
    private Label headerLabel;

    public Modal(String id, Component body) {
        super(id);

        if (!"body".equals(body.getId())) {
            throw new IllegalArgumentException("invalid body markup id. Must be 'body'.");
        }

        this.body = body;
        add(body);

        commonInit();
    }

    public Modal(String id, IModel<String> model) {
        super(id, model);

        this.body = new Label("body", model);
        add(this.body);

        commonInit();
    }

    private void commonInit() {
        setOutputMarkupId(true);

        header = new WebMarkupContainer("header");
        headerLabel = new Label("header-label", "");
        header.add(headerLabel);

        add(header);
        add(new CssClassNameAppender("modal", "hide"));
    }

    public Modal header(IModel<String> label) {
        headerLabel.setDefaultModel(label);
        return this;
    }

    public Modal setHeaderVisible(boolean visible) {
        header.setVisible(visible);
        return this;
    }

    public Modal showAfterInitialize(boolean showAfterInitialize) {
        this.showAfterInitialize = showAfterInitialize;
        return this;
    }

    public Modal addOpenerAttributesTo(Component component) {
        component.add(new AttributeModifier("data-toggle", "modal"));
        component.add(new AttributeModifier("href", "#" + getMarkupId(true)));
        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (fadein) {
            add(new CssClassNameAppender("fade"));
        }

        if (Strings.isEmpty(headerLabel.getDefaultModelObjectAsString())) {
            // there must be at least on character inside the header to prevent
            // layout problems.
            headerLabel.setDefaultModelObject("&nbsp;");
            headerLabel.setEscapeModelStrings(false);
        } else {
            headerLabel.setEscapeModelStrings(true);
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "div");
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript("$('#" + getMarkupId(true) + "').modal({keyboard:" + keyboard +
                                                       ", show:" + showAfterInitialize + "});"));
    }

    public Modal fadein(boolean fadein) {
        this.fadein = fadein;
        return this;
    }

    public Modal keyboard(boolean keyboard) {
        this.keyboard = keyboard;
        return this;
    }
}
