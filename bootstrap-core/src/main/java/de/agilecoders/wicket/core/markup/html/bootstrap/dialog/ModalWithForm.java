package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.resolver.IComponentResolver;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.jquery.JQuery;

/**
 * The {@code Modal} dialog is a simple component with header,
 * footer and body.
 *
 * @author miha
 */
public class ModalWithForm<T> extends GenericPanel<T> implements IModal {

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

    private final Form<?> form;
    
    /**
     * Transparent form.
     * 
     * @author reiern70
     */
    private static class TranspanentForm extends Form<Void> implements IComponentResolver {

		public TranspanentForm(String id) {
			super(id);
		}

		@Override
		public Component resolve(MarkupContainer container,
				MarkupStream markupStream, ComponentTag tag) {
			Component resolvedComponent = getParent().get(tag.getId());
			if (resolvedComponent != null && getPage().wasRendered(resolvedComponent))
			{
				/*
				 * Means that parent container has an associated homonymous tag to this grandchildren
				 * tag in markup. The parent container wants render it and it should be not resolved to
				 * their grandchildren.
				 */
				return null;
			}
			return resolvedComponent;
		}
    	
    }
    
    /**
     * Constructor.
     *
     * @param markupId The non-null id of this component
     */
    public ModalWithForm(final String markupId) {
        this(markupId, null);
    }

    /**
     * Constructor.
     *
     * @param id    The non-null id of this component
     * @param model The component's body model
     */
    public ModalWithForm(String id, IModel<T> model) {
        super(id, model);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        form = new TranspanentForm("form");
        
        add(form);
        
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

        closeBehavior = new AjaxEventBehavior("hidden.bs.modal") {
            @Override
            protected void onEvent(final AjaxRequestTarget target) {
                handleCloseEvent(target);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                attributes.setEventPropagation(AjaxRequestAttributes.EventPropagation.BUBBLE);
            }
        };

        form.add(footer);
        
        add(header);

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
     * Adds a className to the form.
     * 
     * @param className The class name to add
     * @return This
     */
    public ModalWithForm<T> addFormCssClass(String className) {
        return addFormCssClass(Model.of(className));
    }
    
    /**
     * Adds a className to the form.
     * 
     * @param className The class name to add
     * @return This
     */
    public ModalWithForm<T> addFormCssClass(IModel<String> className) {
        form.add(new CssClassNameAppender(className));
        return this;
    }
    
    /**
     * Sets the header label text.
     *
     * @param label The header label
     * @return This
     */
    public ModalWithForm<T> header(IModel<String> label) {
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
    public ModalWithForm<T> header(final IModel<String> label, final boolean escapeMarkup) {
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
    public ModalWithForm<T> setFooterVisible(final boolean visible) {
        footer.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the header and any children are visible.
     *
     * @param visible True if header and any children should be visible
     * @return This
     */
    public ModalWithForm<T> setHeaderVisible(final boolean visible) {
        header.setVisible(visible);
        return this;
    }

    /**
     * Sets whether the close handler is used or not. Default is false.
     *
     * @param useCloseHandler True if close handler should be used
     * @return This
     */
    public final ModalWithForm<T> setUseCloseHandler(final boolean useCloseHandler) {
        this.useCloseHandler.setObject(useCloseHandler);
        return this;
    }

    /**
     * Sets the initial visibility of the modal dialog.
     *
     * @param show Whether to show the dialog or not
     * @return This
     */
    public ModalWithForm<T> show(boolean show) {
        this.show.setObject(show);
        return this;
    }

    /**
     * Append dialog close/hide JavaScript to current AJAX request target.
     * 
     * @param target
     * @return This
     */
    public ModalWithForm<T> appendCloseDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), ModalAction.Action.hide));
        return this;
    }

    /**
     * A short alias for {@link ModalWithForm#appendCloseDialogJavaScript}
     * @param target
     * @return
     */
    public ModalWithForm<T> close(final AjaxRequestTarget target) {
        return appendCloseDialogJavaScript(target);
    }
    
    /**
     * Append dialog show JavaScript to current request AJAX target.
     * 
     * @param target
     * @return This
     */
    public ModalWithForm<T> appendShowDialogJavaScript(final AjaxRequestTarget target) {
        target.appendJavaScript(createActionScript(getMarkupId(true), ModalAction.Action.show));
        return this;
    }
    
    /**
     * A short alias for {@link ModalWithForm#appendShowDialogJavaScript}
     * @param target
     * @return
     */
    public ModalWithForm<T> show(final AjaxRequestTarget target) {
        return appendShowDialogJavaScript(target);
    }

    /**
     * creates an action script to open/close the dialog on client side.
     *
     * @param markupId The component's markup id
     * @param action   Possible values: show/hide
     * @return new script.
     */
    protected String createActionScript(final String markupId, final ModalAction.Action action) {
        return JQuery.$("#"+markupId).chain(ModalAction.action(action)).get();
    }

    public ModalWithForm<T> addOpenerAttributesTo(final Component component) {
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
    public ModalWithForm<T> addCloseButton(final IModel<String> label) {
    	BootstrapModalCloseButton button = new BootstrapModalCloseButton(label);

        return addButton(button);
    }

    /**
     * adds a close button with default label ("Close")
     *
     * @return this instance
     */
    public ModalWithForm<T> addCloseButton() {
        return addCloseButton(Model.of("Close"));
    }

    /**
     * adds a button to footer section.
     *
     * @param button Button to add to footer
     * @return this instance.
     */
    public ModalWithForm<T> addButton(final Component button) {
        if (!BUTTON_MARKUP_ID.equals(button.getId())) {
            throw new IllegalArgumentException(
                    String.format("Invalid button markup id. Must be '%s'.", BUTTON_MARKUP_ID));
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
    public final ModalWithForm<T> setFadeIn(boolean fadein) {
        this.fadein.setObject(fadein);
        return this;
    }

    /**
     * Whether to enable keyboard interaction like ESC to close the dialog.
     *
     * @param keyboard true, if keyboard interaction is enabled
     * @return This
     */
    public final ModalWithForm<T> setUseKeyboard(boolean keyboard) {
        this.keyboard.setObject(keyboard);
        return this;
    }

	@Override
	public Component getModal() {
		return this;
	}

}
