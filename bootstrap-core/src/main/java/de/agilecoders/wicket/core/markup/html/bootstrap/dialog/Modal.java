package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
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
public class Modal<T> extends GenericPanel<T> {

    public static final String BUTTON_MARKUP_ID = "button";

    /**
     * @see <a href="http://getbootstrap.com/javascript/#modals-sizes">Modal Sizes</a>
     */
    public enum Size implements ICssClassNameProvider {
        Default(""),
        Small("sm"),
        Medium("md"),
        Large("lg");

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of button type
         */
        Size(final String cssClassName) {
            this.cssClassName = cssClassName;
        }

        /**
         * @return css class name of button type
         */
        @Override
        public String cssClassName() {
            return "modal-" + cssClassName;
        }
    }

    public enum Backdrop {
        TRUE, FALSE, STATIC
    }

    private boolean show = false;
    private boolean fadein = true;
    private boolean keyboard = true;
    private boolean closeOnEscapeKey = true;
    private Backdrop backdrop = Backdrop.TRUE;

    /**
     * Disables the enforcement of the focus.
     * Needed in cases when a component inside the modal requires
     * the focus as well.
     * See <a href="https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/381">#381</a>
     */
    private final IModel<Boolean> disableEnforceFocus = Model.of(false);

    private Component headerLabel;
    private final List<Component> buttons = new ArrayList<Component>();
    private MarkupContainer footer;
    private MarkupContainer header;
    private AjaxEventBehavior closeBehavior;

    private Size size = Size.Default;

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
    public Modal(String id, IModel<T> model) {
        super(id, model);

        setOutputMarkupPlaceholderTag(true);

        footer = createFooter("footer");
        header = createHeader("header");
        header.add(headerLabel = createHeaderLabel("header-label", ""));
        headerLabel.setOutputMarkupId(true);

        footer.add(new ListView<Component>("buttons", buttons) {
            @Override
            protected void populateItem(ListItem<Component> item) {
                item.add(item.getModelObject());
            }
        });

        WebMarkupContainer dialog = createDialog("dialog");
        add(dialog);
        dialog.add(header, footer);

        BootstrapResourcesBehavior.addTo(this);
    }

    /**
     * Factory method for the Modal's header label
     *
     * @param id The component id
     * @return The header label
     */
    protected Component createHeaderLabel(String id, String label) {
        return new Label(id, label);
    }

    /**
     * Factory method for the Modal's header
     *
     * @param id The component id
     * @return The header container
     */
    protected MarkupContainer createHeader(String id) {
        return new WebMarkupContainer(id);
    }

    /**
     * Factory method for the Modal's footer
     *
     * @param id The component id
     * @return The footer container
     */
    protected MarkupContainer createFooter(String id) {
        return new WebMarkupContainer(id);
    }

