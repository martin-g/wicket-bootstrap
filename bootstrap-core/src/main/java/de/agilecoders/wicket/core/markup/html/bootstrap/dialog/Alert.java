package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import java.time.Duration;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * A bootstrap styled panel for success, warning, and error messages
 *
 * usage:
 * <pre>
 *     add(new Alert("id", Model.of("message text")));
 * </pre>
 *
 * @author miha
 */
public class Alert extends GenericPanel<String> {
    private static final long serialVersionUID = 1L;

    /**
     * The {@code Type} enum defines all possible alert types.
     */
    public enum Type implements ICssClassNameProvider {
        Danger, Success, Info, Warning;

        @Override
        public String cssClassName() {
            return "alert-" + name().toLowerCase();
        }

        public static Type from(String level) {
            if (level.equalsIgnoreCase("ERROR") || level.equalsIgnoreCase("FATAL")) {
                return Danger;
            } else if (level.equalsIgnoreCase("WARNING")) {
                return Warning;
            } else if (level.equalsIgnoreCase("SUCCESS")) {
                return Success;
            } else {
                return Info;
            }
        }

    }

    private final IModel<String> header;
    private WebMarkupContainer closeButton;
    private Component message;
    private Label blockHeader;
    private Label inlineHeader;
    private Type type;
    private boolean dismissable;
    private Duration duration;
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
        dismissable = false;
        this.header = header;
    }

    private Label getInlineHeader() {
        if (inlineHeader == null) {
            inlineHeader = new Label("inline", header);
        }
        return inlineHeader;
    }

    private Label getBlockHeader() {
        if (blockHeader == null) {
            blockHeader = new Label("block", header);
        }
        return blockHeader;
    }

    protected final Component getMessage() {
        if (message == null) {
            message = createMessage("message", getModel());
        }
        return message;
    }

    private WebMarkupContainer getCloseButton() {
        if (closeButton == null) {
            closeButton = new WebMarkupContainer("close") {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onConfigure() {
                    super.onConfigure();
                    setVisible(dismissable);
                }
            };
        }
        return closeButton;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(getInlineHeader(), getBlockHeader(), getMessage(), getCloseButton());

        add(BootstrapResourcesBehavior.instance());
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "alert");
        Attributes.addClass(tag, type);

        if (dismissable) {
            Attributes.addClass(tag, "alert-dismissible");
        } else {
            Attributes.removeClass(tag, "alert-dismissible");
        }
    }

    /**
     * creates a new message component.
     *
     * @param markupId The component id
     * @param message  The message as {@link IModel}
     * @return new message component
     */
    protected Component createMessage(final String markupId, final IModel<String> message) {
        return new Label(markupId, message);
    }

    /**
     * Sets whether the close button is visible.
     *
     * @param visible True if the close button is visible.
     * @return This
     */
    public Alert setCloseButtonVisible(boolean visible) {
        this.dismissable = visible;
        getCloseButton().setVisible(visible);
        return this;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (duration != null && duration.toSeconds() > 0) {
            response.render(OnDomReadyHeaderItem.forScript("window.setTimeout(function(){"
                    + "bootstrap.Alert.getOrCreateInstance('#" + getMarkupId() + "').close();}," + duration.toMillis() + ");"));
        }
    }

    /**
     * Sets the message.
     *
     * @param message The message.
     * @return This instance for chaining.
     */
    public Alert withMessage(final IModel<String> message) {
        setDefaultModel(message);

        return this;
    }

    /**
     * Sets the header message.
     *
     * @param header The header message.
     * @return This instance for chaining.
     */
    public Alert withHeader(IModel<String> header) {
        getBlockHeader().setDefaultModel(header);
        getInlineHeader().setDefaultModel(header);

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
     * hides the alert box after given duration.
     *
     * @param duration the duration to use for closing the alert box
     * @return this.
     */
    public Alert hideAfter(Duration duration) {
        this.duration = duration;
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

        if (Strings.isEmpty(getInlineHeader().getDefaultModelObjectAsString())) {
            getInlineHeader().setVisible(false);
            getBlockHeader().setVisible(false);
        } else {
            getInlineHeader().setVisible(useInlineHeader);
            getBlockHeader().setVisible(!useInlineHeader);
        }

        getMessage().setDefaultModel(getDefaultModel());
    }

    @Override
    protected void detachModel() {
        super.detachModel();
        header.detach();
    }
}
