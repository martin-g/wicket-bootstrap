package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.util.Attributes;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Modal} dialog is a simple component with header,
 * footer and body.
 *
 * @author miha
 */
public class Modal extends Panel {

    public static final String BUTTON_MARKUP_ID = "button";

    private final WebMarkupContainer header;
    private final IModel<Boolean> show = Model.of(false);
    private final IModel<Boolean> fadein = Model.of(true);
    private final IModel<Boolean> keyboard = Model.of(true);
    private final Label headerLabel;
    private final List<Component> buttons = new ArrayList<Component>();
    private final WebMarkupContainer footer;
    private final IModel<Boolean> useCloseHandler = Model.of(false);
    private final AjaxEventBehavior closeBehavior;

    /**
     * Constructor.
     *
     * @param markupId The non-null id of this component
     */
    public Modal(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param id    The non-null id of this component
     * @param model The component's body model
     */
    public Modal(String id, IModel<String> model) {
        super(id, model);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        footer = new WebMarkupContainer("footer");
        header = new WebMarkupContainer("header");
        header.add(headerLabel = new Label("header-label", ""));
        headerLabel.setOutputMarkupId(true);

        footer.add(new ListView<Component>("buttons", buttons) {
            @Override
            protected void populateItem(ListItem<Component> item) {
                item.add(item.getModelObject());
            }
        });

        closeBehavior = new AjaxEventBehavior("hidden") {
            @Override
            protected void onEvent(final AjaxRequestTarget target) {
                handleCloseEvent(target);
            }
        };

        add(header, footer);

        BootstrapResourcesBehavior.addTo(this);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "modal");
        Attributes.set(tag, "tabindex", "-1");

        // ARIA
        Attributes.set(tag, "role", "dialog");
        Attributes.set(tag, "aria-labelledby", headerLabel.getMarkupId());
        Attributes.set(tag, "aria-hidden", "true");
    }

    /**
     * hook to react on modal close event. The {@link }
     *
     * @param target The current {@link AjaxRequestTarget}
     */
    protected void onClose(final AjaxRequestTarget target) {}

    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public Modal header(IModel<String> label) {
        headerLabel.setDefaultModel(label);
        setHeaderVisible(true);
        return this;
    }

    /**
     * Sets the header label text and whether model strings should be escaped.
     *
     * @param label        The header label
     * @param escapeMarkup True is model strings should be escaped
     * @return This
     */
    public Modal header(final IModel<String> label, final boolean escapeMarkup) {
        headerLabel.setDefaultModel(label);
        headerLabel.setEscapeModelStrings(escapeMarkup);
        return this;
    }