    /**
     * Creates a container for the 'modal-dialog' div.
     * It is used to set the size of the modal.
     *
     * @param id The component id
     * @return The dialog container
     */
    protected WebMarkupContainer createDialog(String id) {
        return new TransparentWebMarkupContainer(id) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Attributes.removeClass(tag, Size.Large.cssClassName(), Size.Small.cssClassName());

                switch (size) {
                    case Large:
                        Attributes.addClass(tag, Size.Large.cssClassName());
                        break;
                    case Small:
                        Attributes.addClass(tag, Size.Small.cssClassName());
                        break;
                    default:
                        // do nothing. the CSS classes are removed before the switch
                }
            }
        };
    }

    /**
     * Sets the size of the modal.
     *
     * @param size The size of the modal dialog.
     * @return {@code this}, for method chaining
     */
    public Modal size(Size size) {
        this.size = size;
        return this;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "modal");

        if (useFadein()) {
            Attributes.addClass(tag, "fade");
        } else {
            Attributes.removeClass(tag, "fade");
        }

        if (closeOnEscapeKey) {
            Attributes.set(tag, "tabindex", "-1");
        }

        if (backdrop != Backdrop.TRUE) {
            Attributes.set(tag, "data-backdrop", backdrop.name().toLowerCase() );
        }

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
    public Modal<T> header(IModel<String> label) {
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
    public Modal<T> header(final IModel<String> label, final boolean escapeMarkup) {
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
    public Modal<T> setFooterVisible(final boolean visible) {
        footer.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the header and any children are visible.
     *
     * @param visible True if header and any children should be visible
     * @return This
     */
    public Modal<T> setHeaderVisible(final boolean visible) {
        header.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the close handler is used or not. Default is false.
     *
     * @param useCloseHandler True if close handler should be used
     * @return This
     */
    public final Modal<T> setUseCloseHandler(final boolean useCloseHandler) {
        if (useCloseHandler) {
            if (closeBehavior == null) {
                closeBehavior = new ModalCloseBehavior();
                add(closeBehavior);
            }
        } else if (closeBehavior != null) {
            remove(closeBehavior);
            closeBehavior = null;
        }
        return this;
    }

    /**
     * Sets the initial visibility of the modal dialog.
     *
     * @param show Whether to show the dialog or not
     * @return This
     */
    public Modal<T> show(boolean show) {
        this.show = show;
        return this;
    }

    /**
     * Sets a flag that decides whether using the <em>ESC</em> keyboard
     * key will close this modal
     *
     * @param close The flag that is used to decide whether to close on <em>ESC</em> or not
     * @return This
     */
    public Modal<T> setCloseOnEscapeKey(boolean close) {
        this.closeOnEscapeKey = close;
        return this;
    }

    /**
     * Sets a flag that decides whether using the <em>ESC</em> keyboard
     * key will close this modal
     *
     * @param backdrop Includes a modal-backdrop element.
     *                 Alternatively, specify static for a backdrop which doesn't close the modal on click.
     * @return This
     */
    public Modal<T> setBackdrop(Backdrop backdrop) {
        this.backdrop = backdrop;
        return this;
    }

    /**
     * Append dialog close/hide JavaScript to current AJAX request target.
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    public Modal<T> appendCloseDialogJavaScript(final AjaxRequestTarget target) {
        target.prependJavaScript(createActionScript(getMarkupId(true), "hide"));
        return this;
    }

    /**
     * A short alias for {@link Modal#appendCloseDialogJavaScript}
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    public Modal<T> close(final AjaxRequestTarget target) {
        return appendCloseDialogJavaScript(target);
    }

    /**
     * Append dialog show JavaScript to current request AJAX target.
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    public Modal<T> appendShowDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "show"));
        appendDisableEnforceFocus(target);

        return this;
    }

    /**
     * Appends JavaScript snippet that disables the modal's enforceFocus functionality
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    protected Modal<T> appendDisableEnforceFocus(AjaxRequestTarget target) {
        if (disableEnforceFocus.getObject()) {
            target.appendJavaScript("$.fn.modal.Constructor.prototype.enforceFocus = function () {};");
        }
        return this;
    }

    /**
     * A short alias for {@link Modal#appendShowDialogJavaScript}
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    public Modal<T> show(final AjaxRequestTarget target) {
        return appendShowDialogJavaScript(target);
    }

    /**
     * creates an action script to open/close the dialog on client side.
     *
     * @param markupId The component's markup id
     * @param action   Possible values: show/hide
     * @return new script.
     */
    protected String createActionScript(final String markupId, final String action) {
        return "$('#" + Strings2.escapeMarkupId(markupId) + "').modal('" + action + "');";
    }

    public Modal<T> addOpenerAttributesTo(final Component component) {
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
    public Modal<T> addCloseButton(final IModel<String> label) {
        ModalCloseButton button = new ModalCloseButton(label);
        button.setAnchor(this);

        return addButton(button);
    }

    /**
     * adds a close button with default label ("Close")
     *
     * @return this instance
     */
    public Modal<T> addCloseButton() {
        return addCloseButton(Model.of("Close"));
    }

    /**
     * adds a button to footer section.
     *
     * @param button Button to add to footer
     * @return this instance.
     */
    public Modal<T> addButton(final Component button) {
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
        if (closeBehavior != null) {
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
        return fadein;
    }

    /**
     * @return true, if keyboard usage is activated
     */
    protected final boolean useKeyboard() {
        return keyboard;
    }

    /**
     * @return true, if modal dialog should be shown after initialization
     */
    protected final boolean showImmediately() {
        return show;
    }

    /**
     * Whether to fadein/fadeout the modal dialog or not
     *
     * @param fadein true, if dialog should be animated
     * @return This
     */
    public final Modal<T> setFadeIn(boolean fadein) {
        this.fadein = fadein;
        return this;
    }

    /**
     * Whether to enable keyboard interaction like ESC to close the dialog.
     *
     * @param keyboard true, if keyboard interaction is enabled
     * @return This
     */
    public final Modal<T> setUseKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    /**
     * Whether the modal should not enforce the focus.
     *
     * @param disable true, if the modal should not enforce the focus
     * @return This
     */
    public final Modal<T> setDisableEnforceFocus(boolean disable) {
        this.disableEnforceFocus.setObject(disable);
        return this;
    }

    private class ModalCloseBehavior extends AjaxEventBehavior {

        /**
         * Constructor.
         */
        public ModalCloseBehavior() {
            super("hidden.bs.modal");
        }

        @Override
        protected void onEvent(final AjaxRequestTarget target) {
            handleCloseEvent(target);
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);

            attributes.setEventPropagation(AjaxRequestAttributes.EventPropagation.BUBBLE);
        }
    }

}
