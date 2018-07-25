package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static de.agilecoders.wicket.jquery.JQuery.$;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerLangJsReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.json.JSONFunction;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

/**
 * Abstract base class providing the common aspects of the different kinds of DateTextFields
 * which are based on different date types. This class applies composition over inheritance
 * applying the delegation pattern: It contains an instance of the original wicket date text field class
 * backing the converter.
 *
 * @param <T>
 *     the type of date ({@code java.util.Date} or {@code java.time.LocalDate})
 * @param <P>
 *     the type of the parent wicket {@code TextField} working with date of type {@code T}
 * @param <I>
 *     the input date type for the configuration options withBeginDate or withEndDate
 * @param <C>
 *     the configuration - the concrete implementation of the AbstractDateTextFieldConfig
 * @param <F>
 *     the concrete implementation type of this abstract class
 * @author miha
 * @author Urs Joss
 */
public abstract class AbstractDateTextField<T, P extends TextField<T> & AbstractTextComponent.ITextFormatProvider, I, C extends AbstractDateTextFieldConfig<C, I>, F extends AbstractDateTextField<T, P, I, C, F>>
    extends TextField<T> implements AbstractTextComponent.ITextFormatProvider {

    private static final String EVENT_PARAM = "datePickerEvent";
    private static final String DATE        = "date";

    private final P        converterDelegate;
    private final Class<T> dateTextFieldClass;

    private C config;

    /**
     * Super constructor that needs to be called from the implementing classes
     *
     * @param wicketTextField
     *     The parent non-bootstrap wicket implementation of the DateTextField.
     * @param implementingDateTextFieldClass
     *     the concrete type of the date text field class inheriting from this abstract base class
     * @param config
     *     the configuration
     */
    protected AbstractDateTextField(final P wicketTextField, final Class<T> implementingDateTextFieldClass,
        final C config) {
        super(wicketTextField.getId(), wicketTextField.getModel());
        this.setType(wicketTextField.getType());
        this.setEscapeModelStrings(false);
        this.converterDelegate = wicketTextField;
        this.dateTextFieldClass = implementingDateTextFieldClass;
        this.config = Args.notNull(config, "config");
    }

    /**
     * @return the configuration of the date text field
     */
    public C getConfig() {
        return config;
    }

    /**
     * sets the configuration in a fluent style
     *
     * @param config
     *     the configuration
     * @return this
     */
    public F with(C config) {
        if (config != null) {
            this.config = config;
        }
        return (F) this;
    }

    @Override
    public String getTextFormat() {
        return converterDelegate.getTextFormat();
    }

    @Override
    public <C> IConverter<C> getConverter(final Class<C> type) {
        return converterDelegate.getConverter(type);
    }

    /**
     * Enumeration for date picker client side events.
     */
    public enum Event {
        show,
        // Fired when the date picker is displayed.
        hide,
        // Fired when the date picker is hidden.
        clearDate,
        // Fired when the date is cleared, normally when the “clear” button (enabled with the clearBtn option) is pressed.
        changeDate,
        // Fired when the date is changed.
        changeMonth,
        // Fired when the view month is changed from year view.
        changeYear,
        // Fired when the view year is changed from decade view.
        changeDecade,
        // Fired when the view decade is changed from century view.
        changeCentury, // Fired when the view century is changed from millennium view.
    }

    /**
     * Abstract base class for events.
     */
    public abstract static class AbstractEventHandler implements Serializable {

        protected abstract CharSequence getBody();

        protected String getFunction() {
            StringBuilder sb = new StringBuilder("function(e) {\n");
            sb.append(getBody());
            sb.append("\n}");
            return sb.toString();
        }
    }

    /**
     * Parent ajax event handler
     */
    interface IParentAjaxEventHandler<T> extends Serializable {
        void onAjaxEvent(AjaxRequestTarget target, T date, Event event);
    }

    private abstract class AbstractAjaxEvent extends AbstractEventHandler {

        private final Event                   event;
        private final IParentAjaxEventHandler handler;
        private final boolean                 updateModel;

        public AbstractAjaxEvent(Event event, IParentAjaxEventHandler handler, boolean updateModel) {
            this.event = event;
            this.handler = handler;
            this.updateModel = updateModel;
        }

        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            attributes
                .getExtraParameters()
                .put(EVENT_PARAM, event.name());
            attributes
                .getExtraParameters()
                .put(DATE, new JSONFunction("e.format()"));
        }

        protected void onAjaxEvent(AjaxRequestTarget target) {
            String dateStr = RequestCycle
                .get()
                .getRequest()
                .getRequestParameters()
                .getParameterValue(DATE)
                .toString(null);
            T date = !Strings.isEmpty(dateStr) ?
                converterDelegate
                    .getConverter(dateTextFieldClass)
                    .convertToObject(dateStr, RequestCycle
                        .get()
                        .getRequest()
                        .getLocale()) :
                null;
            if (updateModel) {
                setDefaultModelObject(date);
            }
            handler.onAjaxEvent(target, date, event);
        }
    }

    private class DatePickerAbstractDefaultAjaxBehavior extends AbstractDefaultAjaxBehavior {

        private final AbstractAjaxEvent abstractAjaxEvent;

        public DatePickerAbstractDefaultAjaxBehavior(Event event, IParentAjaxEventHandler handler,
            boolean updateModel) {
            Args.notNull(event, "event");
            Args.notNull(handler, "handler");
            abstractAjaxEvent = createNew(event, handler, updateModel);
        }

        protected AbstractAjaxEvent createNew(Event event, IParentAjaxEventHandler handler, boolean updateModel) {
            return new AbstractAjaxEvent(event, handler, updateModel) {
                @Override
                protected CharSequence getBody() {
                    return AbstractDateTextField.DatePickerAbstractDefaultAjaxBehavior.this
                        .getCallbackScript()
                        .toString();
                }
            };
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            abstractAjaxEvent.updateAjaxAttributes(attributes);
        }

        @Override
        protected void respond(AjaxRequestTarget target) {
            abstractAjaxEvent.onAjaxEvent(target);
        }
    }

    private final Map<Event, AbstractEventHandler> eventMap = new HashMap<Event, AbstractEventHandler>();

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        checkComponentTag(tag, "input");
        Attributes.set(tag, "type", "text");

        super.onComponentTag(tag);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(BootstrapDatepickerReference.INSTANCE));

        if (!getConfig().isDefaultLanguageSet()) {
            response.render(
                JavaScriptHeaderItem.forReference(new BootstrapDatepickerLangJsReference(getConfig().getLanguage())));
        } else {
            response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        }

        response.render(OnDomReadyHeaderItem.forScript(createScript(getConfig())));
    }

    /**
     * creates the initializer script.
     *
     * @return initializer script
     */
    protected CharSequence createScript(final C config) {
        CharSequence script = $(this)
            .chain("datepicker", config)
            .get();
        if (eventMap.isEmpty()) {
            return script;
        }
        // remove trailing ;
        StringBuilder sb = new StringBuilder(script.subSequence(0, script.length() - 1));
        for (Event type : eventMap.keySet()) {
            sb
                .append(".on('")
                .append(type)
                .append("',")
                .append(eventMap
                    .get(type)
                    .getFunction())
                .append(')');
        }
        sb.append(';');
        return sb;
    }

    /**
     * Allows to register/add an event handler.
     *
     * @param type
     *     The event type
     * @param handler
     *     he event type
     * @return this
     */
    public F addEvent(Event type, AbstractEventHandler handler) {
        eventMap.put(type, handler);
        return (F) this;
    }

    /**
     * Adds and ajax based event. Model object is not updated.
     *
     * @param type
     *     the event type
     * @param evenHandler
     *     The event handler
     */
    public F addAjaxEvent(Event type, IParentAjaxEventHandler evenHandler) {
        addAjaxEvent(type, evenHandler, false);
        return (F) this;
    }

    /**
     * Adds and ajax based event
     *
     * @param type
     *     the event type
     * @param evenHandler
     *     The event handler
     * @param updateModel
     *     if model object should be updated when even is fired
     */
    public F addAjaxEvent(Event type, IParentAjaxEventHandler evenHandler, boolean updateModel) {
        DatePickerAbstractDefaultAjaxBehavior datePickerAbstractDefaultAjaxBehavior = new DatePickerAbstractDefaultAjaxBehavior(
            type, evenHandler, updateModel);
        add(datePickerAbstractDefaultAjaxBehavior);
        addEvent(type, datePickerAbstractDefaultAjaxBehavior.abstractAjaxEvent);
        return (F) this;
    }

    /**
     * Allows to remove an event handler
     *
     * @param type
     *     The event type
     * @return this
     */
    public F removeEvent(Event type) {
        eventMap.remove(type);
        return (F) this;
    }

    public String getDestroyScript() {
        return "$('#" + getMarkupId() + "').datepicker('destroy');";
    }

}
