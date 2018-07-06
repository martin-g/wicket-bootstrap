package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static de.agilecoders.wicket.jquery.JQuery.$;

import java.io.Serializable;
import java.time.LocalDate;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

/**
 * A TextField that is mapped to a {@link java.time.LocalDate} object.
 * <p>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 * @author Urs Joss
 */
public class LocalDateTextField extends org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField {
    private static final long serialVersionUID = 3499287675713818823L;

    private static final String EVENT_PARAM = "datePickerEvent";
    private static final String DATE = "date";

    /**
     * Enumeration for date picker client side events.
     */
    public enum Event {
        show, // Fired when the date picker is displayed.
        hide, // Fired when the date picker is hidden.
        clearDate, // Fired when the date is cleared, normally when the “clear” button (enabled with the clearBtn option) is pressed.
        changeDate, // Fired when the date is changed.
        changeMonth, // Fired when the view month is changed from year view.
        changeYear, // Fired when the view year is changed from decade view.
        changeDecade, // Fired when the view decade is changed from century view.
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
     * Ajax event handler
     */
    public interface IAjaxEventHandler extends Serializable {

        void onAjaxEvent(AjaxRequestTarget target, LocalDate date, Event event);

    }

    private abstract class AbstractAjaxEvent extends AbstractEventHandler {

        private final Event event;
        private final IAjaxEventHandler handler;
        private final boolean updateModel;

        public AbstractAjaxEvent(Event event, IAjaxEventHandler handler, boolean updateModel) {
            this.event = event;
            this.handler = handler;
            this.updateModel = updateModel;
        }

        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            attributes.getExtraParameters().put(EVENT_PARAM, event.name());
            attributes.getExtraParameters().put(DATE, new JSONFunction("e.format()"));
        }

        protected void onAjaxEvent(AjaxRequestTarget target) {
            String dateStr = RequestCycle.get().getRequest().getRequestParameters().getParameterValue(DATE).toString(null);
            LocalDate date = !Strings.isEmpty(dateStr)?
                LocalDateTextField.this.getConverter(LocalDate.class).convertToObject(dateStr, RequestCycle.get().getRequest().getLocale()): null;
            if(updateModel) {
                LocalDateTextField.this.setDefaultModelObject(date);
            }
            handler.onAjaxEvent(target, date, event);
        }
    }

    private class DatePickerAbstractDefaultAjaxBehavior extends AbstractDefaultAjaxBehavior {

        private final AbstractAjaxEvent abstractAjaxEvent;


        public DatePickerAbstractDefaultAjaxBehavior(Event event, IAjaxEventHandler handler, boolean updateModel) {
            Args.notNull(event, "event");
            Args.notNull(handler, "handler");
            abstractAjaxEvent = createNew(event, handler, updateModel);
        }

        protected AbstractAjaxEvent createNew(Event event, IAjaxEventHandler handler, boolean updateModel) {
            return new AbstractAjaxEvent(event, handler, updateModel) {
                @Override
                protected CharSequence getBody() {
                    return LocalDateTextField.DatePickerAbstractDefaultAjaxBehavior.this.getCallbackScript().toString();
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

    private LocalDateTextFieldConfig config;

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     */
    public LocalDateTextField(final String markupId) {
        this(markupId, new LocalDateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId    The id of the text field
     * @param datePattern The format of the date
     */
    public LocalDateTextField(final String markupId, final String datePattern) {
        this(markupId, new LocalDateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model) {
        this(markupId, model, new LocalDateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId   The id of the text field
     * @param model      The date model
     * @param dateFormat The format of the date
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model, final String dateFormat) {
        this(markupId, model, new LocalDateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     * @param config   The configuration of this field
     */
    public LocalDateTextField(final String markupId, final IModel<LocalDate> model, final LocalDateTextFieldConfig config) {
        super(markupId, model, config.getFormat());

        this.config = Args.notNull(config, "config");
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param config   The configuration of this field
     */
    public LocalDateTextField(final String markupId, final LocalDateTextFieldConfig config) {
        super(markupId, config.getFormat());

        this.config = Args.notNull(config, "config");
    }

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

        if (!config.isDefaultLanguageSet()) {
            response.render(JavaScriptHeaderItem.forReference(new BootstrapDatepickerLangJsReference(config.getLanguage())));
        } else {
            response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        }

        response.render(OnDomReadyHeaderItem.forScript(createScript(config)));
    }

    /**
     * creates the initializer script.
     *
     * @return initializer script
     */
    protected CharSequence createScript(final LocalDateTextFieldConfig config) {
        CharSequence script = $(this).chain("datepicker", config).get();
        if(eventMap.isEmpty()) {
            return script;
        }
        // remove trailing ;
        StringBuilder sb = new StringBuilder(script.subSequence(0, script.length()-1));
        for(Event type: eventMap.keySet()) {
            sb.append(".on('").append(type).append("',") .append(eventMap.get(type).getFunction()).append(')');
        }
        sb.append(';');
        return sb;
    }

    /**
     * @return the config
     */
    public final LocalDateTextFieldConfig getConfig() {
        return config;
    }

    /**
     * @param config config to use
     * @return current instance
     */
    public LocalDateTextField with(LocalDateTextFieldConfig config) {
        if (config != null) {
            this.config = config;
        }
        return this;
    }

    /**
     *  Allows to register/add an event handler.
     * @param type The event type
     * @param handler he event type
     * @return this
     */
    public LocalDateTextField addEvent(Event type, AbstractEventHandler handler) {
        eventMap.put(type, handler);
        return this;
    }

    /**
     * Adds and ajax based event. Model object is not updated.
     *
     * @param type the event type
     * @param evenHandler The event handler
     */
    public LocalDateTextField addAjaxEvent(Event type, IAjaxEventHandler evenHandler) {
        addAjaxEvent(type, evenHandler, false);
        return this;
    }

    /**
     *  Adds and ajax based event
     *
     * @param type the event type
     * @param evenHandler The event handler
     * @param updateModel if model object should be updated when even is fired
     */
    public LocalDateTextField addAjaxEvent(Event type, IAjaxEventHandler evenHandler, boolean updateModel) {
        DatePickerAbstractDefaultAjaxBehavior datePickerAbstractDefaultAjaxBehavior = new DatePickerAbstractDefaultAjaxBehavior(type, evenHandler, updateModel);
        add(datePickerAbstractDefaultAjaxBehavior);
        addEvent(type, datePickerAbstractDefaultAjaxBehavior.abstractAjaxEvent);
        return this;
    }

    /**
     *  Allows to remove an event handler
     * @param type The event type
     * @return this
     */
    public LocalDateTextField removeEvent(Event type) {
        eventMap.remove(type);
        return this;
    }

    public String getDestroyScript() {
        return "$('#" + getMarkupId()+"').datepicker('destroy');";
    }
}
