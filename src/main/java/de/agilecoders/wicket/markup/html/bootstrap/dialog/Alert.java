package de.agilecoders.wicket.markup.html.bootstrap.dialog;

import com.google.common.base.Strings;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Alert extends Panel {

    /**
     * The {@code Type} enum defines all possible alert types.
     */
    public enum Type implements CssClassNameProvider {
        Error, Success, Info, Warning;

        @Override
        public String cssClassName() {
            return equals(Warning) ? "alert-block" : "alert-" + name().toLowerCase();
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(cssClassName());
        }

    }

    private WebMarkupContainer closeButton;
    private Label message;
    private Label blockHeader;
    private Label inlineHeader;

    private Type type;
    private boolean useInlineHeader = true;

    /**
     * Constructor.
     *
     * @param id      the wicket component id.
     * @param message the alert message
     */
    public Alert(String id, IModel<String> message) {
        this(id, message, Model.of(""));
    }

    /**
     * Constructor.
     *
     * @param id      the wicket component id.
     * @param message the alert message
     * @param header  the title of the alert message
     */
    public Alert(String id, IModel<String> message, IModel<String> header) {
        super(id, message);

        type = Type.Info;

        this.inlineHeader = new Label("inline", header);
        this.blockHeader = new Label("block", header);
        this.message = new Label("message", getDefaultModel());
        this.closeButton = new WebMarkupContainer("close");

        add(this.inlineHeader, this.blockHeader, this.message, this.closeButton);

        add(new BootstrapBaseBehavior(),
            new AssertTagNameBehavior("div"),
            new CssClassNameAppender("alert"));
    }

    /**
     * Sets whether the close button is visible.
     *
     * @param visible True if the close button is visible.
     * @return This
     */
    public Alert setCloseButtonVisible(boolean visible) {
        this.closeButton.setVisible(visible);
        return this;
    }

    /**
     * Sets the alert type.
     *
     * @param type to use.
     * @return This.
     */
    public Alert type(Type type) {
        this.type = type;
        return this;
    }

    /**
     * Whether to use an inline or block header.
     *
     * @param useInlineHeader true to use inline header
     * @return This
     */
    public Alert useInlineHeader(boolean useInlineHeader) {
        this.useInlineHeader = useInlineHeader;
        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (Strings.isNullOrEmpty(inlineHeader.getDefaultModelObjectAsString())) {
            this.inlineHeader.setVisible(false);
            this.blockHeader.setVisible(false);
        } else {
            this.inlineHeader.setVisible(useInlineHeader);
            this.blockHeader.setVisible(!useInlineHeader);
        }

        this.message.setDefaultModel(getDefaultModel());

        if (closeButton.isVisible()) {
            add(new BootstrapJavascriptBehavior());
        }

        add(type.newCssClassNameAppender());
    }
}