    /**
     * Sets whether the footer and any children are visible.
     *
     * @param visible True if footer and any children should be visible
     * @return This
     */
    public Modal setFooterVisible(final boolean visible) {
        footer.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the header and any children are visible.
     *
     * @param visible True if header and any children should be visible
     * @return This
     */
    public Modal setHeaderVisible(final boolean visible) {
        header.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the close handler is used or not. Default is false.
     *
     * @param useCloseHandler True if close handler should be used
     * @return This
     */
    public final Modal setUseCloseHandler(final boolean useCloseHandler) {
        this.useCloseHandler.setObject(useCloseHandler);
        return this;
    }

    /**
     * Sets the initial visibility of the modal dialog.
     *
     * @param show Whether to show the dialog or not
     * @return This
     */
    public Modal show(boolean show) {
        this.show.setObject(show);
        return this;
    }

    public Modal appendCloseDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "hide"));
        return this;
    }

    public Modal appendShowDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "show"));
        return this;
    }

    /**
     * creates an action script to open/close the dialog on client side.
     *
     * @param markupId The component's markup id
     * @param action   Possible values: show/hide
     * @return new script.
     */
    protected String createActionScript(final String markupId, final String action) {
        return "$('#" + markupId + "').modal('" + action + "');";
    }

    public Modal addOpenerAttributesTo(final Component component) {
        component.add(new AttributeModifier("data-toggle", "modal"));
        component.add(new AttributeModifier("href", "#" + getMarkupId(true)));
        return this;
    }

    /**
     * adds a close button with specific label
     *
     * @param label The label of close button
     * @return this instance
     */
    public Modal addCloseButton(final IModel<String> label) {
        ModalCloseButton button = new ModalCloseButton(label);
        button.setAnchor(this);

        return addButton(button);
    }

    /**
     * adds a close button with default label ("Close")
     *
     * @return this instance
     */
    public Modal addCloseButton() {
        return addCloseButton(Model.of("Close"));
    }

    /**
     * adds a button to footer section.
     *
     * @param button Button to add to footer
     * @return this instance.
     */
    public Modal addButton(final Component button) {
        if (!BUTTON_MARKUP_ID.equals(button.getId())) {
            throw new IllegalArgumentException(
                    String.format("Invalid button markup id. Must be '%s'.", BUTTON_MARKUP_ID));
        }

        if (button instanceof ModalCloseButton) {
            // ((ModalCloseButton) button).setAnchor(this);
        }

        buttons.add(button);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (useCloseHandler.getObject()) {
            add(closeBehavior);
        }
    }

    /**
     * handles the close event.
     *
     * @param target The current {@link AjaxRequestTarget}
     */
    private void handleCloseEvent(final AjaxRequestTarget target) {
        if (isVisible()) {
            onClose(target);

            appendCloseDialogJavaScript(target);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (useFadein()) {
            add(new CssClassNameAppender("fade"));
        }

        if (Strings.isEmpty(headerLabel.getDefaultModelObjectAsString())) {
            // there must be at least on character inside the header to prevent
            // layout problems.
            headerLabel.setDefaultModelObject("&nbsp;");
            headerLabel.setEscapeModelStrings(false);
        }

        footer.setVisible(buttons.size() > 0);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript(createInitializerScript(getMarkupId(true))));
    }

    /**
     * creates the initializer script of the modal dialog.
     *
     * @param markupId The component's markup id
     * @return initializer script
     */
    private String createInitializerScript(final String markupId) {
        return addCloseHandlerScript(markupId, createBasicInitializerScript(markupId));
    }

    /**
     * creates the basic initialization script of the modal dialog.
     * Override this to pass in your custom initialization, add event handlers, etc.
     *
     * @param markupId markup id
     * @return initializer script
     * @see #createInitializerScript
     */
    protected String createBasicInitializerScript(final String markupId) {
        return "$('#" + markupId + "').modal({keyboard:" + useKeyboard() + ", show:" + showImmediately() + "})";
    }

    /**
     * adds close handler to initializer script, if use of close handler has been defined.
     *
     * @param markupId markup id
     * @param script   base script to prepend
     * @return close handler script
     */
    private String addCloseHandlerScript(final String markupId, final String script) {
        if (useCloseHandler.getObject()) {
            return script + ";$('#" + markupId + "').on('hidden', function () { "
                   + "  Wicket.Ajax.ajax({'u':'" + closeBehavior.getCallbackUrl() + "','c':'" + markupId + "'});"
                   + "})";
        }

        return script;
    }

    /**
     * @return true, if fade in animation is activated
     */
    protected final boolean useFadein() {
        return fadein.getObject();
    }

    /**
     * @return true, if keyboard usage is activated
     */
    protected final boolean useKeyboard() {
        return keyboard.getObject();
    }

    /**
     * @return true, if modal dialog should be shown after initialization
     */
    protected final boolean showImmediately() {
        return show.getObject();
    }

    /**
     * Whether to fadin/fadeout the modal dialog or not
     *
     * @param fadein true, if dialog should be animated
     * @return This
     */
    public final Modal setFadeIn(boolean fadein) {
        this.fadein.setObject(fadein);
        return this;
    }

    /**
     * Whether to enable keyboard interaction like ESC to close the dialog.
     *
     * @param keyboard true, if keyboard interaction is enabled
     * @return This
     */
    public final Modal setUseKeyboard(boolean keyboard) {
        this.keyboard.setObject(keyboard);
        return this;
    }

}
