package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
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

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.jquery.util.Strings2;

/**
 * The {@code Modal} dialog is a simple component with header,
 * footer and body.
 *
 * @author miha
 */
public class Modal<T> extends GenericPanel<T> {
    private static final long serialVersionUID = 1L;
    public static final String BUTTON_MARKUP_ID = "button";

    /**
     * @see <a href="https://getbootstrap.com/docs/5.3/components/modal/#optional-sizes">Optional sizes</a>
     */
    public enum Size implements ICssClassNameProvider {
        Small("sm"),
        Default(""),
        Large("lg"),
        Extra_large("xl");

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

    /**
     * @see <a href="https://getbootstrap.com/docs/5.3/components/modal/#fullscreen-modal">Fullscreen Modal</a>
     */
    public enum Fullscreen implements ICssClassNameProvider {
    	None(""),
    	Always("fullscreen"),
    	Sm_down("fullscreen-sm-down"),
    	Md_down("fullscreen-md-down"),
    	Lg_down("fullscreen-lg-down"),
    	Xl_down("fullscreen-xl-down"),
    	Xxl_down("fullscreen-xxl-down");


    	private final String cssClassName;

    	/**
    	 * Construct.
    	 *
    	 * @param cssClassName the css class name of button type
    	 */
    	Fullscreen(final String cssClassName) {
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
    private final List<Component> buttons = new ArrayList<>();
    private MarkupContainer footer;
    private MarkupContainer header;
    private AjaxEventBehavior closeBehavior;

    private Size size = Size.Default;
    private Fullscreen fullscreen = Fullscreen.None;

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
    }

    private Component getHeaderLabel() {
        if (headerLabel == null) {
            headerLabel = createHeaderLabel("header-label", "");
        }
        return headerLabel;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        header.add(getHeaderLabel());
        headerLabel.setOutputMarkupId(true);
        Component headerCloseButton = createHeaderCloseButton("header-close-button");
        header.add(headerCloseButton);
        headerCloseButton.setOutputMarkupId(true);

        footer.add(new ListView<>("buttons", buttons) {
            private static final long serialVersionUID = 1L;

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
     * Factory method for the Modal's header 'closebutton'
     * By default it generates the markup for original Bootstrap Modal closebutton
     *
     * @param id The component id
     * @return the close button
     */
    protected Component createHeaderCloseButton(String id) {
        return new WebMarkupContainer(id);
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
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Set<Size> sizes = Arrays.stream(Size.values()).collect(Collectors.toSet());
                sizes.remove(Size.Default);
                sizes.forEach(s -> Attributes.removeClass(tag, s));
                if(sizes.contains(size))
                	Attributes.addClass(tag, size);

                Set<Fullscreen> fulscreens = Arrays.stream(Fullscreen.values()).collect(Collectors.toSet());
                fulscreens.remove(Fullscreen.None);
                fulscreens.forEach(s -> Attributes.removeClass(tag, s));
                if(fulscreens.contains(fullscreen))
                	Attributes.addClass(tag, fullscreen);
            }
        };
    }

    /**
     * Sets the size of the modal.
     *
     * @param size The size of the modal dialog.
     * @return {@code this}, for method chaining
     */
    public Modal<T> size(Size size) {
        this.size = size;
        return this;
    }

    /**
     * Sets fullscreen modal.
     *
     * @param fullscreen Fullscreen mode of the modal dialog.
     * @return {@code this}, for method chaining
     */
    public Modal<T> fullscreen(Fullscreen fullscreen) {
    	this.fullscreen = fullscreen;
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

        Attributes.set(tag, "data-bs-keyboard", "" + closeOnEscapeKey);
        Attributes.set(tag, "data-bs-focus", "" + !disableEnforceFocus.getObject());

        if (backdrop != Backdrop.TRUE) {
            Attributes.set(tag, "data-bs-backdrop", backdrop.name().toLowerCase() );
        }

        // ARIA
        Attributes.set(tag, "role", "dialog");
        Attributes.set(tag, "aria-labelledby", getHeaderLabel().getMarkupId());
        Attributes.set(tag, "aria-hidden", "true");
    }

    /**
     * hook to react on modal close event.
     *
     * @param target The current {@link IPartialPageRequestHandler}
     */
    protected void onClose(final IPartialPageRequestHandler target) {}

    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public Modal<T> header(IModel<String> label) {
        getHeaderLabel().setDefaultModel(label);
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
        getHeaderLabel().setDefaultModel(label)
                .setEscapeModelStrings(escapeMarkup);
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
     * @param target The Ajax request handler
     * @return This instance, for chaining
     */
    public Modal<T> appendCloseDialogJavaScript(final IPartialPageRequestHandler target) {
        target.prependJavaScript(createActionScript(getMarkupId(true), "hide"));
        return this;
    }

    /**
     * A short alias for {@link Modal#appendCloseDialogJavaScript}
     *
     * @param target The Ajax  request handler
     * @return This instance, for chaining
     */
    public Modal<T> close(final IPartialPageRequestHandler target) {
        return appendCloseDialogJavaScript(target);
    }

    /**
     * Append dialog show JavaScript to current request AJAX target.
     *
     * @param target current ajax request target
     * @return this instance for chaining
     */
    public Modal<T> appendShowDialogJavaScript(final IPartialPageRequestHandler target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), "show"));

        return this;
    }

    /**
     * A short alias for {@link Modal#appendShowDialogJavaScript}
     * @param target The Ajax request handler
     * @return This instance, for chaining
     */
    public Modal<T> show(final IPartialPageRequestHandler target) {
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
        return "bootstrap.Modal.getInstance(document.getElementById('" + Strings2.escapeMarkupId(markupId) + "'))." + action + "();";
    }

    public Modal<T> addOpenerAttributesTo(final Component component) {
        component.add(new AttributeModifier("data-bs-toggle", "modal"));
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

//        if (button instanceof ModalCloseButton) {
//            ((ModalCloseButton) button).setAnchor(this);
//        }

        buttons.add(button);
        return this;
    }

    /**
     * handles the close event.
     *
     * @param target The current {@link IPartialPageRequestHandler}
     */
    private void handleCloseEvent(final IPartialPageRequestHandler target) {
        if (isVisible()) {
            onClose(target);

            appendCloseDialogJavaScript(target);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (Strings.isEmpty(getHeaderLabel().getDefaultModelObjectAsString())) {
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

        response.render(OnDomReadyHeaderItem.forScript(createBasicInitializerScript(getMarkupId(true))));
    }

    /**
     * creates the basic initialization script of the modal dialog.
     * Override this to pass in your custom initialization, add event handlers, etc.
     *
     * @param markupId markup id
     * @return initializer script
     */
    protected String createBasicInitializerScript(final String markupId) {
        return "new bootstrap.Modal(document.getElementById('" + markupId + "'))" // options are added as data-attributes
                + (showImmediately() ? ".show()" : "")
                + ";";
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
     * @return This instance, for chaining
     */
    public final Modal<T> setDisableEnforceFocus(boolean disable) {
        this.disableEnforceFocus.setObject(disable);
        return this;
    }

    private class ModalCloseBehavior extends AjaxEventBehavior {
        private static final long serialVersionUID = 1L;

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
